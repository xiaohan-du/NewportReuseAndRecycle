package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.Report;
import ase.newportreuseandrecycle.domain.User;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReportRepositoryImpl implements ReportRepository {

    private RowMapper<Report> ReportRowMapper;
    private final JdbcTemplate listingJdbcTemplate;
    public ReportRepositoryImpl(JdbcTemplate aReportJdbcTemplate) {
        this.listingJdbcTemplate = aReportJdbcTemplate;
        setReportRowMapper();
    }

    @Override
    public void addNewReport(Report aReport) {
        String addAListingSQL = "INSERT INTO report (user_id, listing_id, reason) VALUES (?, ?, ?)";
        listingJdbcTemplate.update(addAListingSQL, aReport.getUserId(),aReport.getListingId(),aReport.getReason());
    }



    private void setReportRowMapper() {
        ReportRowMapper = (rm, index) -> new Report(
                rm.getInt("ID"),
                rm.getInt("USER_ID"),
                rm.getInt("LISTING_ID"),
                rm.getString("Reason")
                );

    }
    @Override
    public List<Report> getReports() {
        String getReportsSQL = "SELECT * FROM report";
        JdbcOperations jdbcTemplate;
        return listingJdbcTemplate.query(getReportsSQL, ReportRowMapper);
    }

    @Override
    public Optional<Report> findByUserIdAndListingId(Integer userId, Integer listingId) {
        String findByUserIdAndListingIdSQL = "SELECT * FROM report WHERE user_Id = ? AND listing_Id = ?";
        List<Report> reports = listingJdbcTemplate.query(findByUserIdAndListingIdSQL, ReportRowMapper, userId, listingId);
        System.out.println(reports);
        if (reports.size() > 0) {
            return Optional.of(reports.get(0));
        }

        return Optional.empty();
    }

}
