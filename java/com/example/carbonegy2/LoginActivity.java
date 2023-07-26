package com.example.carbonegy2;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private MyDBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDBHelper = new MyDBHelper(this);

        Button btn1 = (Button) findViewById(R.id.btnLogin);
        Button btn2 = (Button) findViewById(R.id.btnRegister);
        final EditText inputEmail = (EditText) findViewById(R.id.FullNameView);
        final EditText inputPassword = (EditText) findViewById(R.id.EmailView);

        btn1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please fill in all fields", Toast.LENGTH_LONG).show();
                } else {
                    boolean isValid = myDBHelper.isValidUser(email, password);
                    if (isValid) {


                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        intent.putExtra("email", email);
                        startActivity(intent);

                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid email or password", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
            }
        });
    }
}
