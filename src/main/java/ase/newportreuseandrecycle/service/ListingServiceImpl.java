package ase.newportreuseandrecycle.service;

import ase.newportreuseandrecycle.data.ListingRepository;
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
}
