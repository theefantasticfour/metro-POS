package Entites;

public class Product {
// add the following attributes
    // name , stockQTy,categorie,costbyunit,sellingprice,cartonprice carton qty
    public String name;
    public int productId;
    public int vendorId;
    public int stockQty;
    public String categorie;
    public float costByUnit; // cost of one unit          // profit = selling - cost
    public float sellingPrice; // selling price of one unit
    public float cartonPrice;
    public int cartonQty;
    public int branchId = -1; // by default
    public Product() {
    }
    public Product(String name, int ProductId,int vendorId,int stockQty, String categorie, float costByUnit, float sellingPrice, float cartonPrice, int cartonQty) {
        this.name = name;
        this.productId = ProductId;
        this.vendorId = vendorId;
        this.stockQty = stockQty;
        this.categorie = categorie;
        this.costByUnit = costByUnit;
        this.sellingPrice = sellingPrice;
        this.cartonPrice = cartonPrice;
        this.cartonQty = cartonQty;
    }
    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public void setStockQty(int stockQty) {
        this.stockQty = stockQty;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setCostByUnit(float costByUnit) {
        this.costByUnit = costByUnit;
    }

    public void setSellingPrice(float sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setCartonPrice(float cartonPrice) {
        this.cartonPrice = cartonPrice;
    }

    public void setCartonQty(int cartonQty) {
        this.cartonQty = cartonQty;
    }
}
