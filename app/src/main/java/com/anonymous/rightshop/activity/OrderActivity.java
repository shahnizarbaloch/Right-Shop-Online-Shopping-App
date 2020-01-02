package com.anonymous.rightshop.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.anonymous.rightshop.R;
import com.anonymous.rightshop.adapter.OrderItemsRecyclerViewAdapter;
import com.anonymous.rightshop.db.DatabaseHelper;
import com.anonymous.rightshop.model.CartItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private TextView Name;
    private TextView Email;
    private TextView Number;
    @SuppressLint("StaticFieldLeak")
    public static TextView Total;
    private DatabaseHelper db;
    private FirebaseUser currentUser;
    private DatabaseReference databaseReference;
    private TextView TVbookOrder;

    private EditText Location;
    public ArrayList<CartItem> arrayList;
    private String getName,getEmail,getNumber,getLocation;
    private LinearLayout info_layout;
    private Button btn_login,btn_register;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
        db = new DatabaseHelper(OrderActivity.this);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User Details");
        currentUser = mAuth.getCurrentUser();

        info_layout = findViewById(R.id.info_layout);

        //Recycler View in Fragment
        RecyclerView recyclerView = findViewById(R.id.rv_order_categories);

        ImageView noItems = findViewById(R.id.no_items);

        LinearLayout linearLayout = findViewById(R.id.order_details_ll);

        arrayList= db.getOrder();

        ImageView go_back_home = findViewById(R.id.go_back_home);
        go_back_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderActivity.this.onBackPressed();
            }
        });
        Name = findViewById(R.id.tv_name_order);
        Email = findViewById(R.id.tv_email_address_order);
        Number = findViewById(R.id.tv_mobile_number_order);
        int totalPrice= db.addAllValues();
        Total = findViewById(R.id.tv_total_price_order);
        Total.setText("PKR " + totalPrice);
        Location = findViewById(R.id.tv_location_order);
        TVbookOrder = findViewById(R.id.tv_book_order);
        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this,SigninActivity.class);
                startActivity(intent);
            }
        });

        btn_register=findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        getProfileInfo();
        if(arrayList.size()==0){
            noItems.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
            TVbookOrder.setVisibility(View.GONE);
        }

        TVbookOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(OrderActivity.this);
                progressDialog.setMessage("Sending Order...");
                progressDialog.show();
                getName=Name.getText().toString();
                getEmail=Email.getText().toString();
                getNumber=Number.getText().toString();
                getLocation=Location.getText().toString();
                if(getLocation.isEmpty() || getLocation.length()<8){
                    Toast.makeText(OrderActivity.this, "Please Add Your Correct Location", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                else if(getName.isEmpty()||getEmail.isEmpty()||getNumber.isEmpty()){
                    Toast.makeText(OrderActivity.this, "Internet Seems to be Slow !", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                else if(Total.getText().toString().equals("0")){
                    Toast.makeText(OrderActivity.this, "Please Add Any Item in Your Order!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
                else{
                    sendData();
                    progressDialog.dismiss();
                }
            }
        });

        OrderItemsRecyclerViewAdapter adapter = new OrderItemsRecyclerViewAdapter(arrayList,OrderActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(OrderActivity.this));

        recyclerView.setAdapter(adapter);
    }

    /**
     * This Method will get User Email Number and Name From Firebase Database
     * When User Information will be being Gather then Scroll Bar will be Shown on Screen,
     * When User Information will be Loaded Then ScrollBar will be Gone!!
     */
    private void getProfileInfo() {
        boolean connectivity=checkConnection();
        if(connectivity){
            final ProgressDialog progressDialog = new ProgressDialog(OrderActivity.this);
            progressDialog.setMessage("Loading Profile Data...");
            progressDialog.show();
            if(currentUser==null){
                Toast.makeText(this, "Registering Is Must, For Ordering Any Item!", Toast.LENGTH_SHORT).show();
                info_layout.setVisibility(View.GONE);
                btn_login.setVisibility(View.VISIBLE);
                btn_register.setVisibility(View.VISIBLE);

                progressDialog.dismiss();
            }
            else{
                TVbookOrder.setClickable(true);
                btn_login.setVisibility(View.GONE);
                btn_register.setVisibility(View.GONE);
                info_layout.setVisibility(View.VISIBLE);
                String userid = currentUser.getUid();
                databaseReference.child(userid).addValueEventListener(new ValueEventListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        @SuppressLint({"NewApi", "LocalSuppress"}) String name = Objects.requireNonNull(dataSnapshot.child("Name").getValue()).toString();
                        @SuppressLint({"NewApi", "LocalSuppress"}) String number = Objects.requireNonNull(dataSnapshot.child("Cell Number").getValue()).toString();
                        @SuppressLint({"NewApi", "LocalSuppress"}) String email = Objects.requireNonNull(dataSnapshot.child("Email Address").getValue()).toString();

                        Name.setText(name);
                        Number.setText("+92" + number);
                        Email.setText(email);

                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

        }
        else{
            Toast.makeText(this, "No Internet Connectivity!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(OrderActivity.this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    /**
     * This Method will Delete Order After Sending it to Admin
     */
    private void deleteOrderFromDatabase() {
        SQLiteDatabase sqLiteDatabase = db.getWritableDatabase();
        sqLiteDatabase.delete("ORDERS",null,null);
        sqLiteDatabase.close();
    }

    /**
     * This Method Will Send Order To The Firebase which will be Recieve by Admin
     */
    private void sendData(){
        String currentTime = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        // Write a message to the database
        final DatabaseReference myRef=FirebaseDatabase.getInstance().getReference("Orders");
        DatabaseReference keyRef=myRef.push();
        final String Key= keyRef.getKey();
        assert Key != null;
        Map<String,Object> taskMap = new HashMap<>();
        taskMap.put("Name", getName);
        taskMap.put("Email", getEmail);
        taskMap.put("Number", getNumber);
        taskMap.put("Location", getLocation);
        taskMap.put("Date",currentTime);
        taskMap.put("Total",Total.getText().toString());
        myRef.child("OrderBy").child(Key).setValue(taskMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        myRef.child("OrderDetails").child(Key).setValue(db.getOrder());
                        Toast.makeText(OrderActivity.this, "Your Order Has Been Submitted Successfully!", Toast.LENGTH_SHORT).show();
                        deleteOrderFromDatabase();
                        Intent intent = new Intent(OrderActivity.this,MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);

                        //DONE!!
                    }

                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(OrderActivity.this, "Failed to Submit Order", Toast.LENGTH_SHORT).show();
            }
        });


    }

    /**
     * This Will Check Internet Connection
     * @return connectivity
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private boolean checkConnection(){
        boolean connected;
        ConnectivityManager connectivityManager = (ConnectivityManager) Objects.requireNonNull(getApplicationContext()).getSystemService(Context.CONNECTIVITY_SERVICE);
        //we are connected to a network
        connected = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
        return connected;
    }



}
