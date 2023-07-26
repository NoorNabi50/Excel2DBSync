package org.excelreader;


import org.services.ExcelService;

import java.io.IOException;

public class Main {
    private static final String filePath = "D:\\Products.xlsx";
    public static void main(String[] args) throws IOException {

          ExcelService excelService = new ExcelService(filePath);
          excelService.readAndSaveData();

    }
}