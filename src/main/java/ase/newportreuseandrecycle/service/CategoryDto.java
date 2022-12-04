package ase.newportreuseandrecycle.service;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class CategoryDto {
    private Integer id;
    private String category;
}
