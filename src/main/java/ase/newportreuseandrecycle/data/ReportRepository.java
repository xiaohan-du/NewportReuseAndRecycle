package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.Report;
import ase.newportreuseandrecycle.domain.User;

import java.util.List;
import java.util.Optional;

public interface ReportRepository {
        void addNewReport(Report report);
        List<Report> getReports();

        Optional<Report> findByUserIdAndListingId(Integer userId, Integer listingId);
}

