package com.example.school_hero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Register extends AppCompatActivity {

    EditText reName, reUsername, rePassword;
    Button btn;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        reName = findViewById(R.id.name);
        reUsername = findViewById(R.id.regusername);
        rePassword = findViewById(R.id.regpassword);
        btn = findViewById(R.id.regconfirm);
        spinner = findViewById(R.id.spinner);

        // Database object
        Database db = new Database(getApplicationContext(), "schoolherodb", null, 1);

        // Making spinner working
        List<String> states = Arrays.asList("Headmaster", "Student", "Teacher", "Parent", "Psycologist");
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, states);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = reName.getText().toString();
                String username = reUsername.getText().toString();
                String password = rePassword.getText().toString();
                // Spinner get()
                String state = spinner.getSelectedItem().toString();

                if (username.length()==0 || password.length()==0) {
                    Toast.makeText(getApplicationContext(), "Please write in the fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Insert in database
                    db.register(name, username, password, state);
                    Toast.makeText(getApplicationContext(), "User insert in application", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, Login.class));
                }

            }
        });
    }

}