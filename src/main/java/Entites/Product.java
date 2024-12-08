package Entites;

public class Product {
    // Attributes according to the Product table schema
    public int productId;
    public int vendorId;
    public int branchId;
    public String name;
    public String category;
    public float originalPricePerUnit;
    public float salePricePerUnit;
    public int stockQuantity;
    public float originalPricePerCarton;
    public float salePricePerCarton;
    public int cartonQuantity;
    public int piecesPerCarton;

    // Default constructor
    public Product() {
    }

    // Constructor to initialize the product
    public Product(int productId, int vendorId, String name, String category,
                   float originalPricePerUnit, float salePricePerUnit, int stockQuantity,
                   float originalPricePerCarton, float salePricePerCarton, int cartonQuantity,
                   int piecesPerCarton) {
        this.productId = productId;
        this.vendorId = vendorId;
        this.branchId = branchId;
        this.name = name;
        this.category = category;
        this.originalPricePerUnit = originalPricePerUnit;
        this.salePricePerUnit = salePricePerUnit;
        this.stockQuantity = stockQuantity;
        this.originalPricePerCarton = originalPricePerCarton;
        this.salePricePerCarton = salePricePerCarton;
        this.cartonQuantity = cartonQuantity;
        this.piecesPerCarton = piecesPerCarton;
    }

    // Setters and getters for each attribute
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setOriginalPricePerUnit(float originalPricePerUnit) {
        this.originalPricePerUnit = originalPricePerUnit;
    }

    public void setSalePricePerUnit(float salePricePerUnit) {
        this.salePricePerUnit = salePricePerUnit;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setOriginalPricePerCarton(float originalPricePerCarton) {
        this.originalPricePerCarton = originalPricePerCarton;
    }

    public void setSalePricePerCarton(float salePricePerCarton) {
        this.salePricePerCarton = salePricePerCarton;
    }

    public void setCartonQuantity(int cartonQuantity) {
        this.cartonQuantity = cartonQuantity;
    }

    public void setPiecesPerCarton(int piecesPerCarton) {
        this.piecesPerCarton = piecesPerCarton;
    }

    // Getters for each attribute
    public int getProductId() {
        return productId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public int getBranchId() {
        return branchId;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public float getOriginalPricePerUnit() {
        return originalPricePerUnit;
    }

    public float getSalePricePerUnit() {
        return salePricePerUnit;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public float getOriginalPricePerCarton() {
        return originalPricePerCarton;
    }

    public float getSalePricePerCarton() {
        return salePricePerCarton;
    }

    public int getCartonQuantity() {
        return cartonQuantity;
    }

    public int getPiecesPerCarton() {
        return piecesPerCarton;
    }
}
