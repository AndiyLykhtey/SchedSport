package com.example.andriy.schedsport;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;

public class ScheduAdapter extends AppCompatActivity implements View.OnClickListener {
    ImageView img_Back;
    ConstraintLayout schedule_constraint;
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedu_adapter);

        initUI();
        setOnClickListeners();
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, ScheduAdapter.class);
        context.startActivity(starter);
    }

    private void initUI (){
        img_Back = findViewById(R.id.schedule_back);
        calendarView = findViewById(R.id.schedule_calendar);
        schedule_constraint = findViewById(R.id.Sched_id);
    }

    private void setOnClickListeners(){
        img_Back.setOnClickListener(this);
        calendarView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.schedule_back) {
            MainActivity.start(this);
            finish();
        } else if (view.getId() == R.id.schedule_calendar) {
            finish();
        }
    }

}
