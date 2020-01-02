package com.anonymous.rightshop.sub_fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.anonymous.rightshop.R;
import com.anonymous.rightshop.activity.OrderActivity;
import com.anonymous.rightshop.adapter.SubCategoryRecyclerViewAdapter;
import com.anonymous.rightshop.db.DatabaseHelper;
import com.anonymous.rightshop.model.CategoryItems;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nex3z.notificationbadge.NotificationBadge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class MenShoes extends Fragment {

    //ArrayList of Everything Class
    private ArrayList<CategoryItems> arrayList;
    private SubCategoryRecyclerViewAdapter adapter;
    private ProgressDialog progressDialog ;
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        //View which will be used in some other places
        final View view = inflater.inflate(R.layout.subfragment_men_shoes, container, false);

        //Recycler View in Fragment
        RecyclerView recyclerView = view.findViewById(R.id.men_shoes_rv);
        adapter = new SubCategoryRecyclerViewAdapter(arrayList,getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);
        EditText searchText = view.findViewById(R.id.search_items);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        return view;
    }


    private void filter(String newText) {
        ArrayList<CategoryItems> filterList= new ArrayList<>();
        for (CategoryItems item : arrayList){
            if(item.getName().toLowerCase().contains(newText.toLowerCase())){

                filterList.add(item);
            }
        }
        adapter.filteredList(filterList);
    }



    private ArrayList<CategoryItems> getItems(){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Data");
        progressDialog.show();
        final ArrayList<CategoryItems> arrayListItems = new ArrayList<>();
        final DatabaseReference myRef=FirebaseDatabase.getInstance().getReference("RightShop").child("Men").child("Men Shoes");

        myRef.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                arrayList.clear();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String uid = ds.getKey();
                    assert uid != null;
                    Log.d("TAG", uid);
                    String ProductName= Objects.requireNonNull(dataSnapshot.child(uid).child("Product Name").getValue()).toString();
                    String Image= Objects.requireNonNull(dataSnapshot.child(uid).child("ImageUrl").getValue()).toString();
                    String NewPrice= Objects.requireNonNull(dataSnapshot.child(uid).child("New Price").getValue()).toString();
                    String OldPrice= Objects.requireNonNull(dataSnapshot.child(uid).child("Old Price").getValue()).toString();
                    String Serial = Objects.requireNonNull(dataSnapshot.child(uid).child("Product Serial").getValue()).toString();
                    String Details = Objects.requireNonNull(dataSnapshot.child(uid).child("Product Details").getValue()).toString();
                    arrayListItems.add(new CategoryItems(uid,Image,ProductName,Details,NewPrice,OldPrice,Serial));
                }
                Collections.reverse(arrayListItems);
                adapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Failed to read value
                Toast.makeText(getContext(), "Failed To Load Data!", Toast.LENGTH_SHORT).show();
            }
        });
        return arrayListItems;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayList = new ArrayList<>();
        arrayList = getItems();

    }



}
