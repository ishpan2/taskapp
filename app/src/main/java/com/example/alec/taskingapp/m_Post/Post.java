package com.example.alec.taskingapp.m_Post;

/**
 * Created by Alec on 5/29/2017.
 */

public class Post {

    String name;
    int price;
    public Post(){
        this.price = 10;
    }


    public String getName(){
        return name;
    }

    public int getPrice(){ return  price; }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(int price) { this.price = price; }
}
