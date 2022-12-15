package ase.newportreuseandrecycle.domain;

import org.springframework.data.annotation.Id;
import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Listing {
    @Id
    private Integer id;
    
    @NonNull()
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
