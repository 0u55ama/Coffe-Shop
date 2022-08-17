package com.javacourse.projectshop.utils.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductItem implements Parcelable {


    private String productName, productBrandName;
    private int productImage;
    private double productPrice;

    public ProductItem(String productName, String productBrandName, int productImage, double productPrice) {
        this.productName = productName;
        this.productBrandName = productBrandName;
        this.productImage = productImage;
        this.productPrice = productPrice;
    }

    protected ProductItem(Parcel in) {
        productName = in.readString();
        productBrandName = in.readString();
        productImage = in.readInt();
        productPrice = in.readDouble();
    }

    public static final Creator<ProductItem> CREATOR = new Creator<ProductItem>() {
        @Override
        public ProductItem createFromParcel(Parcel in) {
            return new ProductItem(in);
        }

        @Override
        public ProductItem[] newArray(int size) {
            return new ProductItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(productName);
        parcel.writeString(productBrandName);
        parcel.writeInt(productImage);
        parcel.writeDouble(productPrice);
    }
}
