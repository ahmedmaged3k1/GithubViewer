package com.example.githubviewer.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.githubviewer.domain.model.Owner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class RepoTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun ownerToString(owner: Owner?): String {
        return gson.toJson(owner)
    }

    @TypeConverter
    fun stringToOwner(data: String): Owner? {
        val type = object : TypeToken<Owner>() {}.type
        return gson.fromJson(data, type)
    }


}