package Back.GenerateG;

import android.content.Context;
import android.graphics.Color;

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

import java.util.ArrayList;

import Back.Graphics.GBars;
import Back.Graphics.GPie;

public class GenerateChart {

    private int[] colors = new int[]{Color.CYAN, Color.RED, Color.GREEN, Color.BLUE};

    public BarChart createChartBar(Context context, GBars gBar) {
        BarChart barChart = new BarChart(context);
        barChart = (BarChart) getSameChart(barChart, gBar.getTitle(), Color.RED, Color.WHITE, 3000);
        barChart.setDrawGridBackground(true);
        barChart.setDrawBarShadow(true);
        barChart.setData(getBarData(gBar.getAxis_y()));
        barChart.invalidate();
        axisX(barChart.getXAxis(), gBar.getAxis_x());
        axisLeft(barChart.getAxisLeft());
        axisRight(barChart.getAxisRight());
        barChart.getLegend().setEnabled(false);
        return barChart;
    }

    public PieChart createChartPie(Context context, GPie gPie) {
        PieChart pieChart = new PieChart(context);
        pieChart = (PieChart) getSameChart(pieChart, gPie.getTitle(), Color.RED, Color.WHITE, 3000);
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setData(getPieData(gPie.getValues()));
        pieChart.invalidate();
        return pieChart;
    }

    private Chart getSameChart(Chart chart, String description, int textColor, int background, int animation) {
        chart.getDescription().setText(description);
        chart.getDescription().setTextSize(15);
        chart.getDescription().setTextColor(textColor);
        chart.setBackgroundColor(background);
        chart.animateY(animation);
        return chart;
    }

    private void legend(Chart chart, ArrayList<String> columns) {
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        ArrayList<LegendEntry> entries = new ArrayList<>();
        for (int i = 0; i < columns.size(); i++) {
            LegendEntry entry = new LegendEntry();
            entry.formColor = colors[i];
            entry.label = columns.get(i);
            entries.add(entry);
        }
        legend.setCustom(entries);
    }

    private ArrayList<BarEntry> getBarEntries(ArrayList<Double> mount) {
        ArrayList<BarEntry> entries = new ArrayList<>();
        for (int i = 0; i < mount.size(); i++) {
            entries.add(new BarEntry(i, Float.parseFloat("" + mount.get(i))));
        }
        return entries;
    }

    private ArrayList<PieEntry> getPieEntries(ArrayList<Double> mount) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (int i = 0; i < mount.size(); i++) {
            entries.add(new PieEntry(Float.parseFloat("" + mount.get(i))));
        }
        return entries;
    }

    private void axisX(XAxis x, ArrayList<String> dates) {
        x.setGranularityEnabled(true);
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setValueFormatter(new IndexAxisValueFormatter(dates));
        x.setEnabled(false);
    }

    private void axisLeft(YAxis y) {
        y.setSpaceTop(10);
        y.setAxisMinimum(0);
    }

    private void axisRight(YAxis y) {
        y.setEnabled(false);
    }

    private DataSet getData(DataSet data) {
        data.setColors(colors);
        data.setValueTextSize(Color.WHITE);
        data.setValueTextSize(10);
        return data;
    }

    private BarData getBarData(ArrayList<Double> data) {
        BarDataSet barDataSet = (BarDataSet) getData(new BarDataSet(getBarEntries(data), ""));
        barDataSet.setBarShadowColor(Color.GRAY);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.45f);
        return barData;
    }

    private PieData getPieData(ArrayList<Double> data) {
        PieDataSet pieDataSet = (PieDataSet) getData(new PieDataSet(getPieEntries(data), ""));
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }
}
