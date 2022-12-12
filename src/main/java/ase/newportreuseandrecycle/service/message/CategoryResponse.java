package ase.newportreuseandrecycle.service.message;

import ase.newportreuseandrecycle.service.CategoryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class CategoryResponse {
    private final CategoryRequest categoryRequest;
    private List<CategoryDto> categories;
}
