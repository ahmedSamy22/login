package com.example.login;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private ArrayList<post> posts = new ArrayList<post>();
    PostAdapter adapter;
    ImageView Profile_image , post_img  , download_img;
    TextView account_name , post_time ,  post_header , post_description ,number_of_ratting_post ,number_of_favorite_post;

    public HomeFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        posts.add(new post(R.drawable.samar,"Samar Abdo","1 hr ago" ,"OPP chapter 1","this 's a mol5s for Dr / Ahmed Taha , this subject name is oop and oop is important for us , the programmers ! Object-oriented programming (OOP) is a programming language model in which programs are organized around data, or objects" , 123 ,R.drawable.post1_img , 421 ,false ,true));
        posts.add(new post(R.drawable.omnia,"Omnia Alwy","yesterday 30/07/2019" ,"Android summer training","this 's a mol5s for M / M7md Ibrhm , this subject name is android and the android is important for us , the programmers ! " , 421 ,R.drawable.post2_img , 1238 ,false ,false));
        posts.add(new post(R.drawable.samy,"Ahmed Samy","25/07/2019" ,"Web summer training","this 's a mol5s for M / Ahmed Yousry , this subject name is web development and the web is important for all of us , the programmers ! it's ynksm to 2 parts frontend and backend" , 2 ,R.drawable.post3_img ,713 ,true ,false));
        posts.add(new post(R.drawable.osman,"Ahmed Osman","30/09/2018" ,"Embedded system chapter 4","this 's a mol5s for Dr / Ahmed shalby , this subject name is embedded system and embedded system is important for us , the programmers ! " , 713 ,R.drawable.post4_img, 83712 ,true ,true));

        adapter = new PostAdapter(getActivity(),posts);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);


        final ListView list_post = view.findViewById(R.id.posts);

        list_post.setAdapter(adapter);
/*
        list_post.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                RelativeLayout list_postChildAt=(RelativeLayout) list_post.getChildAt(position);
                list_postChildAt.getChildAt(6).setVisibility(View.GONE);
            }
        });
        /*
        Profile_image = view.findViewById(R.id.profile_image);
        account_name = view.findViewById(R.id.account_name);
        post_time = view.findViewById(R.id.time_post);
        post_header = view.findViewById(R.id.post_header);
        post_description = view.findViewById(R.id.post_description);
        number_of_ratting_post = view.findViewById(R.id.number_of_ratting_post);
        number_of_favorite_post = view.findViewById(R.id.number_of_favorite_post);
        post_img = view.findViewById(R.id.post_img);
        download_img = view.findViewById(R.id.download_img);
*/
        Toast.makeText(getActivity(), "Wel  home", Toast.LENGTH_SHORT).show();
        return view;
    }

}
