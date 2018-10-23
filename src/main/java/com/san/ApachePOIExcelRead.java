package com.san;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApachePOIExcelRead {

    private static final String FILE_NAME = "/tmp/user.xlsx";

    public static void main(String[] args) {
        processExcelFile(FILE_NAME);
    }

    public static void processExcelFile(String filename) {
        try {

            final FileInputStream excelFile = new FileInputStream(new File(filename));
            final Workbook workbook = new XSSFWorkbook(excelFile);
            final Sheet datatypeSheet = workbook.getSheetAt(0);
            final Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                final Row currentRow = iterator.next();
                final Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {
                    final Cell currentCell = cellIterator.next();
                    if (currentCell.getCellType() == CellType.STRING) {
                        System.out.print("STRING : " + currentCell.getStringCellValue() + "--");
                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
                        System.out.print("NUMERIC : " + currentCell.getNumericCellValue() + "--");
                    }
                }
                System.out.println();

            }
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }
}
