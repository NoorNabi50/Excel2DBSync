package org.services;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.models.Product;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ExcelService {
    private XSSFWorkbook workbook = null;
    static XSSFRow row;
    static XSSFSheet sheetObj;

    public ExcelService(String filePath) throws IOException {
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        workbook = new XSSFWorkbook(fis);
    }

    public String readAndSaveData() {
        String responseMessage = "";
        try {

            List<Product> products = readDataFromExcel();

            responseMessage = "Congregations ! Everything completed successfully";
        } catch (Exception e) {
            responseMessage = "Something went wrong during the process - Error : " + e.getMessage();
        }
        return responseMessage;
    }

    private List<Product> readDataFromExcel() {
        List<Product> products = new LinkedList<>();
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int sheetIndex = 0; sheetIndex < numberOfSheets; sheetIndex++) {
            sheetObj = workbook.getSheetAt(sheetIndex);
            Iterator<Row> rowIterator = sheetObj.iterator();
            while (rowIterator.hasNext()) {
                row = (XSSFRow) rowIterator.next();
                if (row.getRowNum() > 0) {
                    Product product = new Product();
                    products.add(getRowCells(row, product));
                }
            }
        }
        return products;
    }

    private Product getRowCells(XSSFRow row, Product product) {
        Iterator<Cell> cellIterator = row.iterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            switch (cell.getCellType()) {
                case NUMERIC:
                    if (cell.getColumnIndex() == 1)
                        product.setPrice(((float) cell.getNumericCellValue()));
                    else
                        product.setStockQty(((int) cell.getNumericCellValue()));
                    break;
                case STRING:
                    product.setSkuNumber(cell.getStringCellValue());
                    break;
            }
        }
        return product;
    }
}
