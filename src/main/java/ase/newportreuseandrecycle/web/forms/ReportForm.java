package ase.newportreuseandrecycle.web.forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportForm {
    private int id;
    private int userid;
    private int listingid;
    private String reason;

}
//    public reportForm (int id, int userid,String reason) {
//        this(id, userid, reason, Boolean.FALSE);
//    }
//}
