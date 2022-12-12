package ase.newportreuseandrecycle.service;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class ReportDto {

        private Integer id;
        private Integer userid;
        private Integer listingid;
        private String reason;
}
