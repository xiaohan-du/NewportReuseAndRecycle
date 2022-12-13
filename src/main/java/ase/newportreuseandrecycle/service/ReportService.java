package ase.newportreuseandrecycle.service;

import ase.newportreuseandrecycle.service.message.ListingRequest;
import ase.newportreuseandrecycle.service.message.ListingResponse;

import java.util.List;
import java.util.Optional;

public interface ReportService {
    List<ReportDto> getReports();

    void addReport(ReportDto newReport);
}

