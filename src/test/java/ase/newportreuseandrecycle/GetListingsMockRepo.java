package ase.newportreuseandrecycle;

import ase.newportreuseandrecycle.data.ListingRepository;
import ase.newportreuseandrecycle.domain.Listing;
import ase.newportreuseandrecycle.service.ListingService;
import ase.newportreuseandrecycle.service.message.ListingRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GetListingsMockRepo {
    @Autowired
    private ListingService listingService;
    @Autowired
    private ListingRepository listingRepository;

    private String testCategory = "food";
    private Integer testExistingId = 4;
    private Integer testNewId = 5;

    @Test
    public void shouldGetAListingByValidCategory() {
        // GIVEN
        ListingRequest listingRequest = ListingRequest.of().build();
        // WHEN
        var listingResponse = listingService.getListingsByCategory(listingRequest, testCategory);
        // THEN
        assertEquals(2, listingResponse.getListings().size());
    }
//
    @Test
    public void shouldGetNoListingByInvalidCategory() {
        // GIVEN
        ListingRequest listingRequest = ListingRequest.of().build();
        // WHEN
        var listingResponse = listingService.getListingsByCategory(listingRequest, "test");
        // THEN
        assertEquals(0, listingResponse.getListings().size());
    }
//
    @Test
    public void shouldGetCorrectListingByValidId() {
        // GIVEN
        ListingRequest listingRequest = ListingRequest.of().build();
        for (int i = 1; i <= 4; i++) {
            // WHEN
            var listingResponse = listingService.getAListingById(listingRequest, i);
            // THEN
            assertEquals(i, listingResponse.getListingDto().getId());
        }
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void shouldAddAListingToRepo() {
        // GIVEN
        Listing l5 = new Listing(testNewId, 1, "test title 1", "test description 1", 1.99, "http://www.clker.com/cliparts/f/Z/G/4/h/Q/no-image-available-hi.png", "test category 1", "collection", 11.11, 22.22);
        listingRepository.addNewListing(l5);
        ListingRequest listingRequest = ListingRequest.of().build();
        // WHEN
        var listingResponse = listingService.getAListingById(listingRequest, testNewId);
        // THEN
        assertEquals(testNewId, listingResponse.getListingDto().getId());
    }

    @Test
    public void shouldGetListingsByCategory() {
        // GIVEN
        listingRepository.getListingsByCategory(testCategory);
        ListingRequest listingRequest = ListingRequest.of().build();
        // WHEN
        var listingResponse = listingService.getListingsByCategory(listingRequest, testCategory);
        // THEN
        assertEquals(2, listingResponse.getListings().size());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void shouldDeleteListingById() {
        // GIVEN
        Optional<Listing> listingFour = listingRepository.getAListingById(testExistingId);
        listingRepository.deleteListingById(testExistingId);
        ListingRequest listingRequest = ListingRequest.of().build();
        // WHEN
        var listingResponse = listingService.getListings(listingRequest);
        // THEN
        assertEquals(3, listingResponse.getListings().size());
    }
}
