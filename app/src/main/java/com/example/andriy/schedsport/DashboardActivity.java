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
import com.google.firebase.auth.FirebaseUser;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView txtWelcome,btnBack;
    private EditText input_new_password;
    private Button btnChangePass, btnLogOut;
    private ConstraintLayout dashboardActivity;

    private FirebaseAuth auth;

    public static void start(Context context) {
        Intent starter = new Intent(context, DashboardActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtWelcome = findViewById(R.id.dashboard_welcom);
        input_new_password = findViewById(R.id.dashboard_new_password);
        btnChangePass = findViewById(R.id.dashboard_btn_change_password);
        btnLogOut = findViewById(R.id.dashboard_btn_logout);
        btnBack = findViewById(R.id.dashboard_back);
        dashboardActivity = findViewById(R.id.dashboard);

        auth = FirebaseAuth.getInstance();

        btnChangePass.setOnClickListener(this);
        btnLogOut.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        if (auth.getCurrentUser() != null){
            txtWelcome.setText(auth.getCurrentUser().getEmail());
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.dashboard_btn_change_password){
            changePassword(input_new_password.getText().toString());
        } else if(view.getId() == R.id.dashboard_btn_logout){
            logOutUser();
        } else if(view.getId() == R.id.dashboard_back){
            MainActivity.start(DashboardActivity.this);
            finish();
        }
    }

    private void logOutUser() {
        auth.signOut();
        if (auth.getCurrentUser() == null){
            LoginActivity.start(this);
            finish();
        }
    }



    private void changePassword(String newPassword) {
        FirebaseUser user = auth.getCurrentUser();
        user.updatePassword(newPassword).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Snackbar.make(dashboardActivity, "Password changed", Snackbar.LENGTH_SHORT)
                            .show();

                }
            }
        });
    }
}
