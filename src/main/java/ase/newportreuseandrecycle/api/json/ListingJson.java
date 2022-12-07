package ase.newportreuseandrecycle.api.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class ListingJson {
    private Integer id;
    private Integer userId;
    private String title;
    private String description;
    private Double price;  // If price == 0, then it's up as a borrow instead of a rent?
    private String imageUrl;
    private String category;
    private String collectionOrDelivery;
}
