package com.boratsinc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.boratsinc.Model.Model;
import com.boratsinc.Model.RatSighting;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    private Model model = Model.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        LineChart lineChart = (LineChart) findViewById(R.id.chart);

        model.loadDummyData();

        List<Entry> entries = convertDataSetToEntry(model.getSightings());

        LineDataSet dataset = new LineDataSet(entries, "# of Calls");

        Log.d("APP", "Made dataset with : " + entries.size());

        LineData data = new LineData(dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        // the labels that should be drawn on the XAxis
        final String[] months = new String[] { "January", "February",  "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };

        IAxisValueFormatter formatter = new IAxisValueFormatter() {

            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return months[(int) value];
            }
        };

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setGranularity(2f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);
        //End setting x-axis labels

        dataset.setDrawFilled(true);

        lineChart.setData(data);
        lineChart.animateY(5000);

        lineChart.getDescription().setText("Average Calls per Month");

    }

    private List<Entry> convertDataSetToEntry(List<RatSighting> data) {
        List<Entry> entries = new ArrayList<>();

        int[] months = new int[12];

        for (RatSighting d : data) {
            int month = Integer.valueOf(d.getCreated().substring(0,2));
            months[month]++;
        }

        for (int x=0;x<months.length;x++) {
            entries.add(new Entry(x, months[x]));
        }


        return entries;
    }
}
