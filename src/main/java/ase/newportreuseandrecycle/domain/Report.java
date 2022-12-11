package ase.newportreuseandrecycle.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Report {
    private Integer id;
    private Integer userId;
    private Integer listingid;
    private String reason;

    public Report(Integer id, Integer userid, Integer listingid, String reason) {
        this.id=id;
        this.userId=userid;
        this.listingid=listingid;
        this.reason=reason;
    }
}
