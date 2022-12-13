package ase.newportreuseandrecycle;

import ase.newportreuseandrecycle.data.ListingRepository;
import ase.newportreuseandrecycle.data.ListingRepositoryImpl;
import ase.newportreuseandrecycle.domain.Category;
import ase.newportreuseandrecycle.domain.Listing;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest
public class ListingsMockRepo {

    private ListingRepository listingRepository = mock(ListingRepositoryImpl.class);

    @Test
    public void shouldGetListings() {
        // GIVEN
        Listing l1 = new Listing(
                1,
                1,
                "test title 1",
                "test description 1",
                1.99,
                "http://www.clker.com/cliparts/f/Z/G/4/h/Q/no-image-available-hi.png",
                "test category 1",
                "collection",
                11.11,
                11.11
        );
        Listing l2 = new Listing(
                2,
                2,
                "test title 2",
                "test description 2",
                2.99,
                "http://www.clker.com/cliparts/f/Z/G/4/h/Q/no-image-available-hi.png",
                "test category 2",
                "collection",
                22.22,
                22.22
        );
        given(listingRepository.getListings()).willReturn(List.of(l1, l2));
        // WHEN
        List<Listing> listings = listingRepository.getListings();
        // THEN
        assertEquals(2, listings.size());
    }
    @Test
    public void shouldGetAListingById() {
        // GIVEN
        Listing l1 = new Listing(
                1,
                1,
                "test title 1",
                "test description 1",
                1.99,
                "http://www.clker.com/cliparts/f/Z/G/4/h/Q/no-image-available-hi.png",
                "test category 1",
                "collection",
                11.11,
                11.11
        );
        given(listingRepository.getAListingById(1)).willReturn(Optional.of(l1));
        // WHEN
        Optional<Listing> aListing = listingRepository.getAListingById(1);
        // THEN
        assertEquals(1, aListing.get().getId());
    }

    @Test
    public void shouldGetEmptyListingByInvalidId() {
        // GIVEN
        Integer invalidId = 0;
        // WHEN
        Optional<Listing> aListing = listingRepository.getAListingById(invalidId);
        // THEN
        assertEquals(Optional.empty(), aListing);
    }

    @Test
    public void shouldGetListingsByCategory() {
        // GIVEN
        Listing l1 = new Listing(
                1,
                1,
                "test title 1",
                "test description 1",
                1.99,
                "http://www.clker.com/cliparts/f/Z/G/4/h/Q/no-image-available-hi.png",
                "test category",
                "collection",
                11.11,
                11.11
        );
        Listing l2 = new Listing(
                2,
                2,
                "test title 2",
                "test description 2",
                2.99,
                "http://www.clker.com/cliparts/f/Z/G/4/h/Q/no-image-available-hi.png",
                "test category",
                "collection",
                22.22,
                22.22
        );
        given(listingRepository.getListingsByCategory("test category")).willReturn(List.of(l1, l2));
        // WHEN
        List<Listing> listings = listingRepository.getListingsByCategory("test category");
        // THEN
        assertEquals(2, listings.size());
    }
    @Test
    public void shouldGetCategories() {
        // GIVEN
        Category c1 = new Category(
                1,
                "test category"
        );
        Category c2 = new Category(
                2,
                "test category"
        );
        given(listingRepository.getCategories()).willReturn(List.of(c1, c2));
        // WHEN
        List<Category> categories = listingRepository.getCategories();
        // THEN
        assertEquals(2, categories.size());
    }

}
