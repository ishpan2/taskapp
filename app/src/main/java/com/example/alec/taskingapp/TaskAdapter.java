package com.example.alec.taskingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.alec.taskingapp.m_Post.Post;

import java.util.ArrayList;

/**
 * Created by Alec on 5/30/2017.
 */

public class TaskAdapter extends BaseAdapter {

    Context context;
   // String[] data;
    ArrayList<Post> posts;
    private static LayoutInflater inflater = null;

    public TaskAdapter(Context context, ArrayList<Post> posts){
        this.context = context;
        this.posts = posts;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if(vi==null)
            vi = inflater.inflate(R.layout.row,null);
        //Fidn the two Texts
        TextView postName =  (TextView)vi.findViewById(R.id.post_title);
        TextView postPrice =  (TextView)vi.findViewById(R.id.post_price);
        //set them to their respective values
        postName.setText(posts.get(position).getName());
        postPrice.setText("$" + Integer.toString(posts.get(position).getPrice()));
        return vi;
    }
}
