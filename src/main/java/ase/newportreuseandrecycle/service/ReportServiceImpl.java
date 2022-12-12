package ase.newportreuseandrecycle.service;

import ase.newportreuseandrecycle.data.ReportRepository;
import ase.newportreuseandrecycle.domain.Report;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService{
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository repo) {
        this.reportRepository = repo;
    }
    @Override
    public void addReport(ReportDto reportDto) {
        Report report = new Report(
                reportDto.getId(),
                reportDto.getUserId(),
                reportDto.getListingId(),
                reportDto.getReason()
        );
        reportRepository.addNewReport(report);
    }
}
