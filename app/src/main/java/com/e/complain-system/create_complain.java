package com.lab5.complain_recording_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class create_complain extends AppCompatActivity{
    Spinner cat, sev;
    EditText id, des;
    Button submit_button;
    TextView tv;
    String comp_id, category, severity, description;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_complain);

        id = findViewById(R.id.complain_id);
        cat = findViewById(R.id.spinnerCategory);
        sev = findViewById(R.id.spinnerSeverity);
        des = findViewById(R.id.description);
        submit_button = findViewById(R.id.submit_compalin);
        tv = findViewById(R.id.tv);

        Intent intent = getIntent();
        final String str = intent.getStringExtra("email");
        tv.setText(str);


    }

    public void saveComplain(View view) {
        comp_id = id.getText().toString();
        category = cat.getSelectedItem().toString();
        severity = sev.getSelectedItem().toString();
        description = des.getText().toString();
        if (comp_id.isEmpty() || category.isEmpty() || severity.isEmpty() || description.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
        } else {
            boolean seccess_comp_insert = databaseHelper.insert_comp(comp_id, category, severity, description);
            if (seccess_comp_insert == true) {
                Toast.makeText(getApplicationContext(), "Submitted suucessfully", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
