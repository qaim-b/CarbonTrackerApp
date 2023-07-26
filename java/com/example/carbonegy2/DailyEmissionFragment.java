package com.example.carbonegy2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.InputFilter;
import android.text.InputType;
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
 * Use the {@link DailyEmissionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DailyEmissionFragment extends Fragment {

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

    public DailyEmissionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DailyEmissionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DailyEmissionFragment newInstance(String param1, String param2) {
        DailyEmissionFragment fragment = new DailyEmissionFragment();
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
//        return inflater.inflate(R.layout.fragment_daily_emission, container, false);

        View view = inflater.inflate(R.layout.fragment_daily_emission, container, false);
        chart = view.findViewById(R.id.dailychart);
        // Set up the bar chart
        BarChart chart = view.findViewById(R.id.dailychart);
        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 25));
        entries.add(new BarEntry(1, 50));
        entries.add(new BarEntry(2, 75));
        entries.add(new BarEntry(3, 100));
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
//        LimitLine limitLine = new LimitLine(100 - inputNumber, "Your Goal");
//        limitLine.setTextColor(Color.parseColor("#FFFFFF"));
//        limitLine.setTextSize(18);
//        limitLine.setLineColor(Color.parseColor("#E2FB4D"));
//        limitLine.setLineWidth(1f);

        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.addLimitLine(limitLine);

        // Update the chart
//        chart.invalidate();

        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setDrawGridLines(false);
        yAxisRight.setDrawAxisLine(false);
        yAxisRight.setDrawLabels(false);

        chart.setDrawBorders(false);
        chart.getBarData().setDrawValues(false);
        chart.getLegend().setEnabled(false);
        chart.setExtraBottomOffset(15f);

        chart.getDescription().setEnabled(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}));
//
//        Button button = view.findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Show the number input dialog
//                showNumberInputDialog();
//
//                // Update the value of inputNumber in the shared preferences
//                SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putInt("input_number", inputNumber);
//                editor.apply();
//
//                // Remove all existing limit lines
//                YAxis leftAxis = chart.getAxisLeft();
//                leftAxis.removeAllLimitLines();
//
//                // Add a new limit line at the updated value of inputNumber
//                LimitLine limitLine = new LimitLine(100 - inputNumber, "Your Goal");
//                limitLine.setTextColor(Color.parseColor("#FFFFFF"));
//                limitLine.setTextSize(18);
//                limitLine.setLineColor(Color.parseColor("#E2FB4D"));
//                limitLine.setLineWidth(1f);
//                leftAxis.addLimitLine(limitLine);
//
//                // Update the chart
//                chart.invalidate();
//
//                editor.putInt("input_number", inputNumber);
//                editor.apply();
//                textView.setText("Your current goal is to reduce emissions by: " + String.valueOf(inputNumber) + "%");
//            }
//        });
        return view;
    }

//    public void showNumberInputDialog() {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//        builder.setTitle("Enter a number");
//
//        // Set up the input
//        final EditText input = new
//                EditText(context);
//        // Specify the type of input expected; this, for example, sets the input as a number
//        input.setInputType(InputType.TYPE_CLASS_NUMBER);
//        InputFilter[] filters = new InputFilter[1];
//        filters[0] = new InputFilter.LengthFilter(3);  // Set the max length to 3
//        input.setFilters(filters);
//        builder.setView(input);
//
//        // Set up the buttons
//        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                String inputValue = input.getText().toString();
//                if (inputValue.isEmpty()) {
//                    // Show an error message if the input value is empty
//                    Toast.makeText(context, "Enter a number between 1 and 100", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                inputNumber = Integer.parseInt(inputValue);
//
//                if (inputNumber >= 1 && inputNumber <= 100) {
//                    // Update the value of inputNumber in the shared preferences
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putInt("input_number", inputNumber);
//                    editor.apply();
//
//                    // Remove all existing limit lines
//                    YAxis leftAxis = chart.getAxisLeft();
//                    leftAxis.removeAllLimitLines();
//
//                    // Add a new limit line at the updated value of inputNumber
//                    LimitLine limitLine = new LimitLine(100 - inputNumber, "Your Goal");
//                    limitLine.setTextColor(Color.parseColor("#FFFFFF"));
//                    limitLine.setTextSize(18);
//                    limitLine.setLineColor(Color.parseColor("#E2FB4D"));
//                    limitLine.setLineWidth(1f);
//                    leftAxis.addLimitLine(limitLine);
//
//                    // Update the chart
//                    chart.invalidate();
//                    textView.setText("Your current goal is to reduce emissions by: " + String.valueOf(inputNumber) + "%");
//                } else {
//                    Toast.makeText(context, "Enter a number between 1 and 100", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//
//        builder.show();
//    }
}