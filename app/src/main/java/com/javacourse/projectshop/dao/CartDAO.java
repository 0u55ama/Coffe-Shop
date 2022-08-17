package com.javacourse.projectshop.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.javacourse.projectshop.utils.model.ProductCart;

import java.util.List;

@Dao
public interface CartDAO {

    @Insert
    void insertCartItem(ProductCart productCart);

    @Query("SELECT * FROM product_table")
    LiveData<List<ProductCart>> getAllCartItem();

    @Delete
    void deleteCartItem(ProductCart productCart);

    @Query("UPDATE PRODUCT_TABLE SET quantity=:quantity WHERE id=:id")
    void updateQuantity(int id,int quantity);

    @Query("UPDATE product_table SET totalItemPrice=:totalItemPrice WHERE id=:id")
    void updatePrice(int id, double totalItemPrice);

    @Query("DELETE FROM product_table")
    void deleteAllItem();

}
