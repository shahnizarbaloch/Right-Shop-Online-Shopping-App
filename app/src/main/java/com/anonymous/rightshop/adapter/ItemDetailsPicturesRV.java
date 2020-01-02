package com.anonymous.rightshop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.anonymous.rightshop.R;
import com.anonymous.rightshop.activity.ItemDetailsActivity;
import com.anonymous.rightshop.model.CategoryItems;
import com.anonymous.rightshop.model.Images;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ItemDetailsPicturesRV extends RecyclerView.Adapter<ItemDetailsPicturesRV.MyViewHolder>{

    private ArrayList<Images> arrayList;
    private Context context;

    public ItemDetailsPicturesRV(ArrayList<Images> arrayList, Context context){
        this.arrayList=arrayList;
        this.context=context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") final View view = inflater.inflate(R.layout.design_items, null, false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        
        MyViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }


}




