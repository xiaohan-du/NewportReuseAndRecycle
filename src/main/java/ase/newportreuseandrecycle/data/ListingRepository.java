package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.Category;
import ase.newportreuseandrecycle.domain.Listing;
import java.util.List;
import java.util.Optional;

public interface ListingRepository {
    List<Listing> getListings();
    Optional<Listing> getAListingById(Integer id);
    void addNewListing(Listing newListing);
    List<Listing> getListingsByCategory(String category);
    List<Category>  getCategories();
    Optional<Listing> getAListById(Integer id);
    void deleteListingById(Integer id);
    void updateListingById(Integer id, Listing listing);
}
