package Entites;

public class Product {
// add the following attributes
    // name , stockQTy,categorie,costbyunit,sellingprice,cartonprice carton qty
    String name;
    int stockQty;
    String categorie;
    float costByUnit;
    float sellingPrice;
    float cartonPrice;
    int cartonQty;
    int branchId = -1; // by default
    public Product(String name, int stockQty, String categorie, float costByUnit, float sellingPrice, float cartonPrice, int cartonQty) {
        this.name = name;
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
