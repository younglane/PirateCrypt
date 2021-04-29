/*
Class is not in use. Data class for a User. This is what will be stored
in the DB. Not Implemented yet.
-Adam Wainright
 */

package com.sigma.piratecrypt

import androidx.room.Entity
import androidx.room.PrimaryKey

import java.util.*


@Entity
data class User(@PrimaryKey val id: UUID = UUID.randomUUID(),
                var name: String = "",
                var password: String = "",
                val messages: MutableList<String> = mutableListOf<String>() )  {


}
