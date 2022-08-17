package com.javacourse.projectshop.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.javacourse.projectshop.dao.CartDAO;
import com.javacourse.projectshop.database.CartDatabase;
import com.javacourse.projectshop.utils.model.ProductCart;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class CartRepo {


    private CartDAO cartDAO;
    private LiveData<List<ProductCart>> allCartItemLiveData;
    private Executor executor= Executors.newSingleThreadExecutor();


    public LiveData<List<ProductCart>> getAllCartItemLiveData() {
        return allCartItemLiveData;
    }

    public CartRepo(Application application){

        cartDAO= CartDatabase.getInstance(application).cartDAO();
        allCartItemLiveData= cartDAO.getAllCartItem();

    }

    public void insertCartItem(ProductCart productCart){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.insertCartItem(productCart);
            }
        });
    }

    public void deleteCartItem(ProductCart productCart){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.deleteCartItem(productCart);
            }
        });
    }

    public void updateQuantity(int id, int quantity){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.updateQuantity(id, quantity);
            }
        });
    }

    public void updatePrice(int id, double price){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.updatePrice(id, price);
            }
        });
    }

    public void deleteAllItem(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cartDAO.deleteAllItem();
            }
        });
    }
}
