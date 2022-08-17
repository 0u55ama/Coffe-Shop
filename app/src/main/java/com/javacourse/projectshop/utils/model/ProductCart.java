package com.javacourse.projectshop.utils.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "product_table")
public class ProductCart {


    @PrimaryKey(autoGenerate = true)
    private int id;


    private String productName, productBrandName;
    private int productImage;
    private double productPrice;


    private int quantity;
    private double totalItemPrice;



    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrandName() {
        return productBrandName;
    }

    public void setProductBrandName(String productBrandName) {
        this.productBrandName = productBrandName;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(double totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }
}
