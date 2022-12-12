package ase.newportreuseandrecycle.service.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class CategoryRequest {
    private Integer id;
    private String category;
}
