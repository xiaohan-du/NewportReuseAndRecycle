package ase.newportreuseandrecycle.service;

import ase.newportreuseandrecycle.data.ListingRepository;
import ase.newportreuseandrecycle.domain.Category;
import ase.newportreuseandrecycle.domain.Listing;
import ase.newportreuseandrecycle.service.message.CategoryRequest;
import ase.newportreuseandrecycle.service.message.CategoryResponse;
import ase.newportreuseandrecycle.service.message.ListingRequest;
import ase.newportreuseandrecycle.service.message.ListingResponse;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ListingServiceImpl implements ListingService{
    private final ListingRepository listingRepository;

    public ListingServiceImpl(ListingRepository repo) {
        this.listingRepository = repo;
    }
    private List<ListingDto> getListingDto(String category) {
        List<Listing> listings;
        if (category.isEmpty()) {
            listings = listingRepository.getListings();
        } else {
            listings = listingRepository.getListingsByCategory(category);
        }
        List<ListingDto> listingsDto = ListingAssembler.toDto(listings);
        return listingsDto;
    }

    @Override
    public ListingResponse getListings(ListingRequest listingRequest) {
        List<ListingDto> listingsDto = getListingDto("");
        return ListingResponse
                .of()
                .listingRequest(listingRequest)
                .listings(listingsDto)
                .build();
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
                listingDto.getCollectionOrDelivery(),
                listingDto.getLatitude(),
                listingDto.getLongitude()
        );
        listingRepository.addNewListing(listing);

    }

    @Override
    public void deleteListingById(Integer id) {
        listingRepository.deleteListingById(id);
    }
    @Override
    public ListingResponse getListingsByCategory(ListingRequest listingRequest, String category) {
        List<ListingDto> listingsDto = getListingDto(category);
        return ListingResponse
                .of()
                .listingRequest(listingRequest)
                .listings(listingsDto)
                .build();
    }

    @Override
    public CategoryResponse getCategories(CategoryRequest categoryRequest) {
        List<Category> categories = listingRepository.getCategories();
        List<CategoryDto> categoriesDto = CategoryAssembler.toDto(categories);
        return CategoryResponse
                .of()
                .categoryRequest(categoryRequest)
                .categories(categoriesDto)
                .build();
    }

    @Override
    public ListingResponse getAListingById(ListingRequest listingRequest, Integer id) {
        ListingDto listingDto = ListingAssembler.toDto(listingRepository.getAListById(id));
        return ListingResponse
                .of()
                .listingRequest(listingRequest)
                .listing(listingDto)
                .build();
    }
}
