package org.wit.ufc.models


import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class UfcMemStore : UfcStore, AnkoLogger {

    val ufcList = ArrayList<UfcModel>()

    override fun findAll(): List<UfcModel> {
        return ufcList
    }

    override fun create(ufc: UfcModel) {
        ufc.id = getId()
        ufcList.add(ufc)
        logAll()
    }

    override fun delete(placemark: UfcModel) {
        ufcList.remove(placemark)
    }

    override fun findAllbyWeight(weight: String): List<UfcModel> {
        TODO("Not yet implemented")
    }

    override fun update(ufc: UfcModel) {
        var foundUfc: UfcModel? = ufcList.find { p -> p.id == ufc.id }
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

            logAll()
        }
    }

    fun logAll() {
        ufcList.forEach { info("${it}") }
    }
}