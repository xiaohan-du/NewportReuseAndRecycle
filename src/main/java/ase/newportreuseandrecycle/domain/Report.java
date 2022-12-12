package ase.newportreuseandrecycle.domain;

import lombok.Data;

@Data
public class Report {
    private Integer id;
    private Integer userId;
    private Integer listingId;
    private String reason;

    public Report(Integer id, Integer userId, Integer listingId, String reason) {
        this.id = id;
        this.userId = userId;
        this.listingId = listingId;
        this.reason = reason;
    }
}
