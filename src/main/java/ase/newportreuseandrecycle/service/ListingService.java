package ase.newportreuseandrecycle.service;

import java.util.List;

public interface ListingService {
    List<ListingDto> getListings();
    void addListing(ListingDto newListing);
    List<ListingDto> getListingsByCategory(String category);
    List<CategoryDto> getCategories();
    ListingDto getAListingById(Integer id);
    void deleteListingById(Integer id);
    void updateListingById(Integer id, ListingDto newListingDto);
}
