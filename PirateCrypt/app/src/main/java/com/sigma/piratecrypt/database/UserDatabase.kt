package com.sigma.piratecrypt.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sigma.piratecrypt.User

@Database(entities = [ User::class ], version = 3)
@TypeConverters(UserTypeConverters::class)


abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao


}
val migration_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE User ADD COLUMN messages TEXT NOT NULL DEFAULT ''"
        )

    }
}