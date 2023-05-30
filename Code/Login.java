package com.example.school_hero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Login extends AppCompatActivity {

    EditText edUsername, edPassword;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.username);
        edPassword = findViewById(R.id.password);
        btn = (Button) findViewById(R.id.login);
        tv = findViewById(R.id.register);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();

                Database db = new Database(getApplicationContext(), "schoolherodb", null, 1);

                if (username.length()==0 || password.length()==0) {
                    Toast.makeText(getApplicationContext(), "Please write in the fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (db.login(username, password) == 1) {
                        switch (db.check(username, password)) {
                            case "Headmaster":
                            // Getting the name of the user
                            Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedpreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("username", username);
                            // Save data with key and value
                            editor.apply();
                            // Jump to Headmaster Interface
                            startActivity(new Intent(Login.this, HeadmasterHome.class));
                            break;
                            case "Teacher":
                                startActivity(new Intent(Login.this, TeacherHome.class));
                                break;
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Register.class));
            }
        });
    }
}
