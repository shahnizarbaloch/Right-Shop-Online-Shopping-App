package com.anonymous.rightshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.anonymous.rightshop.R;
import com.anonymous.rightshop.adapter.CategoryRecyclerViewAdapter;
import com.anonymous.rightshop.adapter.MainRecyclerViewAdapter;
import com.anonymous.rightshop.model.Category;

import java.util.ArrayList;

public class FragmentCategories extends Fragment {
    private ArrayList<Category> arrayList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        //Recycler View in Fragment
        RecyclerView recyclerView = view.findViewById(R.id.categories_rv);
        MainRecyclerViewAdapter adapter = new MainRecyclerViewAdapter(arrayList,getContext());
        //recyclerView.setLayoutManager(new SpeedyLinearLayoutManager(getContext(), SpeedyLinearLayoutManager.H, false));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList = new ArrayList<>();
        //Adding Things Here.. Means when we patch data from firebase then we can add them here ;)
        arrayList.add(new Category(R.drawable.men_category, "Men"));
        arrayList.add(new Category(R.drawable.women_category, "Women"));
        arrayList.add(new Category(R.drawable.kid_category, "Kid"));
        arrayList.add(new Category(R.drawable.other_category_new, "Other"));
    }
}
