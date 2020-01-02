package com.anonymous.rightshop.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.anonymous.rightshop.R;
import com.anonymous.rightshop.activity.OrderActivity;
import com.anonymous.rightshop.adapter.CategoryRecyclerViewAdapter;
import com.anonymous.rightshop.db.DatabaseHelper;
import com.anonymous.rightshop.model.Category;
import com.nex3z.notificationbadge.NotificationBadge;
import java.util.ArrayList;
import java.util.Objects;

public class MenFragment extends Fragment {

    //ArrayList of Everything Class
    private ArrayList<Category> arrayList;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View which will be used in some other places
        final View view = inflater.inflate(R.layout.fragment_men, container, false);
        //Recycler View in Fragment
        RecyclerView recyclerView = view.findViewById(R.id.men_rv);
        CategoryRecyclerViewAdapter adapter = new CategoryRecyclerViewAdapter(arrayList,getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);

        return view;
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList = new ArrayList<>();

        //Adding Things Here.. Means when we patch data from firebase then we can add them here ;)
        arrayList.add(new Category(R.drawable.men_cat_perfume, "Men Perfumes"));
        arrayList.add(new Category(R.drawable.jeans_shirt, "Jeans Shirts"));
        arrayList.add(new Category(R.drawable.jackets, "T-Shirts and Jackets"));
        arrayList.add(new Category(R.drawable.sport_shirts, "Sports Shirts"));
        arrayList.add(new Category(R.drawable.men_shoes, "Men Shoes"));
        arrayList.add(new Category(R.drawable.men_watch, "Men Watches"));
        arrayList.add(new Category(R.drawable.wallet, "Wallets and Bags"));
        arrayList.add(new Category(R.drawable.underwear, "Other Men Items"));
    }



}
