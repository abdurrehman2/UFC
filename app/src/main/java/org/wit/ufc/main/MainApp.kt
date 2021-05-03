package org.wit.ufc.main


import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.ufc.models.UFCJSONStore
import org.wit.ufc.models.UfcMemStore
import org.wit.ufc.models.UfcModel

class MainApp : Application(), AnkoLogger {

   // val ufcList = ArrayList<UfcModel>()
    lateinit var ufcList : UFCJSONStore
    override fun onCreate() {
        super.onCreate()
     this.ufcList  = UFCJSONStore(applicationContext)

        info("Ufc started")

       // ufcList.add(UfcModel("One", "About one..."))
   //     ufcList.add(UfcModel("Two", "About two..."))
   //     ufcList.add(UfcModel("Three", "About three..."))
    }

}