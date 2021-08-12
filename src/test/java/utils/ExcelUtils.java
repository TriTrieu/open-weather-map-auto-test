package utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelUtils {

    private static XSSFSheet excelWSheet;

    private static XSSFWorkbook excelWBook;

    private static XSSFCell excelCell;

    private static XSSFRow Row;

    public static Object[][] getTableArray(String filePath, String sheetName) {
        String[][] tabArray = null;
        try {
            FileInputStream ExcelFile = new FileInputStream(filePath);
            // Access the required test data sheet
            excelWBook = new XSSFWorkbook(ExcelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
            int startRow = 1;
            int startCol = 0;
            int ci, cj;
            int totalRows = excelWSheet.getLastRowNum();

            // you can write a function as well to get Column count
            int totalCols = excelWSheet.getRow(0).getPhysicalNumberOfCells();
            tabArray = new String[totalRows][totalCols];
            ci = 0;
            for (int i = startRow; i <= totalRows; i++, ci++) {
                cj = 0;
                for (int j = startCol; j < totalCols; j++, cj++) {
                    tabArray[ci][cj] = getCellData(i, j);
                    System.out.println(tabArray[ci][cj]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return (tabArray);
    }

    public static String getCellData(int rowNum, int colNum) {
        try {
            String cellValue = null;
            excelCell = excelWSheet.getRow(rowNum).getCell(colNum);
            CellType cellType = excelCell.getCellType();
            switch (cellType) {
                case BOOLEAN:
                    cellValue = String.valueOf(excelCell.getBooleanCellValue());
                    break;
                case NUMERIC:
                    cellValue = NumberToTextConverter.toText(excelCell.getNumericCellValue());
                    break;
                case STRING:
                    cellValue = excelCell.getStringCellValue();
                    break;
                case _NONE:
                case BLANK:
                case ERROR:
                    break;
                default:
                    break;
            }
            return cellValue;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

}

