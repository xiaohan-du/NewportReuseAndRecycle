package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.Category;
import ase.newportreuseandrecycle.domain.Listing;

import javax.swing.*;
import java.util.List;

public interface ListingRepository {
    List<Listing> getListings();
    void addNewListing(Listing newListing);
    List<Listing> getListingsByCategory(String category);
    List<Category>  getCategories();
    Listing getAListById(Integer id);
    void deleteListingById(Integer id);
}
