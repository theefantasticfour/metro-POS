package Models;

import Entites.Product;

import java.util.ArrayList;
import java.util.Map;
// NOTE : A single transaction wwill always have qty 1 no more than that
//
public class Cashier {
    String username;
    String password;
    int BranchID;
    public Cashier(String username, String password) {
        this.username = username;
        this.password = password;
        setBranchID();
    }
    public void setBranchID() {
        // set the branch id of the cashier after looking up the cashier
    }

    public ArrayList<Product> getAllBranchProducts() {
        // search by productid+branchid where StockQty > 0
        return null;
    }
    public void RecordTransactions(Map<String, Integer> cartDetails) {
        // we have the cart details
        // we have the product id but in String as key
        // we have the quantity as value
        // if (Quantity > 1)
        // record n Transactions
        // first of all
        // search by productid+branchid
        for (Map.Entry<String, Integer> entry : cartDetails.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            // search by productid+branchid (Key + BranchID) and get the product
            // register a transaction as now you will have everything
            // to register a transaction
            // from product attributes ect
           // recordATransaction(Product);
            // if (value > 1)
                // for (int i = 0; i < value; i++)
                //recordATransaction(Product);

            // record n Transactions
        }

        // update the stock
        // update the transaction table

    }
    public void recordATransaction(Product product) {
        // register a transaction
        // you have the product
        // you have the branch id
        // you have the cashier id if needed

    }

    public void RecordTransaction(Map<String, Integer> cartDetails) {

    }
}
