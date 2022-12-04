package ase.newportreuseandrecycle.service;

import ase.newportreuseandrecycle.domain.Category;

import java.util.List;
import java.util.stream.Collectors;

public class CategoryAssembler {
    public static List<CategoryDto> toDto(List<Category> categories) {
        return categories.stream().map(c -> toDto(c)).collect(Collectors.toList());
    }

    public static CategoryDto toDto(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getCategory()
        );
    }
}
