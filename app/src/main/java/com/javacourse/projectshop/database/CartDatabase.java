package com.javacourse.projectshop.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.javacourse.projectshop.dao.CartDAO;
import com.javacourse.projectshop.utils.model.ProductCart;



@Database(entities = {ProductCart.class}, version = 1)
public abstract class CartDatabase extends RoomDatabase {

    public abstract CartDAO cartDAO();
    private static CartDatabase instance;

    public static synchronized CartDatabase getInstance(Context context){
        if(instance== null){
            instance= Room.databaseBuilder(context.getApplicationContext()
                        , CartDatabase.class, "ProductDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;

    }
}
