package com.example.ep.myapplication.Activitys.Model

import java.io.Serializable

class State : Serializable {
    val name: String
    var borders: List<String>? = null
        private set
    val nativeName: String
    var flag: String? = null
        private set

    constructor(name: String, nativeName: String) {
        this.name = name
        this.nativeName = nativeName
    }

    constructor(name: String, borders: List<String>?, nativeName: String, flag: String?) {
        this.name = name
        this.borders = borders
        this.nativeName = nativeName
        this.flag = flag
    }
}