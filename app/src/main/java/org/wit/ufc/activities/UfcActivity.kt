package org.wit.ufc.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_ufc.*
import kotlinx.android.synthetic.main.activity_ufc.btnAdd
import kotlinx.android.synthetic.main.activity_ufc.ufcTitle
import kotlinx.android.synthetic.main.activity_ufc_list.*

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import org.wit.ufc.R
import org.wit.ufc.helpers.readImage
import org.wit.ufc.helpers.readImageFromPath
import org.wit.ufc.helpers.showImagePicker
import org.wit.ufc.main.MainApp
import org.wit.ufc.models.Location
//import org.wit.ufc.main.MainApp
import org.wit.ufc.models.UfcModel

class UfcActivity : AppCompatActivity(), AnkoLogger {

    var ufc = UfcModel()
    lateinit var app: MainApp
    var edit: Boolean = false
    val IMAGE_REQUEST = 1

    val LOCATION_REQUEST = 2
  //  var location = Location(52.245696, -7.139102, 15f)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ufc)
       app = application as MainApp
        deleteB.visibility = View.INVISIBLE
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)
        if (intent.hasExtra("ufc_edit")) {
            edit = true
            ufc = intent.extras?.getParcelable<UfcModel>("ufc_edit")!!
            ufcTitle.setText(ufc.title)
            ufcDescription.setText(ufc.description)
            ufcName.setText(ufc.name)
            ufcWeight.setText(ufc.weight)
            deleteB.visibility = View.VISIBLE
            ufcAge.setText(ufc.age)
            deleteB.setOnClickListener(){

                app.ufcList.delete(ufc)


            }

          ufcImage.setImageBitmap(readImageFromPath(this, ufc.image))
            btnAdd.setText(R.string.button_editUfc)
        }


        ufcLocation.setOnClickListener {
            val location = Location(52.245696, -7.139102, 15f)
            if (ufc.zoom != 0f) {
                location.lat =  ufc.lat
                location.lng = ufc.lng
                location.zoom = ufc.zoom
            }
            startActivityForResult(intentFor<UFCMapsActivity>().putExtra("location", location), LOCATION_REQUEST)
        }

        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }

        ufcLocation.setOnClickListener {
       //     startActivity (intentFor<UFCMapsActivity>())
          //  val location = Location(52.245696, -7.139102, 15f)
          //  startActivity (intentFor<UFCMapsActivity>().putExtra("location", location))
          //  startActivityForResult(intentFor<UFCMapsActivity>().putExtra("location", location), LOCATION_REQUEST)
            val location = Location(52.245696, -7.139102, 15f)
            if (ufc.zoom != 0f) {
                location.lat =  ufc.lat
                location.lng = ufc.lng
                location.zoom = ufc.zoom
            }
            startActivityForResult(intentFor<UFCMapsActivity>().putExtra("location", location), LOCATION_REQUEST)

        }








        //changed tag in layout file to
        //<androidx.coordinatorlayout.widget.CoordinatorLayout
        btnAdd.setOnClickListener() {
            ufc.title = ufcTitle.text.toString()
            ufc.description = ufcDescription.text.toString()
            ufc.name   = ufcName.text.toString()
            ufc.age = ufcAge.text.toString()
           ufc.weight = ufcWeight.text.toString()
            ufc.record = ufcRecord.text.toString()

            if (ufc.title.isNotEmpty()) {
                if (edit )
                    app.ufcList.update(ufc)
                else
                  app.ufcList.create(ufc.copy())
                info("add Button Pressed: $ufc")
                info(" list : ${app.ufcList}")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            }
            else {
                toast ("Please Enter a title")
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    ufc.image = data.getData().toString()
                    ufcImage.setImageBitmap(readImage(this, resultCode, data))
                }
            }
            LOCATION_REQUEST -> {

                if (data != null) {
                    val location = data.extras?.getParcelable<Location>("location")!!
                    ufc.lat = location.lat
                    ufc.lng = location.lng
                    ufc.zoom = location.zoom
                }
            }
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_ufc, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.item_cancel -> {
                finish()
            }

            R.id.item_delete -> {
                app.ufcList.delete(ufc)

                finish()
            }


        }
        return super.onOptionsItemSelected(item)
    }




}