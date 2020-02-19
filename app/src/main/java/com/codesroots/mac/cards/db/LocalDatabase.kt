package com.codesroots.mac.cards.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codesroots.mac.cards.models.Buypackge
import com.codesroots.mac.cards.models.Pencode

@Database (entities = [Buypackge::class,Pencode::class],  version = 1)


abstract class CardDatabase : RoomDatabase (){
abstract fun  getCardDao () : CardDao

    companion object {

        @Volatile  private var instance : CardDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context?) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context!!).also {
                instance = it
            }
            }

            private fun buildDatabase(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                CardDatabase::class.java,
                "buypackagz"
            ).build()
    }

}