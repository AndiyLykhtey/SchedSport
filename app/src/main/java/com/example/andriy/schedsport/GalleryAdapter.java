package com.example.andriy.schedsport;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<ImageModel> result;
    private PhotoAdapter adapter;

    private FirebaseDatabase database;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_adapter);

        result = new ArrayList<>();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("image");
        recyclerViewListener();
        updateList();
    }

  public static void start(Context context) {
      Intent starter = new Intent(context, GalleryAdapter.class);
      context.startActivity(starter);
  }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        item.getItemId();
        return super.onContextItemSelected(item);
    }

    private void recyclerViewListener(){
        recyclerView = findViewById(R.id.photo_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PhotoAdapter(result,this);
        recyclerView.setAdapter(adapter);
    }

    private void updateList(){
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                result.add(dataSnapshot.getValue(ImageModel.class));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                ImageModel model = dataSnapshot.getValue(ImageModel.class);

                int index = getItemIndex(model);

                result.set(index, model);
                adapter.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

                ImageModel model = dataSnapshot.getValue(ImageModel.class);

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

    private int getItemIndex(ImageModel imageModel){

        int index = -1;
        for(int i = 0;i<result.size();i++){
            if(result.get(i).key.equals(imageModel.key)){
                index = i;
                break;
            }
        }
        return index;
    }
}
