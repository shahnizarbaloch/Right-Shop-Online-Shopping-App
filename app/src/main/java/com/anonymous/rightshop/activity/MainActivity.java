package com.anonymous.rightshop.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.anonymous.rightshop.R;
import com.anonymous.rightshop.fragments.FragmentCategories;
import com.anonymous.rightshop.fragments.HomeFragment;
import com.anonymous.rightshop.fragments.MyProfileFragment;
import com.anonymous.rightshop.fragments.SpecialOffers;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity{

    TextView textView;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
        BottomNavigationView navView = findViewById(R.id.bottom_nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navView.setSelectedItemId(R.id.navigation_home);
        textView = findViewById(R.id.search_items);


    }



    private void goToFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @SuppressLint("SetTextI18n")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    goToFragment(new HomeFragment());
                    return true;
                case R.id.navigation_my_profile:
                    goToFragment(new MyProfileFragment());
                    return true;
                case R.id.navigation_offers:
                    goToFragment(new SpecialOffers());
                    return true;
                case R.id.navigation_cart:
                    Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.navigation_categories:
                    goToFragment(new FragmentCategories());
                    return true;

            }

            return false;
        }
    };


    @Override
    public void onBackPressed() {

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Exit Confirmation!")
                    .setMessage("Are you sure you want to Exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }




}
