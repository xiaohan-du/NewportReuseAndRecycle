package ase.newportreuseandrecycle.service;

import java.util.List;

public interface ReportService {
    List<ReportDto> getReports();

    boolean addReport(ReportDto newReport);
}

