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
        findViewById<Button>(R.id.btnTest).setOnClickListener {
            insertCode()
        }
        findViewById<Button>(R.id.btnShowGraphic).setOnClickListener {
            callShowGraphics()
        }
        findViewById<Button>(R.id.btnShowReports).setOnClickListener {
            callShowReports()
        }
    }

    private fun insertCode() {
        findViewById<EditText>(R.id.inputText).setText(
            "def Barras{\n" +
                    "                titulo: \"Grafica 1\";\n" +
                    "                ejex:[\"item1\", \"item2\"];\n" +
                    "                ejey:[5, 9];\n" +
                    "                unir:[{0,1}, {1,0}];\n" +
                    "        #Aqui termine\n" +
                    "}\n" +
                    "#Aqui estoy definiendo varios gráficos de Pie\n" +
                    "Def Pie{\n" +
                    "   titulo: \"Grafica 2\";\n" +
                    "   tipo: Porcentaje;\n" +
                    "   etiquetas: [\"Compi1\", \"Compi2\"];\n" +
                    "   valores:[5+1, 10];\n" +
                    "   unir:[{0,1}, {1,0}];\n" +
                    "   extra: \"Resto\";\n" +
                    "}\n" +
                    "Def Pie{\n" +
                    "   titulo: \"Grafica 3\";\n" +
                    "   tipo: Cantidad;\n" +
                    "   etiquetas: [\"Compi1\", \"Compi2\"];\n" +
                    "   valores:[70, 120];\n" +
                    "   total: 250;\n" +
                    "   unir:[{0,1}, {1,0}];\n" +
                    "   extra: \"Resto\";\n" +
                    "}\n" +
                    "#Aqui ejecutar la gráfica 1 y puedo ejecutar más de una grafica\n" +
                    "Ejecutar (\"Grafica 2\");\n" +
                    "Ejecutar (\"Grafica 3\");\n" +
                    "Ejecutar (\"Grafica 1\");\n" +
                    "Ejecutar (\"Grafica 2\");\n" +
                    "Ejecutar (\"Grafica 3\");\n" +
                    "Ejecutar (\"Grafica 1\");\n"
        )
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
            findViewById<Button>(R.id.btnShowGraphic).isEnabled = false
            findViewById<Button>(R.id.btnShowReports).isEnabled = false
        }
    }

    private fun enableBtnShowGraphics() {
        findViewById<Button>(R.id.btnShowGraphic).isEnabled = true
        findViewById<Button>(R.id.btnShowReports).isEnabled = true
    }

    private fun callShowGraphics() {
        try {
            val intent = Intent(applicationContext, ShowGraphics::class.java)
            intent.putExtra("GBars", create.allGBar)
            intent.putExtra("GPies", create.allGPie)
            intent.putExtra("Run", create.runGraphics)
            startActivity(intent)
        } catch (ex: Exception) {
            findViewById<Button>(R.id.btnShowGraphic).isEnabled = false
            findViewById<Button>(R.id.btnShowReports).isEnabled = false
        }
    }

    private fun callShowReports() {
        try {
            val intent = Intent(applicationContext, ShowReports::class.java)
            intent.putExtra("Lexer", lexer.errors)
            intent.putExtra("Parser", sintac.errors)
            intent.putExtra("Create", create)
            startActivity(intent)
        } catch (ex: Exception) {
            findViewById<Button>(R.id.btnShowGraphic).isEnabled = false
            findViewById<Button>(R.id.btnShowReports).isEnabled = false
        }

    }
}