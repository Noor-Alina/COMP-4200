package com.example.project_comp4200;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;   // for firebase authentication
    private TextView register;


    // variables for log in of registered user ...
    private EditText userEmail, userPassword;
    private Button btn_Login;
  // -------------------//
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.btn_register);   // registration
        register.setOnClickListener(this);

        /*/
        Log in
         */
        mAuth = FirebaseAuth.getInstance();
        userEmail = findViewById(R.id.ev_email);
        userPassword = findViewById(R.id.ev_password);
        progressBar = findViewById(R.id.progressBar);

        btn_Login = findViewById(R.id.btn_logIn);
        btn_Login.setOnClickListener(this);



    }


    @Override
    public void onClick(View view) {

        /*
        / to keep check of what button is pressed Login or register, using switch cases, this is decide what activity to trigger
         */
        switch (view.getId()){
            case R.id.btn_register:
                startActivity(new Intent(this,Register.class));  // switching activity (intent)
                break;
            case R.id.btn_logIn:
                userLogin();
                break;
        }
    }

    private void userLogin() {

        /*/
        ensuring correct information is added for log in
         */
        String email = userEmail.getText().toString().trim();
        String password = userPassword.getText().toString().trim();

        if(email.isEmpty()){
            userEmail.setError("Please enter e-mail");
            userEmail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            userEmail.setError("Please enter valid e-mail");
            userEmail.requestFocus();
            return;
        }

        if(password.isEmpty()){
            userPassword.setError("Please enter password");
            userPassword.requestFocus();
            return;
        }

        if(password.length() < 6){
            userPassword.setError("minimum password length is 6 characters");
            userPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);


        /*/
        validating user login task using fire base authenticator
         */
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    // redirect to App Home Page!! (poll part!)      // creating empty activity for now!!
                    startActivity(new Intent(MainActivity.this,demoHomePage.class));
                    progressBar.setVisibility(View.GONE);
                }else {
                    Toast.makeText(MainActivity.this, "Login Failed!, Please check credentials", Toast.LENGTH_LONG).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }
}