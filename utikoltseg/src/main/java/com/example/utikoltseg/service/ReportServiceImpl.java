package com.example.utikoltseg.service;

import com.example.utikoltseg.model.WorkDayModel;
import com.example.utikoltseg.repository.WorkDayRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl {
    @Autowired
    private WorkDayRepository workDayRepository;

    @SneakyThrows
    public void exportJasperReport(HttpServletResponse response) throws JRException, IOException {
        List<WorkDayModel> address = workDayRepository.findAll();
        File file = ResourceUtils.getFile("classpath:utikoltseg.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/utikoltseg","postgres","postgres");
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, con);
        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
    }

}
