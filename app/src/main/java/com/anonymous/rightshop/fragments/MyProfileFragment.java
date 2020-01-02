package com.anonymous.rightshop.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import com.anonymous.rightshop.R;
import com.anonymous.rightshop.activity.SignUpActivity;
import com.anonymous.rightshop.activity.SigninActivity;
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

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MyProfileFragment extends Fragment {

    private String id;
    private EditText Name,Number;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        Name= view.findViewById(R.id.profile_et_name);
        Number = view.findViewById(R.id.profile_et_number);

        Button update = view.findViewById(R.id.profile_button_update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        Button Logout = view.findViewById(R.id.btn_logout);
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        Button btn_login = view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),SigninActivity.class);
                startActivity(intent);
            }
        });

        Button btn_register = view.findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading Profile Data...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User Details");
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if(currentUser==null){
            Name.setVisibility(View.GONE);
            Number.setVisibility(View.GONE);
            update.setVisibility(View.GONE);
            Logout.setVisibility(View.GONE);
            btn_login.setVisibility(View.VISIBLE);
            btn_register.setVisibility(View.VISIBLE);
            progressDialog.dismiss();
        }
        else{
            btn_login.setVisibility(View.GONE);
            btn_register.setVisibility(View.GONE);
            id = currentUser.getUid();
            databaseReference.child(id).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    @SuppressLint({"NewApi", "LocalSuppress"}) String name = Objects.requireNonNull(dataSnapshot.child("Name").getValue()).toString();
                    @SuppressLint({"NewApi", "LocalSuppress"}) String number = Objects.requireNonNull(dataSnapshot.child("Cell Number").getValue()).toString();
                    Name.setText(name);
                    Number.setText(number);
                    progressDialog.dismiss();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    progressDialog.dismiss();
                }
            });
        }


        TextView ContactByWhatsapp = view.findViewById(R.id.contact_by_whatsapp);
        ContactByWhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactWhatsapp();
            }
        });

        TextView ContactByEmail = view.findViewById(R.id.contact_by_email);
        ContactByEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactEmail();
            }
        });

        TextView Report = view.findViewById(R.id.tv_report);
        Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendReport();
            }
        });



        return view;
    }

    private void contactEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto","rightshop.pk.co@gmail.com", null));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
        startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }


    private FirebaseAuth mAuth;

    private void logout(){
        mAuth = FirebaseAuth.getInstance();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setCancelable(true);
        builder.setTitle("Logout");
        builder.setMessage("Are You Sure Want to Logout? Pending Order will be Vanished!");
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAuth.signOut();
                        //deleteOrderFromDatabase();
                        Intent intent = new Intent(getContext(), SigninActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Cancelled!", Toast.LENGTH_SHORT).show();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void updateProfile(){
        Map<String, Object> taskMap = new HashMap<>();
        taskMap.put("User Id",id);
        taskMap.put("Name", Name.getText().toString());
        taskMap.put("Cell Number", Number.getText().toString());
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User Details");
        databaseReference.child(id).setValue(taskMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(getContext(), "Your Profile Details Has Been Updated!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void contactWhatsapp(){
        String contact = "+92 3111052051"; // use country code with your phone number
        String url = "https://api.whatsapp.com/send?phone=" + contact;
        try {
            PackageManager pm = Objects.requireNonNull(getContext()).getPackageManager();
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
        } catch (PackageManager.NameNotFoundException e) {
            Toast.makeText(getContext(), "Whatsapp app not installed in your phone", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private String Title,Details;
    private String name,number;
    /**
     * This Method is for sending reports to the admin
     */
    private void sendReport() {
        final Dialog reportDialog = new Dialog(Objects.requireNonNull(getContext()),R.style.Theme_AppCompat_DayNight_Dialog);
        reportDialog.setContentView(R.layout.dialog_report);
        reportDialog.setCancelable(true);
        reportDialog.setTitle("Report Any Issue!");

        final EditText et_Title = reportDialog.findViewById(R.id.issue_title);

        final EditText et_Details = reportDialog.findViewById(R.id.issue_details);

        Button submit = reportDialog.findViewById(R.id.btn_issue_submit);
        final ProgressBar progressBar = reportDialog.findViewById(R.id.progressBar);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Title = et_Title.getText().toString();
                Details = et_Details.getText().toString();
                if(Title.equals("")|| Details.equals("")){
                    Toast.makeText(getContext(), "Both Fields are Required", Toast.LENGTH_SHORT).show();

                }
                else{
                    progressBar.setVisibility(View.VISIBLE);
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User Details");
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    if(currentUser==null){
                        Toast.makeText(getContext(), "For Sending Report, You Must Login First!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        id = currentUser.getUid();
                        databaseReference.child(id).addValueEventListener(new ValueEventListener() {
                            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                name = Objects.requireNonNull(dataSnapshot.child("Name").getValue()).toString();
                                number = Objects.requireNonNull(dataSnapshot.child("Cell Number").getValue()).toString();
                                sendToAdmin();
                                reportDialog.dismiss();
                                progressBar.setVisibility(View.GONE);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                progressBar.setVisibility(View.GONE);
                            }
                        });
                    }

                }
            }
        });
        reportDialog.show();

    }

    /**
     * This
     */
    private void sendToAdmin() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Reports");
        String key = databaseReference.push().getKey();
        if(key!=null){
            Map<String,Object> map= new HashMap<>();
            map.put("Key",key);
            map.put("Title",Title);
            map.put("Details",Details);
            map.put("Report By",name);
            map.put("Reporter Number",number);
            databaseReference.child(key).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(getContext(), "Your Report Has Been Submitted!", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Failed! Check Your Connection!!", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }
}
