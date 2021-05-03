package org.wit.ufc.models

interface UfcStore {

        fun findAll(): List<UfcModel>
        fun create(ufc: UfcModel)
        fun update(ufc: UfcModel)
    fun delete(ufc: UfcModel)
    fun findAllbyWeight(weight: String): List<UfcModel>
    }
