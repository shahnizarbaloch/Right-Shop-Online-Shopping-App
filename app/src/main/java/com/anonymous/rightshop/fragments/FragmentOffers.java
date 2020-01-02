package com.anonymous.rightshop.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.anonymous.rightshop.R;

public class FragmentOffers extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_offers, container, false);

        return view;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*arrayList = new ArrayList<>();
        arrayList.add(new Category(R.drawable.sleep_icon_primary,"Sleeping",GOOD));
        arrayList.add(new Category(R.drawable.physical_icon_primary,"Physical Activities",NORMAL));
        arrayList.add(new Category(R.drawable.mobile_usage_icon_primary,"Mobile Usage",BAD));
        arrayList.add(new Category(R.drawable.food_icon_primary,"Food Intake",NORMAL));
        arrayList.add(new Category(R.drawable.weather_icon_primary,"Weather",NOT_CALCULATED));*/
    }
}
