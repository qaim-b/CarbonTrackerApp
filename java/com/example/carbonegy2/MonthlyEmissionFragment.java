package com.example.carbonegy2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthlyEmissionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthlyEmissionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Declare a context object
    BarChart chart;
    Context context;
    int inputNumber;
    TextView textView;
    SharedPreferences sharedPreferences;  // Declare a shared preferences object

    public MonthlyEmissionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MonthlyEmissionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MonthlyEmissionFragment newInstance(String param1, String param2) {
        MonthlyEmissionFragment fragment = new MonthlyEmissionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        inputNumber = sharedPreferences.getInt("input_number", 0);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_monthly_emission, container, false);

        context = getActivity();
        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        chart = view.findViewById(R.id.monthlychart);
        setData();

        context = getActivity();
        sharedPreferences = context.getSharedPreferences("com.example.carbonegy2", Context.MODE_PRIVATE);
        inputNumber = sharedPreferences.getInt("input_number", 0);
        textView = view.findViewById(R.id.card);
        textView.setText("Your current goal is to reduce emissions by: " + String.valueOf(inputNumber) + "%");


        // Set up the bar chart
      /*  BarChart chart = view.findViewById(R.id.monthlychart);
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 25));
        entries.add(new BarEntry(1, 50));
        entries.add(new BarEntry(2, 30));
        entries.add(new BarEntry(3, 10));
        entries.add(new BarEntry(4, 75));
        entries.add(new BarEntry(5, 50));
        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        set.setColor(Color.parseColor("#E2FB4D"));

        BarData data = new BarData(set);

        chart.setData(data);
        chart.setFitBars(true);
        chart.setPinchZoom(false);
        chart.setDoubleTapToZoomEnabled(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawLabels(true);
        xAxis.setTextSize(12f);


        xAxis.setTextColor(Color.parseColor("#d9d9d9"));

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setAxisMaximum(100);
        yAxis.setDrawGridLines(false);
        yAxis.setDrawAxisLine(false);
        yAxis.setDrawLabels(true);
        yAxis.setTextSize(12f);
        yAxis.setTextColor(Color.parseColor("#d9d9d9"));
        LimitLine limitLine = new LimitLine(100 - inputNumber, "Your Goal");
        limitLine.setTextColor(Color.parseColor("#FFFFFF"));
        limitLine.setTextSize(18);
        limitLine.setLineColor(Color.parseColor("#E2FB4D"));
        limitLine.setLineWidth(1f);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.addLimitLine(limitLine);

        // Update the chart
        chart.invalidate();

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setDrawGridLines(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawLabels(false);

        chart.setDrawBorders(false);
        chart.getBarData().setDrawValues(false);

        chart.setExtraBottomOffset(15f);

        chart.getDescription().setEnabled(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Jan", "Feb", "Mar", "Apr", "May", "June"}));*/
//        yAxis.setValueFormatter(new IndexAxisValueFormatter(new int[]{0}));


        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Show the number input dialog
                showNumberInputDialog();
                
                // Update the value of inputNumber in the shared preferences
                
                // Remove all existing limit lines
                YAxis leftAxis = chart.getAxisLeft();
                leftAxis.removeAllLimitLines();

               /* // Add a new limit line at the updated value of inputNumber
                LimitLine limitLine = new LimitLine(100 - inputNumber, "Your Goal");
                limitLine.setTextColor(Color.parseColor("#FFFFFF"));
                limitLine.setTextSize(18);
                limitLine.setLineColor(Color.parseColor("#E2FB4D"));
                limitLine.setLineWidth(1f);
                leftAxis.addLimitLine(limitLine);*/

                // Update the chart
                chart.invalidate();

                
                textView.setText("Your current goal is to reduce emissions by: " + String.valueOf(inputNumber) + "%");
            }
        });

        return view;
    }

    public void showNumberInputDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Enter a number");

        // Set up the input
        final EditText input = new
                EditText(context);
        // Specify the type of input expected; this, for example, sets the input as a number
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(3);  // Set the max length to 3
        input.setFilters(filters);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
                String email = sharedPref.getString("email", "default_email");
                String inputValue = input.getText().toString();
                if (inputValue.isEmpty()) {
                    // Show an error message if the input value is empty
                    Toast.makeText(context, "Enter a number between 1 and 100", Toast.LENGTH_SHORT).show();
                    return;
                }

                inputNumber = Integer.parseInt(inputValue);

                if (inputNumber >= 1 && inputNumber <= 100) {
                    // Update the value of inputNumber in the shared preferences
                    MyDBHelper myDBHelper = new MyDBHelper(context);
                    myDBHelper.addUserGoal(email, inputNumber);

                    Log.d("MonthlyEmissionFragment", "The user stated limit line " + inputNumber);

                    // Remove all existing limit lines
                    YAxis leftAxis = chart.getAxisLeft();
                    leftAxis.removeAllLimitLines();

                  /*  // Add a new limit line at the updated value of inputNumber
                    LimitLine limitLine = new LimitLine(100 - inputNumber, "Your Goal");
                    limitLine.setTextColor(Color.parseColor("#FFFFFF"));
                    limitLine.setTextSize(18);
                    limitLine.setLineColor(Color.parseColor("#E2FB4D"));
                    limitLine.setLineWidth(1f);
                    leftAxis.addLimitLine(limitLine);*/
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("input_number", inputNumber);
                    editor.commit();
                    Log.d("important", inputNumber + "this is the saved inputnumber");

                    // Update the chart
                    chart.invalidate();
                    textView.setText("Your current goal is to reduce emissions by: " + String.valueOf(inputNumber) + "%");
                } else {
                    Toast.makeText(context, "Enter a number between 1 and 100", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    // Method to fetch data from the database and set it to the chart
    private void setData() {
        MyDBHelper dbHelper = new MyDBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String email = sharedPreferences.getString("email", "default_email");

        //Query the emissions table
        Cursor cursor = db.rawQuery("SELECT strftime('%m', date) as month, strftime('%Y', date) as year, SUM(value) as total_emissions FROM emissions WHERE user_id = (SELECT id FROM users WHERE email = ?) GROUP BY strftime('%Y-%m', date)", new String[]{email});

        //Check if the table is empty
        if (cursor.getCount() == 0) {
            Log.d("debug", "Table is empty");
            Toast.makeText(context, "Table is empty", Toast.LENGTH_SHORT).show();
        } else {
            //Retrieve data from table
            List<BarEntry> entries = new ArrayList<>();
            List<String> labels = new ArrayList<>();
            int i = 0;
            while (cursor.moveToNext()) {
                entries.add(new BarEntry(i, cursor.getInt(cursor.getColumnIndex("total_emissions"))));
                int monthNum = Integer.parseInt(cursor.getString(cursor.getColumnIndex("month")));
                String monthName = "";
                switch (monthNum) {
                    case 1:
                        monthName = "Jan";
                        break;
                    case 2:
                        monthName = "Feb";
                        break;
                    case 3:
                        monthName = "Mar";
                        break;
                    case 4:
                        monthName = "Apr";
                        break;
                    case 5:
                        monthName = "May";
                        break;
                    case 6:
                        monthName = "Jun";
                        break;
                    case 7:
                        monthName = "Jul";
                        break;
                    case 8:
                        monthName = "Aug";
                        break;
                    case 9:
                        monthName = "Sep";
                        break;
                    case 10:
                        monthName = "Oct";
                        break;
                    case 11:
                        monthName = "Nov";
                        break;
                    case 12:
                        monthName = "Dec";
                        break;
                }
                labels.add(monthName);
                i++;
            }
            cursor.close();
            BarDataSet set = new BarDataSet(entries, "Total emissions per month");
            set.setColor(Color.parseColor("#E2FB4D"));
            BarData data = new BarData(set);
            chart.setData(data);


            chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));
            chart.getXAxis().setLabelRotationAngle(0);
            chart.getXAxis().setGranularity(1);
            chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            chart.getAxisRight().setEnabled(false);
            chart.getDescription().setEnabled(false);
            chart.animateY(1000);
            chart.setFitBars(true);
            chart.setPinchZoom(false);
            chart.setDoubleTapToZoomEnabled(false);
            chart.getLegend().setEnabled(false);

            XAxis xAxis = chart.getXAxis();
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setDrawGridLines(false);
            xAxis.setDrawAxisLine(false);
            xAxis.setDrawLabels(true);
            xAxis.setTextSize(12f);
            xAxis.setTextColor(Color.parseColor("#d9d9d9"));


            YAxis yAxis = chart.getAxisLeft();
            yAxis.setAxisMinimum(0);
            yAxis.setDrawGridLines(false);
            yAxis.setDrawAxisLine(false);
            yAxis.setDrawLabels(true);
            yAxis.setTextSize(12f);
            yAxis.setTextColor(Color.parseColor("#d9d9d9"));
           /* LimitLine limitLine = new LimitLine(100 - inputNumber, "Your Goal");
            limitLine.setTextColor(Color.parseColor("#FFFFFF"));
            limitLine.setTextSize(18);
            limitLine.setLineColor(Color.parseColor("#E2FB4D"));
            limitLine.setLineWidth(1f);*/
            chart.invalidate();

        }

        cursor.close();
        db.close();
    }


}

