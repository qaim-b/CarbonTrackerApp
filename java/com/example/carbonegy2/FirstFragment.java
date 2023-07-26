package com.example.carbonegy2;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends Fragment {

    RecyclerView rv,recordsrv;
    ArrayList<String> dataSource;
    LinearLayoutManager linearLayoutManager;
    MyRvAdapter myRvAdapter;

    private MyDBHelper dbHelper;
    private TextView Percentage;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        // Find the ProgressBar and set it to be determinate
        ProgressBar progressBar = view.findViewById(R.id.progress_bar);
        progressBar.setIndeterminate(false);

        // Set the progress value based on the current emission value
        dbHelper = new MyDBHelper(getActivity());
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        String email = sharedPref.getString("email", "default_email");
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        MyDBHelper myDBHelper = new MyDBHelper(getContext());
        Log.d("FirstFragmnet", "Email at firstfragment is: " + email);
        int currentEmission = myDBHelper.getTotalEmissions(email);

        int inputNumber = myDBHelper.getUserGoal(email);

        Log.d("FirstFragmnet", "limitline at firstfragment is: " + inputNumber);
        int averageEmission = dbHelper.getAverageEmission(email);
        Log.d("FirstFragment", "the users average emissions are " + averageEmission);
        double inputnumberpercent = inputNumber / 100.0;
        double emissionsGoal = averageEmission- (inputnumberpercent * averageEmission);
        double progress = (currentEmission / emissionsGoal) * 100;
        Log.d("FirstFragment", "percentage goal is  : " + inputnumberpercent);
        Log.d("FirstFragment", "current emission in firstfragment is : " + currentEmission);
        Log.d("FirstFragment", "This users goal is " + emissionsGoal);


        Log.d("FirstFragment", "progress bar value " + progress);
        progressBar.setMax(100);
        Double newData = new Double(progress);
        int progressdouble = newData.intValue();
        if (progressdouble<100) {
            progressBar.setProgress(progressdouble,true);
            progressBar.invalidate();
        }else if(progressdouble>100)
            progressBar.setProgress(100,true);
        else
            progressBar.setProgress(0,true);

        Percentage = view.findViewById(R.id.percentageview);
        if(progressdouble<100) {
            Percentage.setText(String.valueOf(progressdouble) + "%");
        }else if(progressdouble>100)
            Percentage.setText("100%");
        else
            Percentage.setText("0%");

        // Create a new EmissionRecordRvAdapter
        recordsrv = view.findViewById(R.id.recordsrv);
        recordsrv.setLayoutManager(new LinearLayoutManager(getActivity()));

        int userID = dbHelper.getUserIdFromEmail(email);
        List emissionRecordList = dbHelper.getEmissionRecords(userID);
        EmissionRecordRvAdapter adapter = new EmissionRecordRvAdapter(emissionRecordList, getActivity());


// Set the adapter to the RecyclerView
        recordsrv.setAdapter(adapter);

// Set the layout manager for the RecyclerView
        recordsrv.setLayoutManager(new LinearLayoutManager(getActivity()));

// Notify the adapter that the data has changed
        adapter.notifyDataSetChanged();



















        rv = view.findViewById(R.id.horizontalRv);

        //save data source
        dataSource = new ArrayList<>();
        dataSource.add("The world can emit more than 2.4 million pounds of CO2 per second.");
        dataSource.add("Travelling by bike or foot emits a fraction of the CO2 *of a car*.");
        dataSource.add("Fossil fuel account for around 80-90 per cent of our CO2 emissions.");
        dataSource.add("Buildings emit 39 percent of the CO2 **released in the US**.");
        dataSource.add("The more paper you use,the more you are contributing to carbon emissions.");
        dataSource.add("On the scale of CO2 emissions,human sources far outweigh volcanoes.");

//        linearLayoutManager = (new LinearLayoutManager(getActivity()));
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        linearLayoutManager = new LinearLayoutManager(getActivity(),FirstFragment.classLinearLayoutManager.HORIZONTAL, false);

        myRvAdapter = new MyRvAdapter(dataSource);
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(myRvAdapter);

        return view;
    }

    class MyRvAdapter extends RecyclerView.Adapter<MyRvAdapter.MyHolder>{
        ArrayList<String> data;

        public MyRvAdapter(ArrayList<String> data) {
            this.data = data;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            View view = LayoutInflater.from(FirstFragment.this).inflate(R.layout.rv_item, parent, false);
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);
            return new MyHolder(view);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.funFact.setText(data.get(position));

        }

        class MyHolder extends RecyclerView.ViewHolder{

            TextView funFact;

            public MyHolder(@NonNull View itemView) {
                super(itemView);
                funFact = itemView.findViewById(R.id.funFact);
            }
        }

    }



}