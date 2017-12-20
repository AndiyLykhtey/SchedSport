package com.example.andriy.schedsport;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ListAdapter extends AppCompatActivity implements View.OnClickListener {
    RecyclerView recyclerView;

    public static void start(Context context) {
        Intent starter = new Intent(context, ListAdapter.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_adapter);

        recyclerView = findViewById(R.id.exercises_list_start);

        FloatingActionButton fab_add = findViewById(R.id.fab_add_ex);
        fab_add.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab_add_ex) {
            AddExercisesActivity.start(this);
            finish();
        }
    }
}

