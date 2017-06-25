package com.example.alec.taskingapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.alec.taskingapp.m_FireBase.FireBaseHelper;
import com.example.alec.taskingapp.m_Post.Post;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.Executor;


/**
 * TODO:
 * -Add loading bar while retreiving the posts from database
 * -Add logout Button
 * -
 *
 *
 */




public class PostList extends AppCompatActivity {


    ListView lv;
    ArrayAdapter<Post> adapter;
    TaskAdapter adapter2;
    DatabaseReference db;
    FireBaseHelper helper;
    ArrayList<Post> postings = new ArrayList<Post>();
    private DatabaseReference mPostReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list2);

        //FIND LIST VIEW FOR TASKS
        lv = (ListView) findViewById(R.id.lv_post);

        //SETUP FIREBASE
        db = FirebaseDatabase.getInstance().getReference();
        //helper = new FireBaseHelper(db);
        //adapter = new ArrayAdapter<Post>(this, android.R.layout.simple_list_item_1,postings);
        //CREATE CUSTOM ADAPTER FOR TASKS
        adapter2 = new TaskAdapter(this,postings);

        //CREATE LISTENER TO POPULATE THE LISTVIEW WITH THE TASKS
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    postings.add(child.getValue(Post.class));
                    lv.setAdapter(adapter2);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }












}
