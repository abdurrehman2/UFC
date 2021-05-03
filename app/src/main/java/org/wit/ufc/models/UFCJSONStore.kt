package org.wit.ufc.models

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import org.wit.ufc.helpers.*
import java.util.*

val JSON_FILE = "Ufc.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<UfcModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class UFCJSONStore : UfcStore, AnkoLogger {

    val context: Context
    var ufcs = mutableListOf<UfcModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<UfcModel>  {
        return ufcs
    }

    //getting started
    override fun findAllbyWeight(weight: String): List<UfcModel> {
        var ufcList = mutableListOf<UfcModel>()
        for (ufc in ufcs )
        {
            if (ufc.weight.equals(weight))
                ufcList.add(ufc)
        }
        return ufcList
    }


    override fun create(ufc: UfcModel) {
        ufc.id = generateRandomId()
        ufcs.add(ufc)
        serialize()
    }


    override fun update(ufc: UfcModel) {
        // todo
        var foundUfc: UfcModel? = ufcs.find { p -> p.id == ufc.id }
        if (foundUfc != null) {
            foundUfc.title = ufc.title
            foundUfc.description = ufc.description
            foundUfc.name = ufc.name
            foundUfc.weight = ufc.weight
            foundUfc.age = ufc.age
            foundUfc.image = ufc.image

            foundUfc.lat = ufc.lat
            foundUfc.lng = ufc.lng
            foundUfc.zoom = ufc.zoom


        }
    }

    override fun delete(ufc: UfcModel) {
        ufcs.remove(ufc)
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(ufcs, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        ufcs = Gson().fromJson(jsonString, listType)
    }
}