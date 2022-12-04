package ase.newportreuseandrecycle.domain;

import lombok.Data;

@Data
public class Listing {
    private Integer id;
    private Integer userId;
    private String title;
    private String description;
    private Double price;  // If price == 0, then it's up as a borrow instead of a rent?
    private String imageUrl;
    private String category;
}
