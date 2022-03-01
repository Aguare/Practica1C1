package com.aguare.appgraphic.Back.GenerateG;

import android.content.Context;
import android.graphics.Color;

import com.aguare.appgraphic.Back.Control.ErrorDesc;
import com.aguare.appgraphic.Back.Graphics.GBars;
import com.aguare.appgraphic.Back.Graphics.GPie;
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
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;


public class GenerateChart {

    private int[] colors = new int[]{Color.GREEN, Color.YELLOW, Color.CYAN, Color.BLUE};
    public ErrorDesc error;

    public BarChart createChartBar(Context context, GBars gBar) {
        BarChart barChart = new BarChart(context);
        barChart = (BarChart) getSameChart(barChart, gBar.getTitle(), 3000);
        barChart.setDrawGridBackground(true);
        barChart.setDrawBarShadow(true);
        barChart.setData(getBarData(gBar.getAxis_y(), gBar));
        barChart.invalidate();
        axisX(barChart.getXAxis(), gBar.getAxis_x(), gBar);
        axisLeft(barChart.getAxisLeft());
        axisRight(barChart.getAxisRight());
        barChart.getLegend().setEnabled(true);
        legend(barChart, gBar.getAxis_x());
        return barChart;
    }

    private Chart getSameChart(Chart chart, String description, int animation) {
        chart.getDescription().setText(description);
        chart.getDescription().setTextSize(15);
        chart.getDescription().setTextColor(Color.RED);
        chart.setBackgroundColor(Color.WHITE);
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


    private void axisX(XAxis x, ArrayList<String> dates, GBars bar) {
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
        data.setColors(ColorTemplate.VORDIPLOM_COLORS);
        data.setValueTextSize(Color.BLACK);
        data.setValueTextSize(15);
        return data;
    }

    public PieChart createChartPie(Context context, GPie gPie) {
        PieChart pieChart = new PieChart(context);
        pieChart = (PieChart) getSameChart(pieChart, gPie.getTitle(), 3000);
        pieChart.setHoleRadius(10);
        pieChart.setTransparentCircleRadius(12);
        pieChart.setDrawHoleEnabled(false);
        pieChart.setData(getPieData(gPie.getValues(), gPie));
        pieChart.invalidate();
        legend(pieChart, gPie.getTags());
        return pieChart;
    }

    private BarData getBarData(ArrayList<Double> data, GBars bar) {
        BarDataSet barDataSet = (BarDataSet) getData(new BarDataSet(getBarEntries(data), ""));
        barDataSet.setBarShadowColor(Color.GRAY);
        BarData barData = new BarData(barDataSet);
        barData.setBarWidth(0.45f);
        return barData;
    }

    private ArrayList<PieEntry> getPieEntries(GPie pie) {
        ArrayList<PieEntry> entries = new ArrayList<>();
        PieEntry pieEntry = changeForPercent(pie);
        ArrayList<Double> mount = pie.getValues();
        for (int i = 0; i < mount.size(); i++) {
            entries.add(new PieEntry(mount.get(i).floatValue()));
        }
        if (pieEntry != null) {
            entries.add(pieEntry);
        }
        return entries;
    }

    private PieEntry changeForPercent(GPie pie) {
        if (pie.getType().equals("Porcentaje")) {
            ArrayList<Double> values = new ArrayList<>();
            pie.setTotal(360.0);
            for (Double val : pie.getValues()) {
                values.add((100 * val) / pie.getTotal());
            }
            pie.setValues(values);
            if (pie.getExtra().equals("Resto")) {
                Double sum = pie.totalValues();
                sum = 100 - sum;
                return new PieEntry(sum.floatValue(), "Resto");
            }
        } else {
            ArrayList<Double> values = new ArrayList<>();
            for (Double val : pie.getValues()) {
                values.add((100 * val) / pie.getTotal());
            }
            pie.setValues(values);
            if (pie.getExtra().equals("Resto")) {
                Double sum = pie.totalValues();
                sum = pie.getTotal() - sum;
                return new PieEntry(sum.floatValue(), "Resto");
            }

        }
        return null;
    }

    private PieData getPieData(ArrayList<Double> data, GPie pie) {
        PieDataSet pieDataSet = (PieDataSet) getData(new PieDataSet(getPieEntries(pie), ""));
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueFormatter(new PercentFormatter());
        return new PieData(pieDataSet);
    }


}
