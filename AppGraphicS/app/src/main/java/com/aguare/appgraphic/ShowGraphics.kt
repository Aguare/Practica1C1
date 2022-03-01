package com.aguare.appgraphic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.aguare.appgraphic.Back.GenerateG.GenerateChart
import com.aguare.appgraphic.Back.Graphics.GBars
import com.aguare.appgraphic.Back.Graphics.GPie
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart

class ShowGraphics : AppCompatActivity() {

    private lateinit var generate: GenerateChart
    private lateinit var gBars: ArrayList<GBars>
    private lateinit var pie: ArrayList<GPie>
    private lateinit var run: ArrayList<String>
    private lateinit var lay: LinearLayout
    private lateinit var bars: ArrayList<BarChart>
    private lateinit var pies: ArrayList<PieChart>
    private lateinit var createdB: ArrayList<String>
    private lateinit var createdP: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_graphics)
        generate = GenerateChart()
        lay = findViewById(R.id.layout)
        var gB = intent.getSerializableExtra("GBars")
        var gPie = intent.getSerializableExtra("GPies")
        var r = intent.getSerializableExtra("Run")
        if (gB != null && gPie != null && r != null) {
            gBars = gB as ArrayList<GBars>
            pie = gPie as ArrayList<GPie>
            run = r as ArrayList<String>
            createdB = ArrayList()
            createdP = ArrayList()
            pies = ArrayList()
            bars = ArrayList()
            createGraphics()
        }
    }

    private fun createGraphics() {
        for (e in run) {
            for (bar in gBars) {
                if (bar.title.equals(e)) {
                    var barNew = bar.clone()
                    createBarGraphic(barNew)
                }
            }
            for (p in pie) {
                if (p.title.equals(e)) {
                    var piNew = p.clone();
                    createPieGraphic(piNew)
                }
            }
        }
    }

    private fun createBarGraphic(gBar: GBars) {
        var context = lay.context
        gBar.sortJoin()
        var barChart = generate.createChartBar(context, gBar)
        barChart.minimumHeight = 500
        lay.addView(barChart);
    }

    private fun createPieGraphic(pie: GPie) {
        var context = lay.context
        pie.sortJoin()
        var pieChart = generate.createChartPie(context, pie)
        pieChart.minimumHeight = 500
        lay.addView(pieChart)
    }

}