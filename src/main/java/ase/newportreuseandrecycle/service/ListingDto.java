package ase.newportreuseandrecycle.service;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ListingDto {
    private Integer id;
    private Integer userId;
    private String title;
    private String description;
    private Double price;  // If price == 0, then it's up as a borrow instead of a rent?
    private String imageUrl;
    private String category;
    private String collectionOrDelivery;
    private Double latitude;
    private Double longitude;
}
