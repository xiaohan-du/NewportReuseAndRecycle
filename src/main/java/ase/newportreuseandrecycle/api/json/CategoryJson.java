package ase.newportreuseandrecycle.api.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class CategoryJson {
    private Integer id;
    private String category;
}
