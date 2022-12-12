package ase.newportreuseandrecycle.service;

import ase.newportreuseandrecycle.domain.Report;

import java.util.List;
import java.util.stream.Collectors;

public class ReportAssembler {

    public static List<ReportDto> toDto(List<Report> reports) {
        return reports.stream().map(c -> toDto(c)).collect(Collectors.toList());
    }
    public static ReportDto toDto(Report report) {
        return new ReportDto(
                report.getId(),
                report.getUserId(),
                report.getListingId(),
                report.getReason()
                );
    }
}
