package org.wit.ufc.activities


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_ufc.view.*
import kotlinx.android.synthetic.main.activity_ufc_list.*
import kotlinx.android.synthetic.main.card_ufc.*
import kotlinx.android.synthetic.main.card_ufc.view.*
import org.jetbrains.anko.intentFor

import org.wit.ufc.R
import org.wit.ufc.models.UfcModel
import org.jetbrains.anko.startActivityForResult
import org.wit.ufc.main.MainApp

class UfcListActivity : AppCompatActivity(), UfcListener {

    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ufc_list)
        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = UfcAdapter(app.ufcList.findAll(), this)
        (recyclerView.adapter as UfcAdapter).notifyDataSetChanged()
        loadUFC()
       toolbar .title = title
        setSupportActionBar(toolbar)



    }


    private fun loadUFC() {
        showUFCs(app.ufcList.findAll())
    }

    fun showUFCs (ufcs: List<UfcModel>) {
        recyclerView.adapter = UfcAdapter(ufcs, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<UfcActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onUfcClick(ufc: UfcModel) {

        startActivityForResult(intentFor<UfcActivity>().putExtra("ufc_edit", ufc), 0)

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //recyclerView is a widget in activity_placemark_list.xml
        recyclerView.adapter?.notifyDataSetChanged()
        loadUFC()
        super.onActivityResult(requestCode, resultCode, data)
    }

}













