package com.kotlin.estudo.projeto1.model

import java.util.Observable

class User: Observable(){

    var userName: String = ""
    set(value) {
        field = value
        setChangedAndNotify("userName")
    }

    private fun setChangedAndNotify(field: Any)
    {
        setChanged()
        notifyObservers(field)
    }
}