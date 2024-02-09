package com.pakricorn.employeeInfo.service;

import com.pakricorn.employeeInfo.entity.Employee;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadService {

    public static boolean isValidExcelFile(MultipartFile file){

        return Objects.equals(file.getContentType(),"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Employee> getEmpData(InputStream inputStream){
        List<Employee> employees = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("employees");
            int rowIndex =0;
            for (Row row : sheet){
                if (rowIndex ==0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                Employee employee = new Employee();
                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 0 -> employee.setId(cell.getStringCellValue());
                        case 1 -> employee.setFirstname(cell.getStringCellValue());
                        case 2 -> employee.setLastname(cell.getStringCellValue());
                        case 3 -> employee.setAddress(cell.getStringCellValue());
                        case 4 -> employee.setMobileNumber((long) cell.getNumericCellValue());
                        case 5 -> employee.setEmail(cell.getStringCellValue());
                        case 6 -> employee.setAadhaar(cell.getStringCellValue());
                        case 7 -> employee.setPanNumber(cell.getStringCellValue());
                        case 8 -> employee.setProjectId(cell.getStringCellValue());
                        default -> {
                        }
                    }
                    cellIndex++;
                }
                employees.add(employee);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return employees;
    }

}
