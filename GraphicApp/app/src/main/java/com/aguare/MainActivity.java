package com.aguare;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private GraphicsView graphicsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entries_main);
        Button btn1 = (Button) findViewById(R.id.btnCompile);
        Button btn2 = (Button) findViewById(R.id.btnGraphics);
        Button btn3 = (Button) findViewById(R.id.btnReports);
        graphicsView = new GraphicsView();
        //btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn1.setOnClickListener(view ->
                compileCode()
                );
        btn2.setOnClickListener(view ->
            callShowGraphics()
        );
        //LinearLayout tmp = (LinearLayout) findViewById(R.id.layoutMain);
        //tmp.getContext();
    }

    private void compileCode(){
        Toast.makeText(this, "Se presionó compilar", Toast.LENGTH_LONG).show();
    }

    private void callShowGraphics(){
        Intent intent = new Intent(this, GraphicsView.class);
    }


}