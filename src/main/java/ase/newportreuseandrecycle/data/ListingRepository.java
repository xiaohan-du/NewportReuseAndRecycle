package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.Listing;

import java.util.List;

public interface ListingRepository {
    List<Listing> getListings();
    List<Listing> getListingsByCategory(String category);
}
