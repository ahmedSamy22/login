package com.example.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ClassroomFragment extends Fragment {
    ListView List;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classroom , container,false);
        ArrayList<String> ArrList=new ArrayList<>();
        ArrList.add("Matrials");
        ArrList.add("Matrials2");
        ArrList.add("Matrials3");
        ArrayAdapter<String> items= new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,ArrList);
        List=(ListView)view.findViewById(R.id.List);
        List.setAdapter(items);

        return view;
    }
}
