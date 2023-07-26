package com.example.carbonegy2;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class ThirdFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //fragment manager to manage fragment flow
        FragmentManager fragmentManager = getChildFragmentManager();

        Button friendsbutton = view.findViewById(R.id.friendsbutton);
        friendsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView9, LeaderboardFriends.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();
            }
        });

        Button nearbybutton = view.findViewById(R.id.nearbybutton);
        nearbybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView9, LeaderboardNearby.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // name can be null
                        .commit();
            }
        });
    }
}

