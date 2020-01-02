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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.anonymous.rightshop.R;
import com.anonymous.rightshop.activity.CategoriesActivity;
import com.anonymous.rightshop.model.Category;
import java.util.ArrayList;


public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MyViewHolder>{

    private ArrayList<Category> arrayList;
    private Context context;

    public MainRecyclerViewAdapter(ArrayList<Category> arrayList, Context context){
        this.arrayList=arrayList;
        this.context=context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") final View view = inflater.inflate(R.layout.design_main_cardview, null, false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category obj = arrayList.get(myViewHolder.getAdapterPosition());
                String subCategories = obj.getName();
                Intent intent = new Intent(context,CategoriesActivity.class);
                switch (subCategories){
                    case "Men":
                        intent.putExtra("fragment","Men");
                        context.startActivity(intent);
                        break;
                    case "Women":
                        intent.putExtra("fragment","Women");
                        context.startActivity(intent);
                        break;
                    case "Kid":
                        intent.putExtra("fragment","Kid");
                        context.startActivity(intent);
                        break;
                    case "Other":
                        intent.putExtra("fragment","Other");
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
        CardView cardView;
        TextView item_category_name;
        RecyclerView rv;
        ImageView imageView;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemCategory = itemView.findViewById(R.id.category_layout);
            cardView = itemView.findViewById(R.id.cardview_main_screen);
            item_category_name = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.image);
            rv = itemView.findViewById(R.id.home_rv);

        }
    }

}




