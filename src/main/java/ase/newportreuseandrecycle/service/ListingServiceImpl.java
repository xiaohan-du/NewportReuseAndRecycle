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
import java.util.Optional;
@Service
public class ListingServiceImpl implements ListingService{
    private final ListingRepository listingRepository;

    public ListingServiceImpl(ListingRepository repo) {
        this.listingRepository = repo;
    }
    private List<ListingDto> getListingsDtoByCategory(String category) {
        List<Listing> listings;
        if (category.isEmpty()) {
            listings = listingRepository.getListings();
        } else {
            listings = listingRepository.getListingsByCategory(category);
        }
        List<ListingDto> listingsDto = ListingAssembler.toDto(listings);
        return listingsDto;
    }

    private ListingDto getListingDtoById(Integer id) {
        Optional<Listing> listing;

        listing = listingRepository.getAListingById(id);
        ListingDto listingsDto = ListingAssembler.toDto(listing.get());
        return listingsDto;
    }

    @Override
    public ListingResponse getListings(ListingRequest listingRequest) {
        List<ListingDto> listingsDto = getListingsDtoByCategory("");
        return ListingResponse
                .of()
                .listingRequest(listingRequest)
                .listings(listingsDto)
                .build();
    }

    @Override
    public ListingResponse getAListingById(ListingRequest listingRequest, Integer id) {
        ListingDto listingDto = getListingDtoById(id);
        return ListingResponse
                .of()
                .listingRequest(listingRequest)
                .listingDto(listingDto)
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
        List<ListingDto> listingsDto = getListingsDtoByCategory(category);
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
    public ListingResponse getListingsByUserId(ListingRequest listingRequest, Integer userId) {
        List<Listing> listings = listingRepository.getListingsByUserId(userId);
        List<ListingDto> listingDtos = ListingAssembler.toDto(listings);
        return ListingResponse
                .of()
                .listingRequest(listingRequest)
                .listings(listingDtos)
                .build();
    }

    @Override
    public void updateListingById(Integer id, ListingDto listingDto) {
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

        listingRepository.updateListingById(id, listing);
    }
}
