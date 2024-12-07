package Entites;

public class Product {
// add the following attributes
    // name , stockQTy,categorie,costbyunit,sellingprice,cartonprice carton qty
    public String name;
    int productId;
    public int vendorId;
    public int stockQty;
    public String categorie;
    public float costByUnit; // cost of one unit          // profit = selling - cost
    public float sellingPrice; // selling price of one unit
    public float cartonPrice;
    public int cartonQty;
    public int branchId = -1; // by default
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
}