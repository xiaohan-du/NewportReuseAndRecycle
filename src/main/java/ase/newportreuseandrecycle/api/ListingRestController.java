package ase.newportreuseandrecycle.api;

import ase.newportreuseandrecycle.api.json.CategoryJson;
import ase.newportreuseandrecycle.api.json.CategoryJsonAssembler;
import ase.newportreuseandrecycle.api.json.ListingJson;
import ase.newportreuseandrecycle.api.json.ListingJsonAssembler;
import ase.newportreuseandrecycle.service.*;
import ase.newportreuseandrecycle.service.message.CategoryRequest;
import ase.newportreuseandrecycle.service.message.CategoryResponse;
import ase.newportreuseandrecycle.service.message.ListingRequest;
import ase.newportreuseandrecycle.service.message.ListingResponse;
import ase.newportreuseandrecycle.web.forms.ListingForm;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api")
public class ListingRestController {
    private ListingService listingService;

    public ListingRestController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping("listings/{category}")
    public ResponseEntity<List<ListingJson>> getListingsByCategory(@PathVariable String category) {
        ListingResponse listingResponse;
        ListingRequest listingRequest = ListingRequest.of().build();
        if (category.equals("all")) {
            listingResponse = listingService.getListings(listingRequest);
        } else {
            listingResponse = listingService.getListingsByCategory(listingRequest, category);
        }
        return ResponseEntity.ok(ListingJsonAssembler.toListingJsonList(listingResponse.getListings()));
    }

    @GetMapping("listings/categories")
    public ResponseEntity<List<CategoryJson>> getCategories() {
        CategoryRequest categoryRequest = CategoryRequest.of().build();
        CategoryResponse categoryResponse = listingService.getCategories(categoryRequest);
        return ResponseEntity.ok(CategoryJsonAssembler.toCategoryJsonList(categoryResponse.getCategories()));
    }

    @PostMapping("listings/edit/{id}")
    public void editListing(ListingForm listingForm, @PathVariable Integer id, Model model) {
        ListingDto newListingDto;
        String imageUrl = listingForm.getImageUrl();

        if (imageUrl == null || imageUrl.isEmpty()) {
            imageUrl = "http://www.clker.com/cliparts/f/Z/G/4/h/Q/no-image-available-hi.png";
        }

        newListingDto = new ListingDto(
                listingForm.getId(),
                listingForm.getUserId(),
                listingForm.getTitle(),
                listingForm.getDescription(),
                listingForm.getPrice(),
                imageUrl,
                listingForm.getCategory(),
                listingForm.getCollectionOrDelivery(),
                listingForm.getLatitude(),
                listingForm.getLongitude());

        model.addAttribute("submitURL", String.format("edit/%s", id));
        listingService.deleteListingById(id);
        listingService.addListing(newListingDto);
    }
}
