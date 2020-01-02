package com.anonymous.rightshop.activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.anonymous.rightshop.R;
import com.anonymous.rightshop.db.DatabaseHelper;
import com.anonymous.rightshop.model.CartItem;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

public class ItemDetailsActivity extends AppCompatActivity {
    private DatabaseHelper db;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
        ProgressDialog progressDialog = new ProgressDialog(ItemDetailsActivity.this);
        db = new DatabaseHelper(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        Intent intent = getIntent();
        final String imageURL = intent.getStringExtra("Image");
        final String name = intent.getStringExtra("Name");
        final String price = intent.getStringExtra("Price");
        final String serial = intent.getStringExtra("Serial");
        final String details = intent.getStringExtra("Details");

        PhotoView itemImage = findViewById(R.id.item_image);
        Picasso.get().load(imageURL).centerInside().fit().into(itemImage);

        TextView itemName = findViewById(R.id.item_name);
        itemName.setText(name);

        TextView itemPrice = findViewById(R.id.item_price);
        itemPrice.setText("PKR "+price);

        TextView item_details = findViewById(R.id.item_details);
        item_details.setText(details);

        progressDialog.dismiss();

        Button btnAddToCart = findViewById(R.id.btn_add_to_cart);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                //Inserting data in the database
                db.insert(ItemDetailsActivity.this,new CartItem(name,price,serial,imageURL));
                Toast.makeText(ItemDetailsActivity.this, name+" has been added to your cart!", Toast.LENGTH_SHORT).show();
                ItemDetailsActivity.this.onBackPressed();
            }
        });

        ImageView go_back_home = findViewById(R.id.go_back_home);
        go_back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemDetailsActivity.this.onBackPressed();
            }
        });
    }
}
