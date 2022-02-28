package com.aguare.appgraphic.Back.GenerateG

import android.content.Context
import android.view.Gravity
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class CreateTable(private val layout: TableLayout, private val context: Context) {
    private lateinit var headers: Array<String>
    private var data: ArrayList<Array<String>> = ArrayList<Array<String>>()
    private var tableRow: TableRow? = null
    private var txtCell: TextView? = null
    private var indexC = 0
    private var indexR = 0

    fun addHeader(headers: Array<String>) {
        this.headers = headers
        createHeader()
    }

    fun addData(data: ArrayList<Array<String>>) {
        this.data = data
        createDataTable()
    }

    private fun newRow() {
        tableRow = TableRow(context)
    }

    private fun newCell() {
        txtCell = TextView(context)
        txtCell!!.gravity = Gravity.CENTER
        txtCell!!.textSize = 16f
    }

    private fun createHeader() {
        indexC = 0
        newRow()
        while (indexC < headers.size) {
            newCell()
            txtCell!!.text = headers[indexC++]
            tableRow!!.addView(txtCell, newTableRowParams())
        }
        layout.addView(tableRow)
    }

    private fun createDataTable() {
        var info: String
        indexR = 1
        while (indexR <= data.size) {
            newRow()
            indexC = 0
            while (indexC <= headers.size) {
                newCell()
                val cols = data!![indexR - 1]
                info = if(indexC < cols.size) cols[indexC] else ""
                txtCell!!.text = info
                tableRow!!.addView(txtCell, newTableRowParams())
                indexC++
            }
            layout.addView(tableRow)
            indexR++
        }
    }

    private fun newTableRowParams(): TableRow.LayoutParams {
        val params = TableRow.LayoutParams()
        params.setMargins(10, 10, 10, 10)
        params.weight = 1f
        return params
    }

}