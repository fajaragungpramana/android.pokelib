package com.github.fajaragungpramana.pokelib.core.data.remote.pokemon.response

import com.google.gson.annotations.SerializedName

data class NameEntity(

    @SerializedName("name")
    val name: String? = null

) {

    private val mMapStat = mapOf(
        STAT_HP to "HP",
        STAT_ATTACK to "ATT",
        STAT_DEFENSE to "DEF",
        STAT_SPECIAL_ATTACK to "S-ATT",
        STAT_SPECIAL_DEFENSE to "S-DEF",
        STAT_SPEED to "SPD"
    )

    fun mapStat() = mMapStat[name]

    companion object {
        private const val STAT_HP = "hp"
        private const val STAT_ATTACK = "attack"
        private const val STAT_DEFENSE = "defense"
        private const val STAT_SPECIAL_ATTACK = "special-attack"
        private const val STAT_SPECIAL_DEFENSE = "special-defense"
        private const val STAT_SPEED = "speed"
    }

}