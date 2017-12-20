package com.example.andriy.schedsport;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListview extends ArrayAdapter<String>{

    private String[] exercises_name;
    private String[] exercises_description;
    private Integer[] img;
    private Activity context;


    public CustomListview(Activity context, String[] exercises_name, String[] exercises_description, Integer[] img) {
        super(context, R.layout.view_item, exercises_name);

        this.context = context;
        this.exercises_name = exercises_name;
        this.exercises_description = exercises_description;
        this.img = img;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if(view == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.view_item, null, true);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.imageView.setImageResource(img[position]);
        viewHolder.textView_name.setText(exercises_name[position]);
        viewHolder.textView_description.setText(exercises_description[position]);

        return view;
    }

    class ViewHolder{
        TextView textView_name,textView_description;
        ImageView imageView;
        ViewHolder(View view){
            textView_name = view.findViewById(R.id.exercises_name);
            textView_description =view.findViewById(R.id.exercises_description);
            imageView = view.findViewById(R.id.exercises_image);
        }
    }
}
