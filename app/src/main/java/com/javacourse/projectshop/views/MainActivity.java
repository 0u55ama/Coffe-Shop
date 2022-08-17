package com.javacourse.projectshop.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.javacourse.projectshop.R;
import com.javacourse.projectshop.utils.adapter.ProductItemAdapter;
import com.javacourse.projectshop.utils.model.ProductCart;
import com.javacourse.projectshop.utils.model.ProductItem;
import com.javacourse.projectshop.viewmodel.CartViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ProductItemAdapter.ProductClickListeners {
    
    private RecyclerView recyclerView;
    private List<ProductItem> productItemList;
    private ProductItemAdapter adapter;
    private CartViewModel viewModel;
    private List<ProductCart> productCartList;
    private CoordinatorLayout coordinatorLayout;
    private ImageView cartImageView;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        inistializeVariables();
        setUpList();


        adapter.setProductItemList(productItemList);
        recyclerView.setAdapter(adapter);

        cartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, CartActivity.class));

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        viewModel.getAllCartItems().observe(this, new Observer<List<ProductCart>>() {
            @Override
            public void onChanged(List<ProductCart> productCarts) {
                productCartList.addAll(productCarts);
            }
        });

    }

    private void setUpList(){
        productItemList.add(new ProductItem("Cafe Worthy Mug", "OSM", R.drawable.cafe_worthy_mugs, 5 ));
        productItemList.add(new ProductItem("Automatic Burr Mill", "OSM", R.drawable.cuisinart_automatic_burr_mil, 40));
        productItemList.add(new ProductItem("Drip Coffee Maker", "OSM", R.drawable.drip_coffeecmaker, 50 ));
        productItemList.add(new ProductItem("Espresso Maker", "OSM", R.drawable.espresso_maker, 90 ));
        productItemList.add(new ProductItem("Flavored Syrups", "OSM", R.drawable.flavored_syrups, 8 ));
        productItemList.add(new ProductItem("Milk Frother", "OSM", R.drawable.milk_frother, 10 ));
        productItemList.add(new ProductItem("Whole Bean Coffee", "OSM", R.drawable.whole_bean_coffee, 45 ));

    }

    private void inistializeVariables() {

        cartImageView= findViewById(R.id.cartIv);

        coordinatorLayout= findViewById(R.id.coordinatorLayout);

        productCartList= new ArrayList<>();

        viewModel=  new ViewModelProvider(this).get(CartViewModel.class);

        productItemList= new ArrayList<>();
        recyclerView= findViewById(R.id.mainRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        adapter= new ProductItemAdapter(this);

    }

    @Override
    public void onCardClicked(ProductItem product) {

        Intent intent= new Intent(MainActivity.this, DetailedActivity.class);
        intent.putExtra("productItem", product);
        startActivity(intent);

    }

    @Override
    public void onaddToCartBtnClicked(ProductItem productItem) {

        ProductCart productCart= new ProductCart();
        productCart.setProductName(productItem.getProductName());
        productCart.setProductBrandName(productItem.getProductBrandName());
        productCart.setProductPrice(productItem.getProductPrice());
        productCart.setProductImage(productItem.getProductImage());


        final int[] quantity= {1};
        final int[] id= new int[1];

        if(! productCartList.isEmpty()){
            for (int i=0; i<productCartList.size(); i++){
                if (productCart.getProductName().equals(productCartList.get(i).getProductName())){
                    quantity[0]= productCartList.get(i).getId();
                    quantity[0]++;
                    id[0]= productCartList.get(i).getId();

                }
            }

        }

        if (quantity[0]==1){
            productCart.setQuantity(quantity[0]);
            productCart.setTotalItemPrice(quantity[0]*productCart.getProductPrice());
            viewModel.insertCartItem(productCart);

        }else{

            viewModel.updateQuantity(id[0], quantity[0]);
            viewModel.updatePrice(id[0], quantity[0]*productCart.getProductPrice());


        }

        makeSnackBar("Item Added To Cart");

    }

    private void makeSnackBar(String msg){

        Snackbar.make(coordinatorLayout, msg, Snackbar.LENGTH_SHORT)
                .setAction("Go to Cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        startActivity(new Intent(MainActivity.this, CartActivity.class));

                    }
                }).show();


    }
}