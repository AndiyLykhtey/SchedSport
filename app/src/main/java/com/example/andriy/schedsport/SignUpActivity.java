package com.example.andriy.schedsport;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    public static void start(Context context) {
        Intent starter = new Intent(context, SignUpActivity.class);
        context.startActivity(starter);
    }

    Button btnSignup;
    EditText input_email, input_password;
    TextView btnLogIn, btnForgotPassword;
    ConstraintLayout activity_sign_up;

    private FirebaseAuth auth;
    Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnSignup = findViewById(R.id.signup_btn_register);
        btnForgotPassword = findViewById(R.id.signup_btn_forgot_password);
        btnLogIn = findViewById(R.id.Signup_btn_login);
        input_email = findViewById(R.id.signup_email);
        input_password = findViewById(R.id.signup_password);
        activity_sign_up = findViewById(R.id.SignUp_Const);

        btnSignup.setOnClickListener(this);
        btnForgotPassword.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);

        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.signup_btn_forgot_password) {
            ForgotPassActivity.start(this);
            finish();
        } else if (view.getId() == R.id.Signup_btn_login) {
            LoginActivity.start(this);
            finish();
        } else if (view.getId() == R.id.signup_btn_register) {
            SignUpUser(input_email.getText().toString(), input_password.getText().toString());
        }
    }

    private void SignUpUser(String email, String password) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            snackbar = Snackbar.make(activity_sign_up, "Error:" + task.getException(), Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        } else {
                            snackbar = Snackbar.make(activity_sign_up, "Register success:", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                        }
                    }
                });
    }
}
