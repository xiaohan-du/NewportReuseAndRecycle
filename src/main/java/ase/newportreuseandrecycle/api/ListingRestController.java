package ase.newportreuseandrecycle.api;

import ase.newportreuseandrecycle.api.json.CategoryJson;
import ase.newportreuseandrecycle.api.json.CategoryJsonAssembler;
import ase.newportreuseandrecycle.api.json.ListingJson;
import ase.newportreuseandrecycle.api.json.ListingJsonAssembler;
import ase.newportreuseandrecycle.service.CategoryDto;
import ase.newportreuseandrecycle.service.ListingDto;
import ase.newportreuseandrecycle.service.ListingService;
import ase.newportreuseandrecycle.web.forms.ListingForm;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
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
        List<ListingDto> listingResponse;
        if (category.equals("all")) {
            listingResponse = listingService.getListings();
        } else {
            listingResponse = listingService.getListingsByCategory(category);
        }
        return ResponseEntity.ok(ListingJsonAssembler.toListingJsonList(listingResponse));
    }

    @GetMapping("listings/categories")
    public ResponseEntity<List<CategoryJson>> getCategories() {
        List<CategoryDto> categroyResponse = listingService.getCategories();
        return ResponseEntity.ok(CategoryJsonAssembler.toCategoryJsonList(categroyResponse));
    }

    @PostMapping("/listings/edit/{id}")
    public void editListing(ListingForm newListing, @PathVariable Integer id) {
        ListingDto newListingDto = new ListingDto(6, 6, "123", "12311", 22.0, "https://en.wikipedia.org/wiki/Image#/media/File:Image_created_with_a_mobile_phone.png", "food");
        listingService.deleteListingById(id);
        listingService.addListing(newListingDto);
    }
}
