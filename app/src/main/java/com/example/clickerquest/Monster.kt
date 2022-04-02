package com.example.clickerquest

import com.parse.ParseClassName
import com.parse.ParseFile
import com.parse.ParseObject
import com.parse.ParseUser

@ParseClassName("Monster")
class Monster : ParseObject() {
    fun getName(): String? {
        return getString(MONSTER_NAME)
    }

    fun getHealth(): Int {
        return getInt(MONSTER_HEALTH)
    }

    fun getCurrentHealth(): Int {
        return getInt(MONSTER_CURRENTHP)
    }

    fun getImage(): ParseFile? {
        return getParseFile(MONSTER_IMAGE)
    }

    fun getStage(): Int {
        return getInt(MONSTER_STAGE)
    }

    fun getGold(): Int {
        return getInt(GOLD)
    }

    companion object{
        const val MONSTER_NAME = "name"
        const val MONSTER_HEALTH = "base_health"
        const val MONSTER_CURRENTHP = "current_health"
        const val MONSTER_IMAGE = "monsterImage"
        const val MONSTER_STAGE = "stageNumber"
        const val GOLD = "coin_drop"
    }
}