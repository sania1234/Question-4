package com.lab5.complain_recording_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    EditText name, email, password, confirmPassword;
    Button register_button,loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
        confirmPassword = findViewById(R.id.cpass);
        register_button = findViewById(R.id.register);
        loginButton= findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Login.class);
                startActivity(intent);
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = name.getText().toString();
                String s2 = email.getText().toString();
                String s3 = password.getText().toString();
                String s4 = confirmPassword.getText().toString();
                if(s1.equals("")||s2.equals("")||s3.equals("")||s4.equals("")){
                    Toast.makeText(getApplicationContext(),"Fields are empty",Toast.LENGTH_SHORT).show();
                }
                else {
                    if(s3.equals(s4)){
                        Boolean chkemail = db.chkemial(s2);;
                        if (chkemail==true){
                            Boolean insert = db.insert(s1,s2,s3);
                            if(insert == true){
                                Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                             }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Email Already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(getApplicationContext(),"Password donot match",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
