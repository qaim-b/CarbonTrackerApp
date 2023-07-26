package com.example.carbonegy2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {
    private MyDBHelper dbHelper;
    private EditText inputName, inputEmail, inputAvgEmission, inputPhone;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Initialize views
        inputName = findViewById(R.id.editName);
        inputEmail = findViewById(R.id.editEmail);
        inputAvgEmission = findViewById(R.id.editAvgEmission);
        inputPhone = findViewById(R.id.editPhone);

        // Initialize DB helper
        dbHelper = new MyDBHelper(this);

        // Get current user's email
        email = getIntent().getStringExtra("email");




        // Set onClickListener for save button
        Button saveButton = findViewById(R.id.btnSave);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get input from views
                String name = inputName.getText().toString().trim();
                String email = inputEmail.getText().toString().trim();
                String avgEmission = inputAvgEmission.getText().toString().trim();
                String phone = inputPhone.getText().toString().trim();

                // Check if any fields are empty
                if (name.isEmpty() || email.isEmpty() || avgEmission.isEmpty() || phone.isEmpty()) {
                    // Show an error message to the user
                    Toast.makeText(EditProfileActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Update user's information in the database
                    dbHelper.updateUser(email, name, avgEmission, phone);

                    // Start MainActivity
                    startActivity(new Intent(EditProfileActivity.this, ForthFragment.class));

                }
            }
        });
    }
}
