package Utils;

import Entites.Product;
import Entites.Transactions;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ReportUtils {

    public static void createSalesReportExcel(ArrayList<Transactions> sales, String fileName) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Sales Report");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Transaction ID", "Branch ID", "Cashier ID", "Product ID", "Transaction Date", "Transaction Amount"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Populate data rows
            for (int i = 0; i < sales.size(); i++) {
                Transactions transaction = sales.get(i);
                Row row = sheet.createRow(i + 1); // Start at row 1 (0-based index)

                row.createCell(0).setCellValue(transaction.getTransactionId());
                row.createCell(1).setCellValue(transaction.getBranchId());
                row.createCell(2).setCellValue(transaction.getCashierId());
                row.createCell(3).setCellValue(transaction.getProductId());
                row.createCell(4).setCellValue(transaction.getTransactionDate().toString()); // Format date if needed
                row.createCell(5).setCellValue(transaction.getTransactionAmount());
            }

            // Write to file
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
                System.out.println("Sales report created: " + fileName);
                JOptionPane.showMessageDialog(null, "Sales report created: " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void createProductsReportExcel(ArrayList<Product> products, String fileName) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Products Report");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"Name", "Stock Quantity", "Category", "Cost By Unit", "Selling Price", "Carton Price", "Carton Quantity", "Branch ID"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Populate data rows
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);
                Row row = sheet.createRow(i + 1); // Start at row 1 (0-based index)

                row.createCell(0).setCellValue(product.name);
                row.createCell(1).setCellValue(product.stockQty);
                row.createCell(2).setCellValue(product.categorie);
                row.createCell(3).setCellValue(product.costByUnit);
                row.createCell(4).setCellValue(product.sellingPrice);
                row.createCell(5).setCellValue(product.cartonPrice);
                row.createCell(6).setCellValue(product.cartonQty);
                row.createCell(7).setCellValue(product.branchId); // if Its all branch product report than branch id will be -1
            }

            // Write to file
            try (FileOutputStream fileOut = new FileOutputStream(fileName)) {
                workbook.write(fileOut);
                System.out.println("Products report created: " + fileName);
                JOptionPane.showMessageDialog(null, "Products report created: " + fileName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
