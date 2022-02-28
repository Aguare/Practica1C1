package com.aguare.appgraphic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.aguare.appgraphic.Back.AnalizerPractice
import com.aguare.appgraphic.Back.Control.CreateGraphics
import com.aguare.appgraphic.Back.SintacticParser
import java.io.StringReader

class MainActivity : AppCompatActivity() {

    private var create = CreateGraphics()
    private lateinit var lexer: AnalizerPractice
    private lateinit var sintac: SintacticParser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnShowGraphic).isEnabled = false
        findViewById<Button>(R.id.btnShowReports).isEnabled = false
        findViewById<Button>(R.id.btnCompile).setOnClickListener {
            compileCode()
        }
        findViewById<Button>(R.id.btnTest).setOnClickListener{
            insertCode()
        }
    }

    private fun insertCode(){
        findViewById<EditText>(R.id.inputText).setText("def Barras{\n" +
                "                titulo: \"Grafica 1\";\n" +
                "                ejex:[\"item1\", \"item2\"];\n" +
                "                ejey:[5, 9];\n" +
                "                unir:[{0,1}, {1,0}];\n" +
                "        #Aqui termine\n" +
                "}\n" +
                "#Aqui estoy definiendo varios gráficos de Pie\n" +
                "Def Pie{\n" +
                "   titulo: \"Grafica 2\";\n" +
                "   tipo: Cantidad;\n" +
                "   etiquetas: [\"Compi1\", \"Compi2\"];\n" +
                "   valores:[5, 10];\n" +
                "   total: 25;\n" +
                "   unir:[{0,1}, {1,0}];\n" +
                "   extra: \"Resto\";\n" +
                "}\n" +
                "Def Pie{\n" +
                "   titulo: \"Grafica 3\";\n" +
                "   tipo: Porcentaje;\n" +
                "   etiquetas: [\"Compi1\", \"Compi2\"];\n" +
                "   valores:[70, 120];\n" +
                "   unir:[{0,1}, {1,0}];\n" +
                "   extra: \"Resto\";\n" +
                "}\n" +
                "#Aqui ejecutar la gráfica 1 y puedo ejecutar más de una grafica\n" +
                "Ejecutar (\"Grafica 1\");")
    }

    private fun compileCode() {
        try {
            var text = StringReader(findViewById<EditText>(R.id.inputText).text.toString())
            lexer = AnalizerPractice(text)
            sintac = SintacticParser(lexer)
            sintac.parse()
            create = sintac.create
            enableBtnShowGraphics()
        } catch (ex: Exception) {
            println("valgo para puraa ")
        }
    }

    private fun enableBtnShowGraphics(){
        findViewById<Button>(R.id.btnShowGraphic).isEnabled = true
        findViewById<Button>(R.id.btnShowReports).isEnabled = true
        callShowGraphics()
    }

    private fun callShowGraphics() {
        val intent = Intent(applicationContext, ShowGraphics::class.java)
        intent.putExtra("Data", create)
        startActivity(intent)
    }
}