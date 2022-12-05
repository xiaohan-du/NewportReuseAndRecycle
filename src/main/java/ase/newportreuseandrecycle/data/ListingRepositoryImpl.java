package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.Listing;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ListingRepositoryImpl implements ListingRepository{

    private final JdbcTemplate jdbcTemplate;
    private final ListingRepositorySpringDataJdbc jdbcRepo;

    public ListingRepositoryImpl(ListingRepositorySpringDataJdbc aJdbcRepo, JdbcTemplate jdbcTemplate) {
        this.jdbcRepo = aJdbcRepo;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Listing> getListings() {
        List<Listing> listings = new ArrayList<>();
        jdbcRepo.findAll().forEach(listings::add);
        return listings;
    }

    @Override
    public void addNewListing(Listing aListing) {
        String addAListingSQL = "INSERT INTO listing (user_id, title, description, price, image_url) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(addAListingSQL, aListing.getUserId(), aListing.getTitle(), aListing.getDescription(), aListing.getPrice(), aListing.getImageUrl());
    }
}
