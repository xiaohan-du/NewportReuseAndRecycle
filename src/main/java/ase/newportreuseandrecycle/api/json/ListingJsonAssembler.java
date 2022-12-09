package ase.newportreuseandrecycle.api.json;

import ase.newportreuseandrecycle.service.ListingDto;

import java.util.List;
import java.util.stream.Collectors;

public class ListingJsonAssembler {
    public static ListingJson toListingJson(ListingDto listingDto) {
        return ListingJson
                .of()
                .id(listingDto.getId())
                .userId(listingDto.getUserId())
                .title(listingDto.getTitle())
                .description(listingDto.getDescription())
                .price(listingDto.getPrice())
                .imageUrl(listingDto.getImageUrl())
                .category(listingDto.getCategory())
                .collectionOrDelivery(listingDto.getCollectionOrDelivery())
                .latitude(listingDto.getLatitude())
                .longitude(listingDto.getLongitude())
                .build();
    }

    public static List<ListingJson> toListingJsonList(List<ListingDto> listingDtoList) {
        return listingDtoList
                .stream()
                .map(l -> ListingJsonAssembler.toListingJson(l))
                .collect(Collectors.toList());
    }
}
