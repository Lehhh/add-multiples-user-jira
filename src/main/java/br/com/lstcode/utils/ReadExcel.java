package br.com.lstcode.utils;

import br.com.lstcode.model.Credentials;
import br.com.lstcode.model.UserGroup;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ReadExcel {

    public List<UserGroup> readExcelUserGroup(String fileName) throws IOException {
        List<UserGroup> userGroupList = new ArrayList<>();
        FileInputStream file = new FileInputStream(new File(fileName));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            if (row.getCell(0).getCellTypeEnum().equals(CellType.STRING) && row.getCell(1).getCellTypeEnum().equals(CellType.STRING)) {
                userGroupList.add(new UserGroup(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue()));
            }
        }
        return userGroupList;
    }
    public Credentials readExcelUserPassword(String fileName) throws IOException {
        Credentials credentials = new Credentials();
        FileInputStream file = new FileInputStream(new File(fileName));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(1);
        credentials.setUsername(sheet.getRow(2).getCell(0).getStringCellValue());
        credentials.setPassword(sheet.getRow(2).getCell(1).getStringCellValue());
        credentials.setUrl(sheet.getRow(2).getCell(2).getStringCellValue());
        return credentials;
    }
}
