package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;


public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    CircularProgressButton circularProgressButton;
    Button login;
    TextView sign;
    ImageView imageView;
    EditText Email;
    EditText userPass;
    GoogleApiClient googleApiClient;
    GoogleSignInClient mGoogleSignInClient;
    SignInButton signInButton;
    int RC_SIGN_IN=101;

    public static final String SHARED_PREF="sharedPrefs";
    public static final String user_email="email";
    public static final String ACCOUNT="account";
    public static String ID;
    int id_val;
    String mail;

//GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        googleApiClient=new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,gso)
                .build();

        signInButton=(SignInButton)findViewById(R.id.googleBtn);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        // signInButton.setScopes(gso.getScopeArray());
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent signInIntent =Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        sign=(TextView)findViewById(R.id.button);
        circularProgressButton=(CircularProgressButton) findViewById(R.id.loginbtn);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myintent =new Intent(MainActivity.this,Main3Activity.class);
                startActivity(myintent);

            }
        });

        circularProgressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AsyncTask<String,String,String> demoDown= new AsyncTask<String, String, String>() {

                    @Override
                    protected String doInBackground(String... Params) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        return "done";
                    }

                    @Override
                    protected void onPostExecute(String s) {
                        if(s.equals("done"))
                        {
                            Toast.makeText(MainActivity.this, "Done", Toast.LENGTH_SHORT).show();
                            circularProgressButton.doneLoadingAnimation(Color.parseColor("#333639"), BitmapFactory.decodeResource(getResources(),R.drawable.ic_done_white_48dp));
                        }

                    }
                };
                circularProgressButton.startAnimation();
                demoDown.execute();
                loginclick();
            }
        });
    }



    GoogleSignInAccount account;
    String acc;

    public void loginclick() {


        Email= (EditText) findViewById(R.id.user);
        userPass= (EditText) findViewById(R.id.pass);

        String email=Email.getText().toString();
        String password=userPass.getText().toString();
        if(email.isEmpty())
        {
            Email.setError("Email required");
            Email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            Email.setError("Unvallied Email");
            Email.requestFocus();
            return;
        }
        if(password.isEmpty())
        {
            userPass.setError("Password is required");
            userPass.requestFocus();
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"true",Toast.LENGTH_LONG).show();
                    mail=mAuth.getCurrentUser().getEmail();
                    id_val=1;
                    save_data();
                    Intent intent =new Intent(MainActivity.this,Main2Activity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            // Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()) {
            account = result.getSignInAccount();
            Toast.makeText(MainActivity.this,"Trueee  "+account.getEmail(), Toast.LENGTH_LONG).show();
            acc=account.getEmail();
            id_val=2;
            save_google();
            Intent intent =new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
        }
        else
            Toast.makeText(MainActivity.this,"False", Toast.LENGTH_LONG).show();
    }

    public void save_data()
    {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(user_email,mail);
        editor.putInt(ID,id_val);
        editor.apply();
    }
    public void save_google()
    {
        SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREF,MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(ACCOUNT,acc);
        editor.putInt(ID,id_val);
        editor.apply();
    }



}
