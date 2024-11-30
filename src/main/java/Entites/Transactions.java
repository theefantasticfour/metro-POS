package Entites;

import java.util.Date;

public class Transactions {
    int transactionId;
    int branchId;
    int cashierId;
    int productId;
    Date transactionDate;
    float transactionAmount;
    String transactionStatus;

    public int getTransactionId() {
        return transactionId;
    }

    public int getBranchId() {
        return branchId;
    }

    public int getCashierId() {
        return cashierId;
    }

    public int getProductId() {
        return productId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }
}
