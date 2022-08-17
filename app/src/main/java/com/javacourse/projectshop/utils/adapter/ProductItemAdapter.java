package com.javacourse.projectshop.utils.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.javacourse.projectshop.R;
import com.javacourse.projectshop.utils.model.ProductItem;

import java.util.List;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ProductItemViewHolder> {


    private List<ProductItem> productItemList;
    private ProductClickListeners productClickListeners;

    public ProductItemAdapter(ProductClickListeners productClickListeners){
        this.productClickListeners= productClickListeners;
    }


    public void setProductItemList(List<ProductItem> productItemList){
        this.productItemList= productItemList;


    }


    @NonNull
    @Override
    public ProductItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.each_shoe,parent, false);
        return new ProductItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductItemViewHolder holder, int position) {

        ProductItem productItem= productItemList.get(position);

        holder.productNameTv.setText(productItem.getProductName());
        holder.productBrandNameTv.setText(productItem.getProductBrandName());
        holder.productPriceTv.setText(String.valueOf(productItem.getProductPrice()));
        holder.productImageView.setImageResource(productItem.getProductImage());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productClickListeners.onCardClicked(productItem);
            }
        });

        holder.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productClickListeners.onaddToCartBtnClicked(productItem);

            }
        });

    }


    @Override
    public int getItemCount() {
        if (productItemList== null){
            return 0;
        }else {
            return productItemList.size();
        }
    }

    public class ProductItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView productImageView, addToCartBtn;
        private TextView productNameTv, productBrandNameTv, productPriceTv;
        private CardView cardView;
        public ProductItemViewHolder(@NonNull View itemView){

            super((itemView));

            cardView= itemView.findViewById(R.id.eachShoeCardView);
            addToCartBtn= itemView.findViewById(R.id.eachProductAddToCartBtn);
            productNameTv= itemView.findViewById(R.id.eachProductName);
            productImageView= itemView.findViewById(R.id.eachProductIv);
            productBrandNameTv= itemView.findViewById(R.id.eachProductBrandNameTv);
            productPriceTv= itemView.findViewById(R.id.eachProductPriceTv);

        }
    }

    public interface ProductClickListeners{
        void onCardClicked(ProductItem product);
        void onaddToCartBtnClicked(ProductItem productItem);
    }
}
