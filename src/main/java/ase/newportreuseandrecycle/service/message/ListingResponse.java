package ase.newportreuseandrecycle.service.message;

import ase.newportreuseandrecycle.service.ListingDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class ListingResponse {
    private final ListingRequest listingRequest;
    private List<ListingDto> listings;
    private ListingDto listing;
}
