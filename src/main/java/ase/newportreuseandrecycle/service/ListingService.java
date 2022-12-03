package ase.newportreuseandrecycle.service;

import java.util.List;

public interface ListingService {
    List<ListingDto> getListings();
    List<ListingDto> getListingsByCategory(String category);
}
