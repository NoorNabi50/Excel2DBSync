package org.models;
import org.apache.poi.hpsf.Decimal;


public class Product {

    private  String SkuNumber ;

    private float Price;

    private  int StockQty;

    public String getSkuNumber() {
        return SkuNumber;
    }

    public void setSkuNumber(String skuNumber) {
        SkuNumber = skuNumber;
    }

    public float getPrice() {
        return Price;
    }

    public void setPrice(float price) {
        Price = price;
    }

    public int getStockQty() {
        return StockQty;
    }

    public void setStockQty(int stockQty) {
        StockQty = stockQty;
    }
}
