package ase.newportreuseandrecycle.domain;

import lombok.Data;

@Data
public class Listing {
    private Integer id;
    private String title;
    private String content;
    private Boolean approved;

}
