package ase.newportreuseandrecycle.service;

import ase.newportreuseandrecycle.service.message.CategoryRequest;
import ase.newportreuseandrecycle.service.message.CategoryResponse;
import ase.newportreuseandrecycle.service.message.ListingRequest;
import ase.newportreuseandrecycle.service.message.ListingResponse;

public interface ListingService {
    ListingResponse getListings(ListingRequest listingRequest);
    void addListing(ListingDto newListing);
    ListingResponse getListingsByCategory(ListingRequest listingRequest, String category);
    CategoryResponse getCategories(CategoryRequest categoryRequest);
    ListingResponse getAListingById(ListingRequest listingRequest, Integer id);
    void deleteListingById(Integer id);
}
