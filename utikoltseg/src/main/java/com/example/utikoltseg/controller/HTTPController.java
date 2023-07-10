package com.example.utikoltseg.controller;

import com.example.utikoltseg.model.TmpInfoModel;
import com.example.utikoltseg.model.WorkDayModel;
import com.example.utikoltseg.repository.TmpInfoRepository;
import com.example.utikoltseg.repository.WorkDayRepository;
import com.example.utikoltseg.service.ReportServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class HTTPController {

    @Autowired
    private WorkDayRepository workDayRepository;
    @Autowired
    private TmpInfoRepository tmpInfoRepository;
    @Autowired
    private ReportServiceImpl reportServiceImpl;

    private void truncateTable() {
        workDayRepository.truncateTable();
        tmpInfoRepository.truncateTable();
    }

    private boolean tableTruncated = false; // Új változó a tábla törlésének jelzésére

    @PostMapping("/save")
    public String handleRequest(@RequestBody WorkDayModel workDayModel) {
        if (!tableTruncated) {
            workDayRepository.truncateTable();
            tableTruncated = true;
        }
        workDayRepository.save(workDayModel);
        return "response";
    }

    @PostMapping("/save-tmp")
    public String handleRequestV2(@RequestBody TmpInfoModel tmpInfoModel) {
        tmpInfoRepository.truncateTable();

        Integer length = Math.toIntExact(workDayRepository.count());
        System.out.println(length);
        tmpInfoModel.setSumMoney(length*tmpInfoModel.getSumMoney());

        tmpInfoRepository.save(tmpInfoModel);
        return "response";
    }

    @GetMapping("/jasperpdf/export")
    public void createPDF(HttpServletResponse response) throws IOException, JRException {
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        reportServiceImpl.exportJasperReport(response);
        truncateTable();
    }

}
