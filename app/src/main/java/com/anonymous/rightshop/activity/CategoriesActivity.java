package com.anonymous.rightshop.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.anonymous.rightshop.R;
import com.anonymous.rightshop.db.DatabaseHelper;
import com.anonymous.rightshop.fragments.KidFragment;
import com.anonymous.rightshop.fragments.MenFragment;
import com.anonymous.rightshop.fragments.OtherFragment;
import com.anonymous.rightshop.fragments.WomenFragment;
import com.nex3z.notificationbadge.NotificationBadge;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CategoriesActivity extends AppCompatActivity {


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public static NotificationBadge mBadge;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }

        DatabaseHelper db = new DatabaseHelper(CategoriesActivity.this);
        mBadge = findViewById(R.id.badge);
        mBadge.setNumber(db.cartItems());
        ImageView go_back_home = findViewById(R.id.go_back_home);
        go_back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CategoriesActivity.this.onBackPressed();
            }
        });

        ImageView gotoCart = findViewById(R.id.goto_cart);
        gotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CategoriesActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });

        TextView ToolbarName = findViewById(R.id.name);

        Intent intent = getIntent();
        String fragment = intent.getStringExtra("fragment");
        assert fragment != null;
        switch (fragment) {
            case "Men":
                ToolbarName.setText("Men Category");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MenFragment()).commit();
                break;
            case "Women":
                ToolbarName.setText("Women Category");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WomenFragment()).commit();
                break;
            case "Kid":
                ToolbarName.setText("Kid Category");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new KidFragment()).commit();
                break;
            case "Other":
                ToolbarName.setText("Other Category");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new OtherFragment()).commit();
                break;
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
