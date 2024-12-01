package Views.SuperAdminview;

import java.awt.event.ActionListener;

public class SuperAdminReportsGraphspanel {
   ActionListener superAdminListener;
    // this listner is for the 3 download buttons
    // download button ki action command will be Values.SALES_REPORT
    // download button ki action command will be Values.STOCK_REPORT
    // download button ki action command will be Values.PROFIT_REPORT
    // --------- for graphs -----------
    // to get the data for the graph
    // all of their 3rd parameter will be false because we are not downloading them
    // for sales report
    // ArrayList<Transaction> transactions = SuperAdminController.downloadSalesReport();
    // for product report
    // ArrayList<Product> products = SuperAdminController.downloadProductReport();
    // will get products that you would then have to manipulate to show in the graph
    // its class contain the attribute to maanipulate
    // for profit report
    // Float profit = SuperAdminController.downloadProfitReport();
    // you will get Arraylist of class transaction That you would then have to manipulate to show in the graph


}
