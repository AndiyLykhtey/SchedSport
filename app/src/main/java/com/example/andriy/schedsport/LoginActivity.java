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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnLogIn;
    EditText input_email, input_password;
    TextView btnSignUp, btnForgotPassword;
    ConstraintLayout activity_Log_In;

    private FirebaseAuth auth;

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initUI();
        setOnClickListeners();
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.login_btn_forgot_password) {
            ForgotPassActivity.start(this);
            finish();
        } else if (view.getId() == R.id.Sign_up) {
            SignUpActivity.start(this);
            finish();
        } else if (view.getId() == R.id.login_btn_login) {
            loginUser(input_email.getText().toString(), input_password.getText().toString());
        }
    }

    private void loginUser(final String email, final String password) {
        if(!email.isEmpty()){
        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        checkPass(password);
                    } else {
                        MainActivity.start(LoginActivity.this);
                        finish();
                    }
                }
            });
    }else {
            Snackbar.make(activity_Log_In, "Enter email", Snackbar.LENGTH_SHORT).show();
        }
    }
    private void initUI (){
        btnLogIn = findViewById(R.id.login_btn_login);
        input_email = findViewById(R.id.login_email);
        input_password = findViewById(R.id.login_password);
        btnSignUp = findViewById(R.id.Sign_up);
        btnForgotPassword = findViewById(R.id.login_btn_forgot_password);
        activity_Log_In = findViewById(R.id.Constrain_layout);
    }

    private void setOnClickListeners(){
        btnSignUp.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);
        btnForgotPassword.setOnClickListener(this);
    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }
    private void checkPass(String password){
        if (password.length() < 6  && !isValidPassword(password)) {
            Snackbar.make(activity_Log_In, "Password lenght must be over 6", Snackbar.LENGTH_SHORT).show();
        }else {
            Snackbar.make(activity_Log_In, "Entered symbol is a mistake", Snackbar.LENGTH_SHORT).show();
        }
    }
}
