package com.example.login;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import static android.content.Context.MODE_PRIVATE;
import static com.example.login.Main2Activity.fir_name;
import static com.example.login.Main2Activity.sec_name;


public class AccountFragment extends Fragment {

    public static final String SHARED_PREF="sharedPrefs";
    String[] uni = {"University","Benha", "cairo", "Ain Shams", "El-Fayom", "Asiwat", "Mansoura", "Manofya", "El-Mania", "El-swais", "Helwan", "El-zagazig", "Bani_Swaif"};
    String[] le = {"level","1st year", "2nd year", "3th year", "4th year", "Others"};
    String[] term = {"term","first term", "second term"};
    Spinner universty_sp,level_sp,term_sp;
    ImageView ima,iml,imt,imu,imp;

    EditText ea,ep;
    Button save;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account , container,false);

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        String username = sharedPreferences.getString(fir_name ,"") + " " + sharedPreferences.getString(sec_name ,"") ;

        EditText account_name = view.findViewById(R.id.account_name);
        account_name.setText(username);



        universty_sp=(Spinner)view.findViewById(R.id.uni);
        /*SharedPreferences sharedPreferences3=getActivity().getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        int t3=sharedPreferences3.getInt(univ,0);
        universty_sp.setSelection(t3);
*/
        ArrayAdapter adapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,uni){
            @Override
            public boolean isEnabled(int i)
            {
                if(i==0)
                {
                    return  false;
                }
                else
                {

                    return  true;
                }
            }
        };
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        universty_sp.setAdapter(adapter);


        level_sp=(Spinner)view.findViewById(R.id.level);
  /*      SharedPreferences sharedPreferences2=getActivity().getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        int t2=sharedPreferences2.getInt(lev,0);
        level_sp.setSelection(t2);
*/
        ArrayAdapter adapter2=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,le){

            @Override
            public boolean isEnabled(int i)
            {
                if(i==0)
                {
                    return  false;
                }
                else
                {

                    return  true;
                }
            }
        };
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        level_sp.setAdapter(adapter2);



        term_sp=(Spinner)view.findViewById(R.id.term);
  /*      SharedPreferences sharedPreferences1=getActivity().getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
       // int t=sharedPreferences1.getInt(TERM,0);
        //term_sp.setSelection(t);
*/
        ArrayAdapter adapter4=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,term){
            @Override
            public boolean isEnabled(int i)
            {
                if(i==0)
                {
                    return  false;
                }
                else
                {

                    return  true;
                }
            }
        };
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        term_sp.setAdapter(adapter4);
        ima=(ImageView)view.findViewById(R.id.ea);
        imp=(ImageView)view.findViewById(R.id.ep);
        ea=(EditText)view.findViewById(R.id.account_name);
        ep=(EditText)view.findViewById(R.id.pass);
        save=(Button)view.findViewById(R.id.save);
        ima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ea.setEnabled(true);
                ea.setText("");
                ea.requestFocus();
            }
        });

        imp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ep.setEnabled(true);
                ep.setText("");
                ep.requestFocus();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ea.setEnabled(false);
                ep.setEnabled(false);

            }
        });

        return view;
    }
}
