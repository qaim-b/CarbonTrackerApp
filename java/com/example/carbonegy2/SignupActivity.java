package com.example.carbonegy2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    private MyDBHelper dbHelper;
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String EMAIL_KEY = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        dbHelper = new MyDBHelper(this);

        Spinner inputCity = findViewById(R.id.inputCity);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.city, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        inputCity.setAdapter(adapter);

        final EditText inputEmail = findViewById(R.id.FullNameView);
        final EditText inputPassword = findViewById(R.id.EmailView);
        final EditText inputConfirmPassword = findViewById(R.id.inputConPassword);

        Button btnSignup = findViewById(R.id.btnLogin);
        Button btnLogin = findViewById(R.id.btnRegister);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                String confirmPassword = inputConfirmPassword.getText().toString();
                String city = inputCity.getSelectedItem().toString();
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    inputEmail.setError("Invalid email address");
                    return;
                }

                if (email.isEmpty()) {
                    inputEmail.setError("Email is required");
                    return;
                }

                if (password.isEmpty()) {
                    inputPassword.setError("Password is required");
                    return;
                }

                if (confirmPassword.isEmpty()) {
                    inputConfirmPassword.setError("Confirm password is required");
                    return;
                }
                if (password.length() < 8) {
                    inputPassword.setError("Password must be at least 8 characters");
                    return;
                }


                if (!password.equals(confirmPassword)) {
                    inputConfirmPassword.setError("Passwords do not match");
                    return;
                }

                if (dbHelper.userExists(email)) {
                    inputEmail.setError("Email already in use");
                    return;
                }

                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("email", email);
                values.put("password", password);
                values.put("city", city);
                long newRowId = db.insert("users", null, values);
                Log.d("SignupActivity", "Inserting new user:" + " " + email + " " + password);
                db.close();

                if (newRowId > 0) {
                    int average = dbHelper.getAverageEmission(email);
                    dbHelper.updateUserEmission(email, average);
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    intent.putExtra("email", email);
                    startActivity(intent);

                } else {
                    Toast.makeText(SignupActivity.this, "Error: Signup Failed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

