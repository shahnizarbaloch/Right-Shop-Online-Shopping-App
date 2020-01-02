package com.anonymous.rightshop.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.anonymous.rightshop.R;
import com.anonymous.rightshop.model.Category;

import java.util.ArrayList;


public class NotLoggedInMainRVAdapter extends RecyclerView.Adapter<NotLoggedInMainRVAdapter.MyViewHolder>{

    private ArrayList<Category> arrayList;
    private Context context;

    public NotLoggedInMainRVAdapter(ArrayList<Category> arrayList, Context context){
        this.arrayList=arrayList;
        this.context=context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        @SuppressLint("InflateParams") final View view = inflater.inflate(R.layout.design_main_cardview, null, false);

        final MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Login is Must, Sorry!", Toast.LENGTH_SHORT).show();
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




