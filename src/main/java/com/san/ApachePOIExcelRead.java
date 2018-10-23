package com.san;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApachePOIExcelRead {

    private static final String FILE_NAME = "/tmp/user.xlsx";

    public static void main(String[] args) {
        // processExcelFile(FILE_NAME);
        final List<Map<String, Object>> list = getProcessedExcelFileMap(FILE_NAME);
        System.out.println("ApachePOIExcelRead.main() : " + list);
        final List<List<Object>> listRecords = getProcessedExcelFileList(FILE_NAME);
        System.out.println("ApachePOIExcelRead.main() : " + listRecords);
    }

    public static void processExcelFile(String filename) {

        try (final FileInputStream excelFile = new FileInputStream(new File(filename)); final Workbook workbook = new XSSFWorkbook(excelFile)) {

            final Sheet datatypeSheet = workbook.getSheetAt(0);
            final Iterator<Row> iterator = datatypeSheet.iterator();

            while (iterator.hasNext()) {

                final Row currentRow = iterator.next();
                final Iterator<Cell> cellIterator = currentRow.iterator();

                while (cellIterator.hasNext()) {
                    final Cell currentCell = cellIterator.next();
                    if (currentCell.getCellType() == CellType.STRING) {
                        final String stringCellValue = currentCell.getStringCellValue();

                        System.out.print("STRING : " + stringCellValue + "--");
                    } else if (currentCell.getCellType() == CellType.NUMERIC) {
                        final double numericCellValue = currentCell.getNumericCellValue();
                        System.out.print("NUMERIC : " + numericCellValue + "--");
                    }
                }
                System.out.println();

            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

    }

    public static List<List<Object>> getProcessedExcelFileList(String filename) {

        final List<List<Object>> dictionary = new ArrayList<>();
        final List<String> columns = new ArrayList<>();
        try (final FileInputStream excelFile = new FileInputStream(new File(filename)); final Workbook workbook = new XSSFWorkbook(excelFile)) {

            final Sheet datatypeSheet = workbook.getSheetAt(0);
            final int physicalNumberOfRows = datatypeSheet.getPhysicalNumberOfRows();
            final Row row = datatypeSheet.getRow(0);
            final Iterator<Cell> iterator2 = row.iterator();
            while (iterator2.hasNext()) {
                final Cell currentCell = iterator2.next();
                columns.add(currentCell.getStringCellValue());

            }
            System.out.println("ApachePOIExcelRead.getProcessedExcelFile() " + columns);

            for (int i = 1; i < physicalNumberOfRows; i++) {
                final Row currentRow = datatypeSheet.getRow(i);

                final List<Object> listRow = getRowList(columns, currentRow);
                dictionary.add(listRow);
                System.out.println();

            }
            System.out.println("ApachePOIExcelRead.getProcessedExcelFile() dictionary : " + dictionary);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return dictionary;

    }

    private static List<Object> getRowList(final List<String> columns, final Row currentRow) {
        final List<Object> listRow = new ArrayList<>();
        final Iterator<Cell> cellIterator = currentRow.iterator();
        while (cellIterator.hasNext()) {
            final Cell currentCell = cellIterator.next();
            if (currentCell.getCellType() == CellType.STRING) {
                final String stringCellValue = currentCell.getStringCellValue();
                listRow.add(stringCellValue);
                // System.out.print("STRING : " + stringCellValue + "--");
            } else if (currentCell.getCellType() == CellType.NUMERIC) {
                final double numericCellValue = currentCell.getNumericCellValue();
                listRow.add(numericCellValue);
                // System.out.print("NUMERIC : " + numericCellValue + "--");
            }
        }
        return listRow;
    }

    public static List<Map<String, Object>> getProcessedExcelFileMap(String filename) {

        final List<Map<String, Object>> dictionary = new ArrayList<>();
        final List<String> columns = new ArrayList<>();
        try (final FileInputStream excelFile = new FileInputStream(new File(filename)); final Workbook workbook = new XSSFWorkbook(excelFile)) {

            final Sheet datatypeSheet = workbook.getSheetAt(0);
            final int physicalNumberOfRows = datatypeSheet.getPhysicalNumberOfRows();
            final Row row = datatypeSheet.getRow(0);
            final Iterator<Cell> iterator2 = row.iterator();
            while (iterator2.hasNext()) {
                final Cell currentCell = iterator2.next();
                columns.add(currentCell.getStringCellValue());

            }
            System.out.println("ApachePOIExcelRead.getProcessedExcelFile() " + columns);

            for (int i = 1; i < physicalNumberOfRows; i++) {
                final Row currentRow = datatypeSheet.getRow(i);

                final Map<String, Object> mapRow = getRowMap(columns, currentRow);
                dictionary.add(mapRow);
                System.out.println();

            }
            System.out.println("ApachePOIExcelRead.getProcessedExcelFile() dictionary : " + dictionary);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return dictionary;

    }

    private static Map<String, Object> getRowMap(final List<String> columns, final Row currentRow) {
        int columnCount = 0;
        final Map<String, Object> mapRow = new HashMap<>();
        final Iterator<Cell> cellIterator = currentRow.iterator();
        while (cellIterator.hasNext()) {
            final Cell currentCell = cellIterator.next();
            if (currentCell.getCellType() == CellType.STRING) {
                final String stringCellValue = currentCell.getStringCellValue();
                mapRow.put(columns.get(columnCount), stringCellValue);
                // System.out.print("STRING : " + stringCellValue + "--");
            } else if (currentCell.getCellType() == CellType.NUMERIC) {
                final double numericCellValue = currentCell.getNumericCellValue();
                mapRow.put(columns.get(columnCount), numericCellValue);
                // System.out.print("NUMERIC : " + numericCellValue + "--");
            }
            columnCount++;
        }
        return mapRow;
    }
}
