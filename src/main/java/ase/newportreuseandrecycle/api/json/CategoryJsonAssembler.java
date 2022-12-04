package ase.newportreuseandrecycle.api.json;

import ase.newportreuseandrecycle.service.CategoryDto;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryJsonAssembler {
    public static CategoryJson toCategoryJson(CategoryDto categoryDto) {
        return CategoryJson
                .of()
                .id(categoryDto.getId())
                .category(categoryDto.getCategory())
                .build();
    }
    public static List<CategoryJson> toCategoryJsonList(List<CategoryDto> categoryDtoList) {
        return categoryDtoList
                .stream()
                .map(c -> CategoryJsonAssembler.toCategoryJson(c))
                .collect(Collectors.toList());
    }
}
