package com.pluralsight;

public class Products {

    private String productName;
    private String productSku;
    private double productPrice;
    private String productDepartment;

    public Products(String productName, String productSku, double productPrice, String productDepartment) {
        this.productName = productName;
        this.productSku = productSku;
        this.productPrice = productPrice;
        this.productDepartment = productDepartment;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSku() {
        return productSku;
    }

    public void setProductSku(String productSku) {
        this.productSku = productSku;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDepartment() {
        return productDepartment;
    }

    public void setProductDepartment(String productDepartment) {
        this.productDepartment = productDepartment;
    }
}

//public void checkOut(double Price){
//    this.CheckOut = true;
//    this.subTotal =
//
//
//}
