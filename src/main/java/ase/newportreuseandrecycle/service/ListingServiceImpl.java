package ase.newportreuseandrecycle.service;

import ase.newportreuseandrecycle.data.ListingRepository;
import ase.newportreuseandrecycle.domain.Category;
import ase.newportreuseandrecycle.domain.Listing;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ListingServiceImpl implements ListingService{
    private final ListingRepository listingRepository;

    public ListingServiceImpl(ListingRepository repo) {
        this.listingRepository = repo;
    }

    @Override
    public List<ListingDto> getListings() {
        List<Listing> listings = listingRepository.getListings();
        return ListingAssembler.toDto(listings);
    }

    @Override
    public void addListing(ListingDto listingDto) {
        Listing listing = new Listing(
                listingDto.getId(),
                listingDto.getUserId(),
                listingDto.getTitle(),
                listingDto.getDescription(),
                listingDto.getPrice(),
                listingDto.getImageUrl(),
                listingDto.getCategory(),
                listingDto.getCollectionOrDelivery()
        );
        listingRepository.addNewListing(listing);

    }

    public List<ListingDto> getListingsByCategory(String cagtegory) {
        List<Listing> listings = listingRepository.getListingsByCategory(cagtegory);
        return ListingAssembler.toDto(listings);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = listingRepository.getCategories();
        return CategoryAssembler.toDto(categories);
    }
}
