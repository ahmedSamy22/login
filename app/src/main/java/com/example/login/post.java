package com.example.login;


public class post {

    private int mProfile_image ;
    private String mAccount_name ;
    private String mPost_time ;
    private String mPost_header ;
    private String mPost_description;
    private  int mNumber_of_ratting_post ;
    private int mNumber_of_favorite_post;
    private int mPost_img ;
    private boolean mLove;
    private boolean mRate;



    //private int id =0;
    //static int pub_id =0;

    post(int profile_img , String account_name , String post_time , String post_header , String post_description , int number_of_ratting_post , int post_img , int number_of_favorite_post , boolean love , boolean rate){

        mProfile_image = profile_img ;
        mAccount_name = account_name;
        mPost_time = post_time;
        mPost_header = post_header;
        mPost_description = post_description;
        mNumber_of_ratting_post = number_of_ratting_post;
        mNumber_of_favorite_post = number_of_favorite_post;
        mPost_img = post_img;
        mLove = love;
        mRate = rate;


        //pub_id++;
        //id=pub_id;
    }

    public int getProfile_image() {
        return mProfile_image;
    }

    public String getAccount_name() {
        return mAccount_name;
    }

    public String getPost_time() {
        return mPost_time;
    }

    public String getPost_header() {
        return mPost_header;
    }

    public String getPost_description() {
        return mPost_description;
    }

    public int getNumber_of_ratting_post() {
        return mNumber_of_ratting_post;
    }

    public int getPost_img() {
        return mPost_img;
    }

    public int getmNumber_of_favorite_post() {
        return mNumber_of_favorite_post;
    }


/*
    public int getId() {
        return id;
    }

    public boolean setLike(){

        return true;
    }
    */
}




