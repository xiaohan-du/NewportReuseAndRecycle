package ase.newportreuseandrecycle.api;

import ase.newportreuseandrecycle.api.json.ListingJson;
import ase.newportreuseandrecycle.api.json.ListingJsonAssembler;
import ase.newportreuseandrecycle.service.ListingDto;
import ase.newportreuseandrecycle.service.ListingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class ListingRestController {
    private ListingService listingService;
    public ListingRestController(ListingService listingService) {
        this.listingService = listingService;
    }

    @GetMapping("listing")
    public ResponseEntity<List<ListingJson>> getAllListings() {
        List<ListingDto> ListingResponse = listingService.getListings();
        return ResponseEntity.ok(ListingJsonAssembler.toListingJsonList(ListingResponse));
    }
}
