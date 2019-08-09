package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String SHARED_PREF = "sharedPrefs";
    TextView username_header;
  //  FirebaseDatabase database;
 //   DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Create_Post.class);
                startActivity(intent);
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
/*
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("matrial");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                matrial mat = dataSnapshot.getValue(matrial.class);
                //  Toast.makeText(Home.this, mat.title+" "+mat.description, Toast.LENGTH_SHORT).show();
                Log.v("ammm"," value is : " + mat.title);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Home.this, "What !", Toast.LENGTH_SHORT).show();
            }
        });
/*
        username_header = findViewById(R.id.username_header);
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);
        String username = sharedPreferences.getString(fir_name, "") + " " + sharedPreferences.getString(sec_name, "");

        username_header.setText(username);
*/
/*
        SharedPreferences shared = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);

        String f_name = shared.getString(fir_name, "");
        String l_name = shared.getString(sec_name, "");
        String user_name = f_name + " " + l_name;
        String email = shared.getString(ACCOUNT,"");


        TextView username_header = findViewById(R.id.username_header);
        username_header.setText(user_name);

        TextView email_header = findViewById(R.id.email_header);
        email_header.setText(email);
*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
        } else if (id == R.id.nav_account) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();

        } else if (id == R.id.nav_classroom) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ClassroomFragment()).commit();

        } else if (id == R.id.nav_favorite) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavoriteFragment()).commit();

        } else if (id == R.id.nav_tools) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ToolsFragment()).commit();

        } else if (id == R.id.nav_share) {
            Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_send) {
            Toast.makeText(this, "Send", Toast.LENGTH_SHORT).show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;

    }
}
