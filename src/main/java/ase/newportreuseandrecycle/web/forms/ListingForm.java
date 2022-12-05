package ase.newportreuseandrecycle.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListingForm {
    private Integer id;
    private Integer userId;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
}
