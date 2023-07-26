package com.example.carbonegy2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    MyDBHelper dbHelper;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String email = getIntent().getStringExtra("email");
        Log.d("MainActivity", "Email: " + email);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("email", email);
        editor.apply();



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new MyDBHelper(this);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        NavController navController = Navigation.findNavController(this, R.id.fragment);
        Bundle bundle = new Bundle();
        bundle.putString("email", email);
        navController.setGraph(navController.getGraph(), bundle);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);



        BottomNavigationView a=(BottomNavigationView) findViewById(R.id.bottomNavigationView);
        a.getMenu().getItem(2).setEnabled(false);
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Fragment fragment = new recordFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment,fragment);
                                fragmentTransaction.commit();

            }
        });

    }
}
