package Utils;

import Entites.Product;
import Entites.Transactions;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReportUtils {

    public static void createSalesReportExcel(ArrayList<Transactions> transactions, String fileName) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Sales Report");

        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Transaction ID", "Branch ID", "Cashier ID", "Vendor ID", "Product ID", "Transaction Date", "Quantity", "Transaction Amount", "Transaction Cost"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            cell.setCellStyle(headerStyle);
        }

        // Fill data rows
        int rowIdx = 1;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (Transactions transaction : transactions) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(transaction.getTransactionId());
            row.createCell(1).setCellValue(transaction.getBranchId());
            row.createCell(2).setCellValue(transaction.getCashierId());
            row.createCell(3).setCellValue(transaction.getVendorId());
            row.createCell(4).setCellValue(transaction.getProductId());
            row.createCell(5).setCellValue(dateFormat.format(transaction.getTransactionDate()));
            row.createCell(6).setCellValue(transaction.getQuantity());
            row.createCell(7).setCellValue(transaction.getTransactionAmount());
            row.createCell(8).setCellValue(transaction.getTransactionCost());
        }

        // Autosize columns for better readability
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write to file
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
            System.out.println("Sales report created successfully: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing Excel file: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void createProductsReportExcel(ArrayList<Product> products, String fileName, int branchId) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Branch " + branchId + " Report");
        // Create header row
        Row headerRow = sheet.createRow(0);
        String[] headers = {"Product ID", "Product Name", "Quantity", "Branch ID"};
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            CellStyle headerStyle = workbook.createCellStyle();
            Font font = workbook.createFont();
            font.setBold(true);
            headerStyle.setFont(font);
            cell.setCellStyle(headerStyle);
        }

        // Fill data rows
        int rowIdx = 1;
        for (Product product : products) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(product.productId); // Product ID
            row.createCell(1).setCellValue(product.name); // Product Name
            row.createCell(2).setCellValue(product.stockQuantity); // Quantity
            row.createCell(3).setCellValue(branchId); // Branch ID
        }

        // Autosize columns for better readability
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write to file
        try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
            workbook.write(fileOut);
            System.out.println("Excel report created successfully: " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing Excel file: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
