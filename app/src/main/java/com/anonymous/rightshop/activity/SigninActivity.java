package com.anonymous.rightshop.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.anonymous.rightshop.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SigninActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    private static final String TAG = "TAG";
    EditText etEmail,etPassword;
    String Email,Password;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        }
        initialize();
    }

    private void initialize(){
        mAuth = FirebaseAuth.getInstance();
        etEmail = findViewById(R.id.et_email_address);
        etPassword=findViewById(R.id.et_password);
        progressBar = findViewById(R.id.progressBar);
    }

    /**
     * THis method will be called when we start our app, if user is already logged in then it will automatically will goto HOme
     */
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user!=null) {
            Intent intent = new Intent(this,SplashActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

    }

    /**
     * This Method is called when login button will be clicked!
     * And it checks whether the input fields are correct or not,
     * if input fields are correct then it will check the user info in the firebase and login the user
     * @param view This is the Login Button View
     */
    public void loginInApplication(View view) {
        progressBar.setVisibility(View.VISIBLE);
        Email = etEmail.getText().toString();
        Password = etPassword.getText().toString();
        if(Email.isEmpty() || !(Email.contains("@"))|| Email.length()<8){
            Toast.makeText(this, "Incorrect Email !", Toast.LENGTH_SHORT).show();
            etEmail.setBackgroundResource(R.drawable.error_text_field);
            etPassword.setBackgroundResource(R.drawable.edit_text_background);
            progressBar.setVisibility(View.GONE);
        }
        else if (Password.length()<8){
            Toast.makeText(this, "Incorrect Password!", Toast.LENGTH_SHORT).show();
            etPassword.setBackgroundResource(R.drawable.error_text_field);
            etEmail.setBackgroundResource(R.drawable.edit_text_background);
            progressBar.setVisibility(View.GONE);
        }
        else {
            login(Email,Password);
        }

    }


    /**
     * This Method will login the User
     * @param emailAddress EMail Address of the user
     * @param password Password of the user
     */
    private void login(String emailAddress, String password){
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(emailAddress, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @SuppressLint("NewApi")
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            progressBar.setVisibility(View.GONE);
                            Intent intent = new Intent(SigninActivity.this,MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();

                        }
                        else {
                            // If sign in fails, display a message to the user.
                            try {
                                throw Objects.requireNonNull(task.getException());
                            } catch (FirebaseAuthInvalidUserException e) {
                                Toast.makeText(SigninActivity.this, "Invalid Email id", Toast.LENGTH_SHORT).show();
                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                Log.d(TAG , "email :" + Email);
                                Toast.makeText(SigninActivity.this, "invalid Password", Toast.LENGTH_SHORT).show();
                            } catch (FirebaseNetworkException e) {
                                Toast.makeText(SigninActivity.this, "Network Problem", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                //Log.e(TAG, e.getMessage());
                            }
                            //Log.w(TAG, "signInWithEmail:failed", task.getException());
                            Toast.makeText(SigninActivity.this, "Error While Logging in!!",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                        // ...
                    }
                });
    }


    /**
     * This Method is for going in SignUp Activity
     * @param view signup TextView
     */
    public void gotoSignUp(View view) {
        Intent intent = new Intent(SigninActivity.this,SignUpActivity.class);
        startActivity(intent);
    }


    /**
     * This Method is for going in forget Password Activity
     * @param view Forget Password Text View
     */
    public void gotoForgetPassword(View view) {
        Intent intent = new Intent(SigninActivity.this, ForgetPasswordActivity.class);
        startActivity(intent);
    }
}
