package ase.newportreuseandrecycle;

import ase.newportreuseandrecycle.data.ListingRepository;
import ase.newportreuseandrecycle.domain.Listing;
import ase.newportreuseandrecycle.service.ListingService;
import ase.newportreuseandrecycle.service.message.ListingRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Optional;

@SpringBootTest
public class GetListingsMockRepo {
    @Autowired
    private ListingService listingService;
    @MockBean
    private ListingRepository listingRepository;

    @Test
    public void shouldGetTwoListings() {
        // GIVEN
        Listing l1 = new Listing(1, 1, "test title 1", "test description 1", 1.99, "http://www.clker.com/cliparts/f/Z/G/4/h/Q/no-image-available-hi.png", "test category 1", "collection", 11.11, 22.22);
        Listing l2 = new Listing(2, 2, "test title 2", "test description 2", 2.99, "http://www.clker.com/cliparts/f/Z/G/4/h/Q/no-image-available-hi.png", "test category 2", "collection", 33.33, 44.44);
        given(listingRepository.getListings()).willReturn(List.of(l1, l2));
        ListingRequest listingRequest = ListingRequest.of().build();
        // WHEN
        var listingResponse = listingService.getListings(listingRequest);
        // THEN
        assertEquals(2, listingResponse.getListings().size());
    }

    @Test
    public void shouldGetAListingByValidCategory() {
        // GIVEN
        Listing l1 = new Listing(1, 1, "test title 1", "test description 1", 1.99, "http://www.clker.com/cliparts/f/Z/G/4/h/Q/no-image-available-hi.png", "test category 1", "collection", 11.11, 22.22);
        given(listingRepository.getListingsByCategory("test category 1")).willReturn(List.of(l1));
        ListingRequest listingRequest = ListingRequest.of().build();
        // WHEN
        var listingResponse = listingService.getListingsByCategory(listingRequest, "test category 1");
        // THEN
        assertEquals(1, listingResponse.getListings().size());
    }

    @Test
    public void shouldGetNoListingByInvalidCategory() {
        // GIVEN
        ListingRequest listingRequest = ListingRequest.of().build();
        // WHEN
        var listingResponse = listingService.getListingsByCategory(listingRequest, "test category 2");
        // THEN
        assertEquals(0, listingResponse.getListings().size());
    }
}
