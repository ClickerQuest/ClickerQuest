package com.example.clickerquest

import com.parse.ParseClassName
import com.parse.ParseObject


@ParseClassName("Upgrades")
class Upgrades : ParseObject(){

    fun getDesc(): String? {
        return getString(des)
    }

    fun getAmount(): Int {
        return getInt(amount)
    }

    fun getCost(): Int {
        return getInt(cost)
    }

    companion object {
        const val des = "description"
        const val amount = "amount"
        const val cost = "cost"
    }

}