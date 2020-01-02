package com.anonymous.rightshop.activity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.anonymous.rightshop.R;
import com.anonymous.rightshop.db.DatabaseHelper;
import com.anonymous.rightshop.fragments.SpecialOffers;
import com.anonymous.rightshop.sub_fragments.Cosmetics;
import com.anonymous.rightshop.sub_fragments.Dresses;
import com.anonymous.rightshop.sub_fragments.HomeAccessories;
import com.anonymous.rightshop.sub_fragments.HomeDecorations;
import com.anonymous.rightshop.sub_fragments.Jackets;
import com.anonymous.rightshop.sub_fragments.JeansShirts;
import com.anonymous.rightshop.sub_fragments.Jewellery;
import com.anonymous.rightshop.sub_fragments.KidClothes;
import com.anonymous.rightshop.sub_fragments.KidShoes;
import com.anonymous.rightshop.sub_fragments.LadyBags;
import com.anonymous.rightshop.sub_fragments.MenOther;
import com.anonymous.rightshop.sub_fragments.MenPerfumes;
import com.anonymous.rightshop.sub_fragments.MenShoes;
import com.anonymous.rightshop.sub_fragments.MenWatches;
import com.anonymous.rightshop.sub_fragments.MobileAccessories;
import com.anonymous.rightshop.sub_fragments.SportsShirts;
import com.anonymous.rightshop.sub_fragments.Wallets;
import com.anonymous.rightshop.sub_fragments.WomenOther;
import com.anonymous.rightshop.sub_fragments.WomenPerfumes;
import com.anonymous.rightshop.sub_fragments.WomenShoes;
import com.anonymous.rightshop.sub_fragments.WomenWatches;
import com.nex3z.notificationbadge.NotificationBadge;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SubCategoriesActivity extends AppCompatActivity {


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    public static NotificationBadge mBadge;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_categories);

        DatabaseHelper db = new DatabaseHelper(SubCategoriesActivity.this);
        mBadge = findViewById(R.id.badge);
        mBadge.setNumber(db.cartItems());
        ImageView go_back_home = findViewById(R.id.go_back_home);
        go_back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubCategoriesActivity.this.onBackPressed();
            }
        });

        ImageView gotoCart = findViewById(R.id.goto_cart);
        gotoCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubCategoriesActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });

        TextView ToolbarName = findViewById(R.id.name);

        Intent intent = getIntent();
        String subFragments = intent.getStringExtra("sub_fragment");

        assert subFragments != null;
        switch (subFragments) {

            case "Special Offers":
                ToolbarName.setText("Special Items");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SpecialOffers()).commit();
                break;
            case "Men Perfumes":
                ToolbarName.setText("Men Perfumes");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MenPerfumes()).commit();
                break;
            case "Jeans Shirts":
                ToolbarName.setText("Jeans Shirts");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new JeansShirts()).commit();
                break;
            case "Sports Shirts":
                ToolbarName.setText("Sports Shirts");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SportsShirts()).commit();
                break;
            case "Men Shoes":
                ToolbarName.setText("Men Shoes");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MenShoes()).commit();
                break;
            case "Men Watches":
                ToolbarName.setText("Men Watches");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MenWatches()).commit();
                break;
            case "Wallets and Bags":
                ToolbarName.setText("Wallets and Bags");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Wallets()).commit();
                break;
            case "T-Shirts and Jackets":
                ToolbarName.setText("T-Shirts and Jackets");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Jackets()).commit();
                break;
            case "Other Men Items":
                ToolbarName.setText("Other Items");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MenOther()).commit();
                break;

            case "Other Women Items":
                ToolbarName.setText("Other Personal Items");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WomenOther()).commit();
                break;

            case "Women Perfumes":
                ToolbarName.setText("Women Perfumes");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WomenPerfumes()).commit();
                break;
            case "Cosmetics":
                ToolbarName.setText("Cosmetics");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Cosmetics()).commit();
                break;
            case "Dresses":
                ToolbarName.setText("Dresses");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Dresses()).commit();
                break;
            case "Jewellery":
                ToolbarName.setText("Jewellery");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new Jewellery()).commit();
                break;
            case "Lady Bags":
                ToolbarName.setText("Lady Bags");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LadyBags()).commit();
                break;
            case "Women Shoes":
                ToolbarName.setText("Women Shoes");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WomenShoes()).commit();
                break;
            case "Women Watches":
                ToolbarName.setText("Women Watches");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new WomenWatches()).commit();
                break;
            case "Kid Clothes":
                ToolbarName.setText("Kid Clothes");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new KidClothes()).commit();
                break;
            case "Kid Shoes":
                ToolbarName.setText("Kid Shoes");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new KidShoes()).commit();
                break;
            case "Home Accessories":
                ToolbarName.setText("Home Accessories");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeAccessories()).commit();
                break;
            case "Home Decorations":
                ToolbarName.setText("Home Decorations");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeDecorations()).commit();
                break;
            case "Mobile Accessories":
                ToolbarName.setText("Mobile Accessories");
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MobileAccessories()).commit();
                break;
        }
    }
}
