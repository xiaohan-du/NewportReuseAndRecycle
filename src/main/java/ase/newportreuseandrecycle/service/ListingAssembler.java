package ase.newportreuseandrecycle.service;

import ase.newportreuseandrecycle.domain.Listing;

import java.util.List;
import java.util.stream.Collectors;

public class ListingAssembler {
    public static List<ListingDto> toDto(List<Listing> listings) {
        return listings.stream().map(l -> toDto(l)).collect(Collectors.toList());
    }

    public static ListingDto toDto(Listing listing) {
        return new ListingDto(
                listing.getId(),
                listing.getUserId(),
                listing.getTitle(),
                listing.getDescription(),
                listing.getPrice(),
                listing.getImageUrl(),
                listing.getCategory(),
                listing.getCollectionOrDelivery()
        );
    }
}
