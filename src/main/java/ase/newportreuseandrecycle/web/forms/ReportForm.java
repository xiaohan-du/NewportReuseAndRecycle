package ase.newportreuseandrecycle.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportForm {
    private int id;
    private int userId;
    private int listingId;
    private String reason;

}

