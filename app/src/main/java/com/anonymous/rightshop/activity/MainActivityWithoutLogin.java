package com.anonymous.rightshop.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.anonymous.rightshop.R;
import com.anonymous.rightshop.adapter.NotLoggedInMainRVAdapter;
import com.anonymous.rightshop.model.Category;

import java.util.ArrayList;

public class MainActivityWithoutLogin extends AppCompatActivity{

    ArrayList<Category> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_withoutlogin);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
        initialize();
    }

    private void initialize(){

        Toolbar toolbar = findViewById(R.id.Toolbar);
        toolbar.setTitle("Right Shop (Not Logged In)");
        setSupportActionBar(toolbar);

        arrayList = new ArrayList<>();
        arrayList.add(new Category(R.drawable.men_category,"Men"));
        arrayList.add(new Category(R.drawable.women_category,"Women"));
        arrayList.add(new Category(R.drawable.kid_category,"Kid"));
        arrayList.add(new Category(R.drawable.other_category_new,"Other"));

        RecyclerView recyclerView = findViewById(R.id.home_rv);
        NotLoggedInMainRVAdapter adapter = new NotLoggedInMainRVAdapter(arrayList,MainActivityWithoutLogin.this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);

    }


}
