package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.Category;
import ase.newportreuseandrecycle.domain.Listing;

import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public Optional<Listing> getAListingById(Integer id) {
        Optional<Listing> aListing;
        try {
            aListing = listingJdbcRepo.findById(id);
            return aListing;
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void addNewListing(Listing aListing) {
        String addAListingSQL = "INSERT INTO listing (user_id, title, description, price, image_url, category, collection_or_delivery, latitude, longitude) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        listingJdbcTemplate.update(addAListingSQL, aListing.getUserId(), aListing.getTitle(), aListing.getDescription(), aListing.getPrice(), aListing.getImageUrl(), aListing.getCategory(), aListing.getCollectionOrDelivery(), aListing.getLatitude(), aListing.getLongitude());
    }

    @Override
    public List<Listing> getListingsByCategory(String category) {
        List<Listing> listings = new ArrayList<>();
        listingJdbcRepo.findListingsByCategory(category).forEach(listings::add);
        return listings;
    }

    @Override
    public Optional<Listing> getAListById(Integer id) {
        return listingJdbcRepo.findListingById(id);
    }

    @Override
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        categoryJdbcRepo.findAll().forEach(categories::add);
        return categories;
    }

    @Override
    public void deleteListingById(Integer id) {
        listingJdbcRepo.deleteById(id);
    }

    @Override
    public void updateListingById(Integer id, Listing listing) {
        String updateAListing = "UPDATE listing SET user_id=?, title=?, description=?, price=?, image_url=?, category=?, collection_or_delivery=?, latitude=?, longitude=? WHERE id=?;";
        listingJdbcTemplate.update(updateAListing, listing.getUserId(), listing.getTitle(), listing.getDescription(), listing.getPrice(), listing.getImageUrl(), listing.getCategory(), listing.getCollectionOrDelivery(), listing.getLatitude(), listing.getLongitude(), listing.getId());
    }
}
