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
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText input_email;
    private Button btnResetPass;
    private TextView btnBack;
    private ConstraintLayout fogot_activity;

    private FirebaseAuth auth;

    public static void start(Context context) {
        Intent starter = new Intent(context, ForgotPassActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        initId();
        setOnClickListener();
        auth = FirebaseAuth.getInstance();
    }

    private void initId(){
        input_email = findViewById(R.id.forgot_email);
        btnResetPass = findViewById(R.id.forgot_btn_reset);
        btnBack = findViewById(R.id.forgot_btn_back);
        fogot_activity = findViewById(R.id.forgot_activity);
    }

    private void setOnClickListener(){
        btnResetPass.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.forgot_btn_back){
            LoginActivity.start(this);
            finish();
        } else if(view.getId() == R.id.forgot_btn_reset){
            resetPassword(input_email.getText().toString());
        }
    }

    private void resetPassword(final String email) {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Snackbar.make(fogot_activity, "We have sent password to email" + email, Snackbar.LENGTH_SHORT).show();
                        } else{
                            Snackbar.make(fogot_activity, "Failed to send password", Snackbar.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
