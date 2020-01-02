package com.anonymous.rightshop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.anonymous.rightshop.R;
import com.anonymous.rightshop.activity.SubCategoriesActivity;
import com.anonymous.rightshop.model.Category;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.MyViewHolder>{

    private ArrayList<Category> arrayList;
    private Context context;

    public CategoryRecyclerViewAdapter(ArrayList<Category> arrayList, Context context){
        this.arrayList=arrayList;
        this.context=context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") final View view = inflater.inflate(R.layout.design_category, null, false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.itemCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category obj = arrayList.get(myViewHolder.getAdapterPosition());
                String subCategories = obj.getName();
                final Intent intent = new Intent(context, SubCategoriesActivity.class);

                switch (subCategories){
                    case "Men Perfumes":
                       intent.putExtra("sub_fragment","Men Perfumes");
                        context.startActivity(intent);
                        break;
                    case "Jeans Shirts":
                        intent.putExtra("sub_fragment","Jeans Shirts");
                        context.startActivity(intent);
                        break;
                    case "Sports Shirts":
                        intent.putExtra("sub_fragment","Sports Shirts");
                        context.startActivity(intent);
                        break;
                    case "Men Shoes":
                        intent.putExtra("sub_fragment","Men Shoes");
                        context.startActivity(intent);
                        break;
                    case "Men Watches":
                        intent.putExtra("sub_fragment","Men Watches");
                        context.startActivity(intent);
                        break;
                    case "Wallets and Bags":
                        intent.putExtra("sub_fragment","Wallets and Bags");
                        context.startActivity(intent);
                        break;
                    case "T-Shirts and Jackets":
                        intent.putExtra("sub_fragment","T-Shirts and Jackets");
                        context.startActivity(intent);
                        break;
                    case "Other Men Items":
                        intent.putExtra("sub_fragment","Other Men Items");
                        context.startActivity(intent);
                        break;
                    case "Other Women Items":
                        intent.putExtra("sub_fragment","Other Women Items");
                        context.startActivity(intent);
                        break;
                    case "Women Perfumes":
                        intent.putExtra("sub_fragment","Women Perfumes");
                        context.startActivity(intent);
                        break;
                    case "Cosmetics":
                        intent.putExtra("sub_fragment","Cosmetics");
                        context.startActivity(intent);
                        break;
                    case "Dresses":
                        intent.putExtra("sub_fragment","Dresses");
                        context.startActivity(intent);
                        break;
                    case "Jewellery":
                        intent.putExtra("sub_fragment","Jewellery");
                        context.startActivity(intent);
                        break;
                    case "Lady Bags":
                        intent.putExtra("sub_fragment","Lady Bags");
                        context.startActivity(intent);
                        break;
                    case "Women Shoes":
                        intent.putExtra("sub_fragment","Women Shoes");
                        context.startActivity(intent);
                        break;
                    case "Women Watches":
                        intent.putExtra("sub_fragment","Women Watches");
                        context.startActivity(intent);
                        break;
                    case "Kid Clothes":
                        intent.putExtra("sub_fragment","Kid Clothes");
                        context.startActivity(intent);
                        break;
                    case "Kid Shoes":
                        intent.putExtra("sub_fragment","Kid Shoes");
                        context.startActivity(intent);
                        break;
                    case "Home Accessories":
                        intent.putExtra("sub_fragment","Home Accessories");
                        context.startActivity(intent);
                        break;
                    case "Home Decorations":
                        intent.putExtra("sub_fragment","Home Decorations");
                        context.startActivity(intent);
                        break;
                    case "Mobile Accessories":
                        intent.putExtra("sub_fragment","Mobile Accessories");
                        context.startActivity(intent);
                        break;

                }
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Category obj = arrayList.get(i);
        myViewHolder.item_category_name.setText(obj.getName());
        myViewHolder.imageView.setImageResource(obj.getImage());


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout itemCategory;
        TextView item_category_name;
        RecyclerView rv;
        ImageView imageView;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCategory = itemView.findViewById(R.id.category_layout);
            item_category_name = itemView.findViewById(R.id.category_name);
            imageView = itemView.findViewById(R.id.category_image);
            rv = itemView.findViewById(R.id.men_rv);

        }
    }

}




