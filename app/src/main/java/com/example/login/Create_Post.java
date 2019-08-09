package com.example.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Create_Post extends AppCompatActivity {
    String[] uni = {"University","Benha", "cairo", "Ain Shams", "El-Fayom", "Asiwat", "Mansoura", "Manofya", "El-Mania", "El-swais", "Helwan", "El-zagazig", "Bani_Swaif"};
    String[] le = {"level","1st year", "2nd year", "3th year", "4th year", "Others"};
    String[] term = {"term","first term", "second term"};
    String[] sub = {"subject","OOP","Math1","Data Structure","AI"};
    String num="0";

    String[] Dr = {"Dr"};
    RadioButton RE;
    RadioButton RM;
    ImageView add;
    Spinner universty,level,subject,ter;
    EditText doct,title,description;
    String mat,lev,univ,term_val,doctor,post_title,descrip;
    DatabaseReference databaseReference;
    DatabaseReference database;
TextView account_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__post);


        databaseReference= FirebaseDatabase.getInstance().getReference("post");
        database= FirebaseDatabase.getInstance().getReference("matrial");

        title=(EditText)findViewById(R.id.title);

        description=(EditText)findViewById(R.id.description);



        getSupportActionBar().setTitle("Create Post");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        universty=(Spinner)findViewById(R.id.uni);
        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,uni){
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
        universty.setAdapter(adapter);



        level=(Spinner)findViewById(R.id.level);
        ArrayAdapter adapter2=new ArrayAdapter(this,android.R.layout.simple_list_item_1,le){
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
        level.setAdapter(adapter2);


        subject=(Spinner)findViewById(R.id.sub);
        ArrayAdapter adapter3=new ArrayAdapter(this,android.R.layout.simple_list_item_1,sub){
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
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subject.setAdapter(adapter3);



        ter=(Spinner)findViewById(R.id.term);
        ArrayAdapter adapter4=new ArrayAdapter(this,android.R.layout.simple_list_item_1,term){
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
        ter.setAdapter(adapter4);



        doct=findViewById(R.id.Dr);


        RE=(RadioButton)findViewById(R.id.RE);
        RM=(RadioButton)findViewById(R.id.RM);
        RE.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                doct.setVisibility(View.VISIBLE);
            }
        });
        RM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doct.setVisibility(View.GONE);
            }
        });



        /////////////////////////////////////////////////////////////   pdf ////////////
        add=(ImageView)findViewById(R.id.img_create_post);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Create_Post.this, "Add matrial", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       getMenuInflater().inflate(R.menu.home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id =item.getItemId();
        if(id==R.id.post) {
            Toast.makeText(this, "Posting ...  ", Toast.LENGTH_SHORT).show();
            posting();
            upload_matrial();

        }
        return super.onOptionsItemSelected(item);
    }
    private void posting()
    {

            String id=databaseReference.push().getKey();
        univ=universty.getSelectedItem().toString();
        lev=level.getSelectedItem().toString();
        term_val=ter.getSelectedItem().toString();
        mat=subject.getSelectedItem().toString();
        doctor=doct.getText().toString();
        if(universty.getSelectedItemPosition()!=0||level.getSelectedItemPosition()!=0||ter.getSelectedItemPosition()!=0||subject.getSelectedItemPosition()!=0)
        {
            General general1=new General(id,mat,univ,lev,term_val,doctor);
            databaseReference.child(id).setValue(general1);
            Toast.makeText(this, "uploaded post", Toast.LENGTH_LONG).show();
    }
        else
            Toast.makeText(this, "failed post", Toast.LENGTH_LONG).show();

    }
    private void upload_matrial()
    {
        String key;

        key=databaseReference.push().getKey();
        post_title = title.getText().toString();
        descrip=description.getText().toString();
        if(subject.getSelectedItemPosition()==1)
            num="1";
        if(subject.getSelectedItemPosition()==2)
            num="2";
        if(subject.getSelectedItemPosition()==3)
            num="3";
        if(subject.getSelectedItemPosition()==4)
            num="4";
        if(subject.getSelectedItemPosition()==5)
            num="5";

        matrial ma=new matrial(num,post_title,descrip);
        database.child(key).setValue(ma);
        Toast.makeText(this, "Upload matrial", Toast.LENGTH_SHORT).show();
    }
}
