package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.Listing;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ListingRepositoryImpl implements ListingRepository{
    private final ListingRepositorySpringDataJdbc jdbcRepo;
    public ListingRepositoryImpl(ListingRepositorySpringDataJdbc aJdbcRepo) {
        this.jdbcRepo = aJdbcRepo;
    }

    @Override
    public List<Listing> getListings() {
        List<Listing> listings = new ArrayList<>();
        jdbcRepo.findAll().forEach(listings::add);
        return listings;
    }
}
