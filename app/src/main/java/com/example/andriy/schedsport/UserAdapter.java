package com.example.andriy.schedsport;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<UserModel> list;
    private Context context;

    public UserAdapter(List<UserModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item,
                parent, false));
    }

    @Override
    public void onBindViewHolder(final UserViewHolder holder, int position) {

        UserModel userModel = list.get(position);

        holder.txt_exercises_name.setText(userModel.name);
        holder.txt_exercises_description.setText(userModel.description);
        Glide.with(context)
                .load(userModel.getImageId())
                .into(holder.img_exercises_image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder{

        TextView txt_exercises_name, txt_exercises_description;
        ImageView img_exercises_image;

        public UserViewHolder(View itemView) {
            super(itemView);

            txt_exercises_name = itemView.findViewById(R.id.exercises_name);
            txt_exercises_description = itemView.findViewById(R.id.exercises_description);
            img_exercises_image = itemView.findViewById(R.id.exercises_image);

            itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                    contextMenu.add(getAdapterPosition(), 0,0,"Add selected exercises");
                }
            });
        }
    }
}
