package ase.newportreuseandrecycle.service;

import ase.newportreuseandrecycle.data.ReportRepository;
import ase.newportreuseandrecycle.domain.Report;
import ase.newportreuseandrecycle.domain.User;
import ase.newportreuseandrecycle.web.forms.ReportForm;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    @Override
    public List<ReportDto> getReports() {
        List<Report> reports = reportRepository.getReports();
        return ReportAssembler.toDto(reports);
    }
}

