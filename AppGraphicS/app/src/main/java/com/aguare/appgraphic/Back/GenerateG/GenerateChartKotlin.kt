package Back.GenerateG

import com.github.mikephil.charting.charts.BarChart
import android.content.Context
import android.graphics.Color
import com.aguare.appgraphic.Back.Graphics.GBars
import com.aguare.appgraphic.Back.Graphics.GPie
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.DataSet
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.formatter.PercentFormatter
import java.util.ArrayList

class GenerateChartKotlin {

    private val colors = intArrayOf(Color.CYAN, Color.RED, Color.GREEN, Color.BLUE)

    fun createChartBar(context: Context?, gBar: GBars): BarChart {
        var barChart = BarChart(context)
        barChart = getSameChart(barChart, gBar.title, Color.RED, Color.WHITE, 3000) as BarChart
        barChart.setDrawGridBackground(true)
        barChart.setDrawBarShadow(true)
        barChart.data = getBarData(gBar.axis_y)
        barChart.invalidate()
        axisX(barChart.xAxis, gBar.axis_x)
        axisLeft(barChart.axisLeft)
        axisRight(barChart.axisRight)
        barChart.legend.isEnabled = false
        return barChart
    }

    fun createChartPie(context: Context?, gPie: GPie): PieChart {
        var pieChart = PieChart(context)
        pieChart = getSameChart(pieChart, gPie.title, Color.RED, Color.WHITE, 3000) as PieChart
        pieChart.holeRadius = 10f
        pieChart.transparentCircleRadius = 12f
        pieChart.isDrawHoleEnabled = false
        pieChart.data = getPieData(gPie.values)
        pieChart.invalidate()
        return pieChart
    }

    private fun getSameChart(
        chart: Chart<*>,
        description: String,
        textColor: Int,
        background: Int,
        animation: Int
    ): Chart<*> {
        chart.description.text = description
        chart.description.textSize = 15f
        chart.description.textColor = textColor
        chart.setBackgroundColor(background)
        chart.animateY(animation)
        return chart
    }

    private fun legend(chart: Chart<*>, columns: ArrayList<String>) {
        val legend = chart.legend
        legend.form = Legend.LegendForm.CIRCLE
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        val entries = ArrayList<LegendEntry>()
        for (i in columns.indices) {
            val entry = LegendEntry()
            entry.formColor = colors[i]
            entry.label = columns[i]
            entries.add(entry)
        }
        legend.setCustom(entries)
    }

    private fun getBarEntries(mount: ArrayList<Double>): ArrayList<BarEntry> {
        val entries = ArrayList<BarEntry>()
        for (i in mount.indices) {
            entries.add(BarEntry(i.toFloat(), ("" + mount[i]).toFloat()))
        }
        return entries
    }

    private fun getPieEntries(mount: ArrayList<Double>): ArrayList<PieEntry> {
        val entries = ArrayList<PieEntry>()
        for (i in mount.indices) {
            entries.add(PieEntry(("" + mount[i]).toFloat()))
        }
        return entries
    }

    private fun axisX(x: XAxis, dates: ArrayList<String>) {
        x.isGranularityEnabled = true
        x.position = XAxis.XAxisPosition.BOTTOM
        x.valueFormatter = IndexAxisValueFormatter(dates)
        x.isEnabled = false
    }

    private fun axisLeft(y: YAxis) {
        y.spaceTop = 10f
        y.axisMinimum = 0f
    }

    private fun axisRight(y: YAxis) {
        y.isEnabled = false
    }

    private fun getData(data: DataSet<*>): DataSet<*> {
        data.setColors(*colors)
        data.valueTextSize = Color.WHITE.toFloat()
        data.valueTextSize = 10f
        return data
    }

    private fun getBarData(data: ArrayList<Double>): BarData {
        val barDataSet = getData(BarDataSet(getBarEntries(data), "")) as BarDataSet
        barDataSet.barShadowColor = Color.GRAY
        val barData = BarData(barDataSet)
        barData.barWidth = 0.45f
        return barData
    }

    private fun getPieData(data: ArrayList<Double>): PieData {
        val pieDataSet = getData(PieDataSet(getPieEntries(data), "")) as PieDataSet
        pieDataSet.sliceSpace = 2f
        pieDataSet.valueFormatter = PercentFormatter()
        return PieData(pieDataSet)
    }
}