package com.anonymous.rightshop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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
import com.anonymous.rightshop.activity.ItemDetailsActivity;
import com.anonymous.rightshop.model.CategoryItems;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class SpecialItemRecyclerViewAdapter extends RecyclerView.Adapter<SpecialItemRecyclerViewAdapter.MyViewHolder>{

    private ArrayList<CategoryItems> arrayList;
    private Context context;

    public SpecialItemRecyclerViewAdapter(ArrayList<CategoryItems> arrayList, Context context){
        this.arrayList=arrayList;
        this.context=context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") final View view = inflater.inflate(R.layout.design_special_items, null, false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.item_old_price.setPaintFlags(myViewHolder.item_old_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemDetailsActivity.class);
                final CategoryItems obj = arrayList.get(myViewHolder.getAdapterPosition());
                intent.putExtra("Name",obj.getName());
                intent.putExtra("Price",obj.getOldPrice());
                intent.putExtra("Image",obj.getImage());
                context.startActivity(intent);
            }
        });
        return myViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        CategoryItems obj = arrayList.get(i);
        myViewHolder.item_category_name.setText(obj.getName());
        Picasso.get().load(obj.getImage()).centerInside().fit().into(myViewHolder.imageView);
        myViewHolder.item_new_price.setText("PKR "+obj.getOldPrice());
        myViewHolder.item_old_price.setText("PKR "+obj.getNewPrice());
        if(obj.getNewPrice().isEmpty()){
            myViewHolder.item_old_price.setVisibility(View.GONE);
        }
        else {
            myViewHolder.item_old_price.setVisibility(View.VISIBLE);
        }



    }

    public void filteredList(ArrayList<CategoryItems> filterList) {

        arrayList=filterList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout itemSubCategory;
        CardView cardView;
        TextView item_category_name,item_old_price,item_new_price;
        RecyclerView rv;
        ImageView imageView;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemSubCategory = itemView.findViewById(R.id.category_layout);
            cardView = itemView.findViewById(R.id.cardview_special_screen);
            item_category_name = itemView.findViewById(R.id.category_name);
            imageView = itemView.findViewById(R.id.category_image);
            item_old_price = itemView.findViewById(R.id.old_price);
            item_new_price = itemView.findViewById(R.id.new_price);
            rv = itemView.findViewById(R.id.men_rv);
        }
    }


}




