package ase.newportreuseandrecycle.service;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ListingDto {
    private Integer id;
    private String title;
    private String content;
    private Boolean approved;
}
