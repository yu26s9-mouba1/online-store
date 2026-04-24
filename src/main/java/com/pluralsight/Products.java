package com.pluralsight;

public class Products {

    private String productNane;
    private int productSKU;
    private double productPrice;
    private String productDepartment;

    public Products(String productNane, int productSKU, double productPrice, String productDepartment) {
        this.productNane = productNane;
        this.productSKU = productSKU;
        this.productPrice = productPrice;
        this.productDepartment = productDepartment;
    }

    public String getProductNane() {
        return productNane;
    }

    public void setProductNane(String productNane) {
        this.productNane = productNane;
    }

    public int getProductSKU() {
        return productSKU;
    }

    public void setProductSKU(int productSKU) {
        this.productSKU = productSKU;
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

