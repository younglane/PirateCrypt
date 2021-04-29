package com.sigma.piratecrypt.database

import android.util.Log
import androidx.room.TypeConverter
import kotlinx.serialization.json.Json
import java.util.*

class UserTypeConverters {

    @TypeConverter
    fun toUUID(uuid: String?): UUID? {
        return UUID.fromString(uuid)
    }

    @TypeConverter
    fun fromUUID(uuid: UUID?): String? {
        return uuid?.toString()
    }
    @TypeConverter
    fun toListOfStrings(flatStringList: String): MutableList<String>{
        if (flatStringList == ""){
            return mutableListOf<String>()
        }
        else{
            return flatStringList.split('❘').toMutableList()
        }
    }
    @TypeConverter
    fun fromListOfStrings(listOfString: MutableList<String>): String {
        return listOfString.joinToString("❘")
    }




}
