package ase.newportreuseandrecycle.service;

import ase.newportreuseandrecycle.data.ReportRepository;
import ase.newportreuseandrecycle.domain.Report;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository repo) {
        this.reportRepository = repo;
    }

    @Override
    public boolean addReport(ReportDto reportDto) {

        var option = reportRepository.findByUserIdAndListingId(reportDto.getUserId(), reportDto.getListingId());

        if (option.isEmpty()) {
            reportRepository.addNewReport(new Report(reportDto.getId(), reportDto.getUserId(), reportDto.getListingId(), reportDto.getReason()));
            return true;
        }

        return false;
    }

    @Override
    public List<ReportDto> getReports() {
        List<Report> reports = reportRepository.getReports();
        return ReportAssembler.toDto(reports);
    }
}

