package com.aguare.appgraphic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TableLayout
import android.widget.Toast
import com.aguare.appgraphic.Back.Control.CreateGraphics
import com.aguare.appgraphic.Back.Control.ErrorDesc
import com.aguare.appgraphic.Back.Control.Operation
import com.aguare.appgraphic.Back.GenerateG.CreateTable

class ShowReports : AppCompatActivity() {

    private lateinit var table1: TableLayout
    private lateinit var table2: TableLayout
    private lateinit var table3: TableLayout
    private lateinit var parse: ArrayList<ErrorDesc>
    private lateinit var lexer: ArrayList<ErrorDesc>
    private lateinit var operations: ArrayList<Operation>
    private lateinit var create: CreateGraphics


    private var headers = arrayOf("Tipo Gráfico", "Cantidad")
    private var h2 = arrayOf("Lexema", "Línea", "Columna", "Tipo", "Descripción")
    private var h3 = arrayOf("Operador", "Línea", "Columna", "Operación realizada")
    private lateinit var rows: ArrayList<Array<String>>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.show_reports)
        table1 = findViewById(R.id.table1)
        table2 = findViewById(R.id.table2)
        table3 = findViewById(R.id.table3)
        var r1 = intent.getSerializableExtra("Lexer")
        var r2 = intent.getSerializableExtra("Parser")
        var r3 = intent.getSerializableExtra("Create")
        if (r1 != null && r2 != null && r3 != null) {
            lexer = r1 as ArrayList<ErrorDesc>
            parse = r2 as ArrayList<ErrorDesc>
            create = r3 as CreateGraphics
            operations = create.operations
        }
        rows = ArrayList<Array<String>>()
        var table1 = CreateTable(table1, applicationContext)
        var table2 = CreateTable(table2, applicationContext)
        var table3 = CreateTable(table3, applicationContext)
        table1.addHeader(headers)
        table1.addData(getRows())
        table2.addHeader(h2)
        table2.addData(getRowsErrors())
        table3.addHeader(h3)
        table3.addData(getRowsOperation())
    }

    private fun getRows(): ArrayList<Array<String>> {
        rows.add(arrayOf("Barras", "" + create.allGBar.size))
        rows.add(arrayOf("Pie", "" + create.allGPie.size))
        return rows
    }

    private fun getRowsErrors(): ArrayList<Array<String>> {
        rows.clear()
        for (e in lexer) {
            rows.add(arrayOf(e.content, "" + e.line, "" + e.column, e.typeError, e.msjInfo))
        }
        for (e in parse) {
            rows.add(arrayOf(e.content, "" + e.line, "" + e.column, e.typeError, e.msjInfo))
        }
        return rows
    }

    private fun getRowsOperation(): ArrayList<Array<String>> {
        rows.clear()
        for (e in operations) {
            rows.add(arrayOf(e.type_operation, "" + e.line, "" + e.column, e.operation))
        }
        return rows
    }
}