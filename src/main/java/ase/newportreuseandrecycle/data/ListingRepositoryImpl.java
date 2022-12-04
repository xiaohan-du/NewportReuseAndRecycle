package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.Category;
import ase.newportreuseandrecycle.domain.Listing;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ListingRepositoryImpl implements ListingRepository{
    private final ListingRepositorySpringDataJdbc listingJdbcRepo;
    private final CategoryRepositorySpringDataJdbc categoryJdbcRepo;
    public ListingRepositoryImpl(ListingRepositorySpringDataJdbc aListingJdbcRepo, CategoryRepositorySpringDataJdbc aCategoryJdbcRepo) {
        this.listingJdbcRepo = aListingJdbcRepo;
        this.categoryJdbcRepo = aCategoryJdbcRepo;
    }

    @Override
    public List<Listing> getListings() {
        List<Listing> listings = new ArrayList<>();
        listingJdbcRepo.findAll().forEach(listings::add);
        return listings;
    }

    @Override
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
