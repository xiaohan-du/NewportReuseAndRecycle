package ase.newportreuseandrecycle.data;

import ase.newportreuseandrecycle.domain.Report;
import ase.newportreuseandrecycle.domain.User;

import java.util.List;

public interface ReportRepository {
        void addNewReport(Report report);
        List<Report> getReports();
}

