package com.anonymous.rightshop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anonymous.rightshop.R;
import com.anonymous.rightshop.activity.OrderActivity;
import com.anonymous.rightshop.db.DatabaseHelper;
import com.anonymous.rightshop.model.CartItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class OrderItemsRecyclerViewAdapter extends RecyclerView.Adapter<OrderItemsRecyclerViewAdapter.MyViewHolder>{

    private ArrayList<CartItem> arrayList;
    private Context context;
    private DatabaseHelper db;
    public OrderItemsRecyclerViewAdapter(ArrayList<CartItem> arrayList, Context context){
        this.arrayList=arrayList;
        this.context=context;
        db = new DatabaseHelper(context);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") final View view = inflater.inflate(R.layout.design_order_items, null, false);

        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, @SuppressLint("RecyclerView") final int position) {
        CartItem obj = arrayList.get(position);
        myViewHolder.itemName.setText(obj.getName());
        myViewHolder.itemPrice.setText("Rs." + obj.getOldPrice());
        Picasso.get().load(obj.getImage()).centerInside().fit().into(myViewHolder.imageView);
        myViewHolder.itemRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData(position);
                OrderActivity.Total.setText(String.valueOf(db.addAllValues()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout itemList;
        TextView itemName,itemPrice,itemRemove;
        ImageView imageView;
        RecyclerView rv;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemList=itemView.findViewById(R.id.item_rv);
            itemName=itemView.findViewById(R.id.tv_item_name);
            itemPrice=itemView.findViewById(R.id.tv_item_price);
            imageView = itemView.findViewById(R.id.image);
            itemRemove=itemView.findViewById(R.id.tv_item_remove);
            rv = itemView.findViewById(R.id.rv_order_categories);
        }
    }



    @SuppressLint("SetTextI18n")
    private void deleteData(int position) {
        CartItem n = arrayList.get(position);
        long check = db.delete(n.getId());
        if (check > 0) {
            //Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
            arrayList.remove(position);
            notifyDataSetChanged();
        } else {
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }
    }

}




