package ase.newportreuseandrecycle.service;

import java.util.List;
import java.util.Optional;

public interface ListingService {
    List<ListingDto> getListings();
    Optional<ListingDto> getAListingById(Integer id);
    void addListing(ListingDto newListing);
    List<ListingDto> getListingsByCategory(String category);
    List<CategoryDto> getCategories();
    void deleteListingById(Integer id);
    void updateListingById(Integer id, ListingDto newListingDto);
}
