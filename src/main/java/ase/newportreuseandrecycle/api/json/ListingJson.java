package ase.newportreuseandrecycle.api.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@Value
@AllArgsConstructor
@Builder(builderMethodName = "of")
public class ListingJson {
    private Integer id;
    private String title;
    private String content;
    private Boolean approved;
}
