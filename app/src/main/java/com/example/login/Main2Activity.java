package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.login.MainActivity.SHARED_PREF;

public class Main2Activity extends AppCompatActivity {
    String[] uni={"Benha","cairo","Ain Shams","El-Fayom","Asiwat","Mansoura","Manofya","El-Mania","El-swais","Helwan","El-zagazig","Bani_Swaif"};
    String[] le={"1st year","2nd year","3th year","4th year","Others"};
    String[] term_arr={"first","second"};

    // public static final String SHARED_PREF="sharedPrefs";

    DatabaseReference databaseReference;

    AutoCompleteTextView univer,level;
    AutoCompleteTextView auto,term;
    AutoCompleteTextView au;
    String f_name,seconde_name,university,year,term_val;
    public static final String fir_name="fname";
    public static final String sec_name="sname";
    EditText user;
    EditText  second;
    String cc="hhhhh";
    public static final String univ="univer";
    public static final String lev="level";
    Button submit_btn;
    String i1,i2,i3,i4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        databaseReference= FirebaseDatabase.getInstance().getReference("data");

        user=(EditText) findViewById(R.id.name1);

        second=(EditText)findViewById(R.id.name2);
        submit_btn=(Button)findViewById(R.id.submit);


        ArrayAdapter<String> ad=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,uni);
        auto=(AutoCompleteTextView)findViewById(R.id.auto);
        auto.setThreshold(1);
        auto.setAdapter(ad);
        auto.setTextColor(Color.BLACK);

        ArrayAdapter<String> ad2=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,le);
        AutoCompleteTextView au=(AutoCompleteTextView)findViewById(R.id.le);
        au.setThreshold(1);
        au.setAdapter(ad2);
        au.setTextColor(Color.BLACK);

        ArrayAdapter<String> tr=new ArrayAdapter<String>(this,android.R.layout.select_dialog_item,term_arr);
        term=(AutoCompleteTextView)findViewById(R.id.term);
        term.setThreshold(1);
        term.setAdapter(tr);
        term.setTextColor(Color.BLACK);

        auto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                university=(String)parent.getItemAtPosition(position).toString();
                Toast.makeText(Main2Activity.this, " "+ parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });
        au.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                year=parent.getItemAtPosition(position).toString();
                Toast.makeText(Main2Activity.this, " "+ parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });
        term.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                term_val=parent.getItemAtPosition(position).toString();
                Toast.makeText(Main2Activity.this, " "+ parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
        });




        submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save_data();
                upload();
                // Toast.makeText(Main2Activity.this,"7hra",Toast.LENGTH_LONG).show();
                /*SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
                i1= sharedPreferences.getString(fir_name,"");
                i2= sharedPreferences.getString(sec_name,"");
                i3= sharedPreferences.getString(univ,"");
                i4= sharedPreferences.getString(lev,"");

                Toast.makeText(Main2Activity.this, "Welcome "+i1+" "+i2, Toast.LENGTH_LONG).show();
*/
                Intent home_intent = new Intent(Main2Activity.this,Home.class);
                startActivity(home_intent);
                finish();
            }
        });
    }

    public void save_data()
    {

        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(fir_name,user.getText().toString());
        editor.putString(sec_name,second.getText().toString());
        editor.putString(univ,university);
        editor.putString(lev,year);
        editor.apply();
    }

    private void upload()
    {
        String user_name=user.getText().toString()+second.getText().toString();
        Intent i=getIntent();
        String email=i.getStringExtra("email");

        if(!TextUtils.isEmpty(user_name))
        {
            String id=databaseReference.push().getKey();
            UsersData usersData=new UsersData(id,user_name,university,term_val,email,year);
            databaseReference.child(id).setValue(usersData);
            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Faild to upload", Toast.LENGTH_SHORT).show();
        }


    }
}
