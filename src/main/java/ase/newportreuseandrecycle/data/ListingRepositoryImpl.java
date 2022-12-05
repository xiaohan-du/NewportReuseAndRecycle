package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.Category;
import ase.newportreuseandrecycle.domain.Listing;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ListingRepositoryImpl implements ListingRepository{

    private final JdbcTemplate listingJdbcTemplate;
    private final ListingRepositorySpringDataJdbc listingJdbcRepo;
    private final CategoryRepositorySpringDataJdbc categoryJdbcRepo;
    public ListingRepositoryImpl(ListingRepositorySpringDataJdbc aListingJdbcRepo, CategoryRepositorySpringDataJdbc aCategoryJdbcRepo, JdbcTemplate aListingJdbcTemplate) {
        this.listingJdbcRepo = aListingJdbcRepo;
        this.categoryJdbcRepo = aCategoryJdbcRepo;
        this.listingJdbcTemplate = aListingJdbcTemplate;
    }

    @Override
    public List<Listing> getListings() {
        List<Listing> listings = new ArrayList<>();
        listingJdbcRepo.findAll().forEach(listings::add);
        return listings;
    }

    @Override
    public void addNewListing(Listing aListing) {
        String addAListingSQL = "INSERT INTO listing (user_id, title, description, price, image_url, category) VALUES (?, ?, ?, ?, ?, ?)";
        listingJdbcTemplate.update(addAListingSQL, aListing.getUserId(), aListing.getTitle(), aListing.getDescription(), aListing.getPrice(), aListing.getImageUrl(), aListing.getCategory());
    }

    public List<Listing> getListingsByCategory(String category) {
        List<Listing> listings = new ArrayList<>();
        listingJdbcRepo.findListingsByCategory(category).forEach(listings::add);
        return listings;
    }

    @Override
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        categoryJdbcRepo.findAll().forEach(categories::add);
        return categories;
    }
}
