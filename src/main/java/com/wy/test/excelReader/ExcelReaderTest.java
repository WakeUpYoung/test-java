package com.wy.test.excelReader;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.xssf.usermodel.*;

public class ExcelReaderTest {
    public static void main(String[] args) {
        File file = new File("C:/Users/Wang Yu/Desktop/项目/test.xlsx");
        readOldExcel(file);

    }

    private static String getCellValue(XSSFCell cell){
        String value = "";
        switch (cell.getCellType()){
            case STRING:
                value = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (HSSFDateUtil.isCellDateFormatted(cell)){
                    Date date = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    value = String.valueOf(df.format(date));
                }else{
                    value = String.valueOf(cell.getNumericCellValue());
                }
                break;
            default:
                break;
        }
        return value;
    }

    private static void readNewExcel(File file){
        try {
            InputStream is = new FileInputStream(file);
            // 得到工作簿对象
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            // 得到工作表
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++){
                XSSFRow row = sheet.getRow(i);
                for (int j = 0; j < row.getLastCellNum(); j++){
                    XSSFCell cell = row.getCell(j);
                    String value = getCellValue(cell);
                    System.out.print(value + "\t\t");
                }
                System.out.println("");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            System.out.println("IOException");
            e.printStackTrace();
        }
    }

    private static void readOldExcel(File file){
        try {
            InputStream is = new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            HSSFSheet sheet = workbook.getSheetAt(0);
            System.out.println(sheet);
        }catch (OfficeXmlFileException e){
            System.out.println("使用新版本");
            readNewExcel(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
