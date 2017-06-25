package com.example.alec.taskingapp.m_FireBase;

import android.widget.Toast;

import com.example.alec.taskingapp.m_Post.Post;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Alec Williams on 5/29/2017.
 */

public class FireBaseHelper {

    DatabaseReference db;

    Boolean saved = null;
    ArrayList<Post> postings = new ArrayList<>();

    public FireBaseHelper(DatabaseReference db){

        this.db = db;

    }

    //ADD POSTING
    public Boolean post(Post post){
        if(post==null){
            saved = false;
        }
        else{
            try{
                db.child("Post").push().setValue(post);
                saved = true;
            }
            catch(DatabaseException e){
                e.printStackTrace();
                saved = false;
            }
        }
        return saved;
    }

    public ArrayList<Post> retrieve(){
        db.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                fetchPost(dataSnapshot);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                fetchPost(dataSnapshot);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                fetchPost(dataSnapshot);


            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                fetchPost(dataSnapshot);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return postings;
    }




    /**
     * POPULATES THE POSTINGS ARRAY WITH THE POSTINGS
     * IN FIREBASE
     * @param dataSnapshot
     */
    private void fetchPost(DataSnapshot dataSnapshot){
        Iterable<DataSnapshot> children = dataSnapshot.getChildren();

        //iterate through each of them and populate the list
        for(DataSnapshot child : children){
            Post post = child.getValue(Post.class);
            postings.add(post);
           // System.out.println(post.getName());

        }
    }
}

