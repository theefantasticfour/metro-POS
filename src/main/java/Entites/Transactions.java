package Entites;

import java.util.Date;

public class Transactions {
    int transactionId;
    int branchId;
    int cashierId;
    int vendorId;
    int productId;
    Date transactionDate;
    int quantity = 1;
    float transactionAmount ; // selling price of the product
    float transactionCost ; // cost price of the product
    public Transactions()
    {

    }
    public Transactions(int transactionId, int branchId, int cashierId, int vendorId, int productId, Date transactionDate, float transactionAmount, float transactionCost) {
        this.transactionId = transactionId;
        this.branchId = branchId;
        this.cashierId = cashierId;
        this.vendorId = vendorId;
        this.productId = productId;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.transactionCost = transactionCost;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getBranchId() {
        return branchId;
    }

    public int getCashierId() {
        return cashierId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public int getProductId() {
        return productId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public float getTransactionCost() {
        return transactionCost;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public void setCashierId(int cashierId) {
        this.cashierId = cashierId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTransactionAmount(float transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setTransactionCost(float transactionCost) {
        this.transactionCost = transactionCost;
    }
}
