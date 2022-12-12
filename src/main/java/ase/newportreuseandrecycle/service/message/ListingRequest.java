package ase.newportreuseandrecycle.service.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import org.springframework.data.annotation.Id;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class ListingRequest {
    @Id
    private Integer id;
    private Integer userId;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private String category;
    private String collectionOrDelivery;
    private Double latitude;
    private Double longitude;
}
