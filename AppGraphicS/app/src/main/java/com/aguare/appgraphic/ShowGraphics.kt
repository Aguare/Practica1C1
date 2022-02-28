package com.aguare.appgraphic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import com.aguare.appgraphic.Back.Control.CreateGraphics
import com.aguare.appgraphic.Back.GenerateG.GenerateChart
import com.aguare.appgraphic.Back.Graphics.GBars

class ShowGraphics : AppCompatActivity() {

    private lateinit var create: CreateGraphics
    private lateinit var generate: GenerateChart
    private lateinit var gBars: ArrayList<GBars>
    private lateinit var lay: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_graphics)
        generate = GenerateChart()
        lay = findViewById(R.id.layout)
        var received = intent.getSerializableExtra("Data")
        if (received != null) {
            create = received as CreateGraphics
            createGraphics()
        }
    }

    fun createGraphics() {
        gBars = create.allGBar
        var count = 0
        while (gBars.size <= count) {
            count++
            var context = lay.context
            lay.addView(generate.createChartBar(context, gBars.get(count)))
        }
    }
}