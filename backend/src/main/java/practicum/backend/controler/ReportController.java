package practicum.backend.controler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import practicum.backend.dto.Report;
import practicum.backend.service.ReportService;

@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/reports")
    public Report generateReport() {
        return reportService.generateReport();
    }
}
