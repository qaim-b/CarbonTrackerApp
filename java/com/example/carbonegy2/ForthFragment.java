package com.example.carbonegy2;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForthFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForthFragment extends Fragment {

    private MyDBHelper dbHelper;
    private TextView nameView, emailView, avgEmissionView, phoneView, city;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ForthFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ForthFragment newInstance(String param1, String param2) {
        ForthFragment fragment = new ForthFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_forth, container, false);
        View view = inflater.inflate(R.layout.fragment_forth, container, false);

        // Initialize views
        nameView = view.findViewById(R.id.FullNameView);
        emailView = view.findViewById(R.id.EmailView);
        avgEmissionView = view.findViewById(R.id.AverageEmissionView);
        phoneView = view.findViewById(R.id.phoneNumberView);
        city = view.findViewById(R.id.CityView);


        // Initialize DB helper
        dbHelper = new MyDBHelper(getActivity());

        // Get current user's email
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "default_email");


        Log.d("ForthFragment", "Email: " + email);


        showUserDetails(email);




//        setContentView(R.layout.fragment_editprofile);

        Button btn1 = (Button) view.findViewById(R.id.btnEditInfo);
        Button btn2 = (Button) view.findViewById(R.id.btnSignOut);

        btn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(getActivity(), EditProfileActivity.class));
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });

        return view;

    }


    private void showUserDetails(String email) {
        Cursor userCursor = dbHelper.getUserDetails(email);
        if (userCursor.getCount() > 0) {
            userCursor.moveToFirst();
            String name = userCursor.getString(userCursor.getColumnIndex("name"));
            String phone = userCursor.getString(userCursor.getColumnIndex("phone"));
            String city = userCursor.getString(userCursor.getColumnIndex("city"));
            int avgEmission = userCursor.getInt(userCursor.getColumnIndex("average_emission"));
            nameView.setText(name);
            avgEmissionView.setText(String.valueOf(avgEmission));
            emailView.setText(email);
            phoneView.setText(phone);
            this.city.setText(city);
        } else {
            Log.e("ForthFragment", "User not found");
        }
        userCursor.close();
    }



}