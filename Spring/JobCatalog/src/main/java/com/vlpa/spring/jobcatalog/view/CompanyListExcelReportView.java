package com.vlpa.spring.jobcatalog.view;

import com.vlpa.spring.jobcatalog.model.Company;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class CompanyListExcelReportView extends AbstractExcelView {

    private Logger logger = LoggerFactory.getLogger(CompanyListExcelReportView.class);

    @Override
    protected void buildExcelDocument(Map model, HSSFWorkbook workbook, HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws Exception {

        logger.debug("<buildExcelDocument> Start generating report...");

        List<Company> companies = (List<Company>) model.get("listCompanies");
        HSSFSheet sheet = workbook.createSheet();

        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("NAME");
        header.createCell(2).setCellValue("DESCRIPTION");
        header.createCell(3).setCellValue("EMPLOYEE_COUNT");
        header.createCell(4).setCellValue("ADDRESS");

        int rowNum = 1;
        for(Company company : companies) {
            HSSFRow row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(company.getId());
            row.createCell(1).setCellValue(company.getName());
            row.createCell(2).setCellValue(company.getDescription());
            row.createCell(3).setCellValue(company.getEmployeeCount());
            row.createCell(4).setCellValue(company.getAddress());
        }

        logger.debug("<buildExcelDocument> Report finished.");
    }
}
