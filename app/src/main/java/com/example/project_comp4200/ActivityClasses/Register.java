package com.example.project_comp4200.ActivityClasses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.util.*;
import android.widget.Toast;

import com.example.project_comp4200.Database.MainActivity;
import com.example.project_comp4200.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;   // for firebase authentication

    /*
    / editview textview and button variables to store date and setting click listeners
     */
    private TextView existingUser;
    private EditText editText_userName, editText_userPassword, editText_confirm_userPassword, editText_email;
    private Button registerUser;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        /*
        / Initializing the variables
         */

        registerUser = findViewById(R.id.registerButton);
        existingUser = findViewById(R.id.login_link);

        registerUser.setOnClickListener(this);
        existingUser.setOnClickListener(this);

        editText_email = findViewById(R.id.email_input);
        editText_userName = findViewById(R.id.name_input);
        editText_userPassword = findViewById(R.id.password_input);
        editText_confirm_userPassword = findViewById(R.id.confirm_password_input);

        progressBar = findViewById(R.id.progressBar2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_link:
                startActivity(new Intent(this, MainActivity.class));  // switching activity (intent)
                break;
            case R.id.registerButton:
                registerUser();  // function to handle user registration
                break;
        }
    }

    private void registerUser() {
        String email= editText_email.getText().toString().trim();
        String fullName = editText_userName.getText().toString().trim();
        String password = editText_userPassword.getText().toString().trim();
        String confirmed_password = editText_confirm_userPassword.getText().toString().trim();

        /*

        / making sure all the information is added correctly!
         */
        if(fullName.isEmpty()){
            editText_userName.setError("Full Name is required!!");
            editText_userName.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editText_email.setError("E-mail is required!!");
            editText_email.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editText_email.setError("Invalid Email!!");
            editText_email.requestFocus();
            return;
        }

        if(password.isEmpty()){
            editText_userPassword.setError("Password is required!!");
            editText_userPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            editText_userPassword.setError("Password too short!!");
            editText_userPassword.requestFocus();
            return;
        }

        if(!password.equals(confirmed_password)){
            editText_confirm_userPassword.setError("Passwords do not match!!");
            editText_confirm_userPassword.requestFocus();
            return;
        }
        /*
        / progress bar is no visible after click and add user to FireBase using email and pass
         */

        progressBar.setVisibility(View.VISIBLE);

        /*/
        adding User object using constructor to fireBase database using email and password authentication in FireBase
         */

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            com.example.project_comp4200.ActivityClasses.User user = new com.example.project_comp4200.ActivityClasses.User(fullName, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(Register.this, "User has been registered successfully", Toast.LENGTH_LONG).show();
                                                progressBar.setVisibility(View.GONE);

                                                // re direct to log in page now !!

                                            } else {
                                                Toast.makeText(Register.this, "Failed to Register, Try Again", Toast.LENGTH_SHORT).show();
                                                progressBar.setVisibility(View.GONE);
                                            }
                                        }
                                    });
                        } else {
                            Toast.makeText(Register.this, "Failed to Register, Try Again", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


    }
}