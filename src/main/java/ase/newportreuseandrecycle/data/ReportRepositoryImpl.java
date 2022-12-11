package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.Report;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReportRepositoryImpl implements ReportRepository {

    private final JdbcTemplate listingJdbcTemplate;
    public ReportRepositoryImpl(JdbcTemplate aReportJdbcTemplate) {
        this.listingJdbcTemplate = aReportJdbcTemplate;
    }

    @Override
    public void addNewReport(Report aReport) {
        String addAListingSQL = "INSERT INTO report (user_id, listing_id, reason) VALUES (?, ?, ?)";
        listingJdbcTemplate.update(addAListingSQL, aReport.getUserId(),aReport.getListingId(),aReport.getReason());
    }

}
