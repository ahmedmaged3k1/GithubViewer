package com.example.githubviewer.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.githubviewer.domain.model.License
import com.example.githubviewer.domain.model.Owner
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.domain.model.ReposResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
@ProvidedTypeConverter
class RepoTypeConverter {
    private val gson = Gson()

    // Converters for RepoDetailsResponse
    @TypeConverter
    fun repoResponseToString(repoDetailsResponse: ReposResponse?): String {
        return gson.toJson(repoDetailsResponse)
    }

    @TypeConverter
    fun stringToRepoResponse(data: String): ReposResponse? {
        val type = object : TypeToken<ReposResponse>() {}.type
        return gson.fromJson(data, type)
    }

    // Converters for Owner
    @TypeConverter
    fun ownerToString(owner: Owner?): String {
        return gson.toJson(owner)
    }

    @TypeConverter
    fun stringToOwner(data: String): Owner? {
        val type = object : TypeToken<Owner>() {}.type
        return gson.fromJson(data, type)
    }

    // Converters for License
    @TypeConverter
    fun licenseToString(license: License?): String {
        return gson.toJson(license)
    }

    @TypeConverter
    fun stringToLicense(data: String): License? {
        val type = object : TypeToken<License>() {}.type
        return gson.fromJson(data, type)
    }
    @TypeConverter
    fun listToString(list: List<Any>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun stringToList(json: String): List<Any> {
        val type = object : TypeToken<List<Any>>() {}.type
        return gson.fromJson(json, type)
    }
    @TypeConverter
    fun anyToString(value: Any?): String? {
        return gson.toJson(value)
    }

    @TypeConverter
    fun stringToAny(data: String?): Any? {
        return if (data.isNullOrBlank()) {
            null
        } else {
            gson.fromJson(data, object : TypeToken<Any?>() {}.type)
        }
    }
}