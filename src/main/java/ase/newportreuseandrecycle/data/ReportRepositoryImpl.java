package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.Listing;
import ase.newportreuseandrecycle.domain.Report;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class ReportRepositoryImpl implements ReportRepository {

    private final JdbcTemplate listingJdbcTemplate;
    public ReportRepositoryImpl(JdbcTemplate aReportJdbcTemplate) {
        this.listingJdbcTemplate = aReportJdbcTemplate;
    }

    @Override
    public void addNewReport(Report aReport) {
        String addAListingSQL = "INSERT INTO report (user_id, listing_id, reason) VALUES (?, ?, ?)";
        listingJdbcTemplate.update(addAListingSQL, aReport.getUserId(),aReport.getListingid(),aReport.getReason());
    }

}
