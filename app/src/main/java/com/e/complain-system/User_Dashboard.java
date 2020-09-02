package com.lab5.complain_recording_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class User_Dashboard extends Activity {

    Button logout, add_complain, view_complain;
    DatabaseHelper db;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__dashboard);
        db = new DatabaseHelper(this);
        final String str;


        //xml references
        logout= findViewById(R.id.logout);
        text = findViewById(R.id.username);
        add_complain= findViewById(R.id.add_complain_button);
        view_complain = findViewById(R.id.view_complain_button);


        //useremail fetch
        Intent intent = getIntent();
        str = intent.getStringExtra("name");
        text.setText("Welcome "+ str );


        //intent to create complain
        add_complain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentforComplain = new Intent(User_Dashboard.this, create_complain.class);
                intentforComplain.putExtra("email" ,str);
                startActivity(intentforComplain);
            }
        });
    }

    public  void logout(View view){
        SharedPreferences sharedpreferences = getSharedPreferences(Login.MYPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.commit();
    }

    public void close(View view){
        finish();
    }
}
