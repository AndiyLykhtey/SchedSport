package com.example.andriy.schedsport;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class AddExercisesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<UserModel> result;
    private UserAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    public static void start(Context context) {
        Intent starter = new Intent(context, AddExercisesActivity.class);
        context.startActivity(starter);
    }
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercises);

        result = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("exercises");
        recyclerViewListener();
        updateList();

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        item.getItemId();
        return super.onContextItemSelected(item);
    }

    private void recyclerViewListener(){
        recyclerView = findViewById(R.id.exercises_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new UserAdapter(result,this);
        recyclerView.setAdapter(adapter);
    }

    private void updateList(){
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                result.add(dataSnapshot.getValue(UserModel.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                UserModel model = dataSnapshot.getValue(UserModel.class);

                int index = getItemIndex(model);

                result.set(index, model);
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                UserModel model = dataSnapshot.getValue(UserModel.class);

                int index = getItemIndex(model);

                result.remove(index);
                adapter.notifyItemRemoved(index);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private int getItemIndex(UserModel userModel){

        int index = -1;
        for(int i = 0;i<result.size();i++){
            if(result.get(i).key.equals(userModel.key)){
                index = i;
                break;
            }
        }
        return index;
    }

    /*private void addExercisesToList(int position){
        reference.child(result.get(position).key).addValueEventListener(this);
    }*/

}
