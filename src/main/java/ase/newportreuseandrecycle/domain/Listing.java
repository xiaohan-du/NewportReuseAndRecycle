package ase.newportreuseandrecycle.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Listing {
    @Id
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
