package com.codesroots.mac.cards.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.codesroots.mac.cards.models.Buypackge
import com.codesroots.mac.cards.DataLayer.helper.PreferenceHelper.setUserId
import com.codesroots.mac.cards.models.Pencode


@Dao
interface CardDao {

   @Insert
   abstract fun insertPackege(user: Buypackge)

   @Insert
   abstract fun insertPetList(pets: List<Pencode>)

   @Query("SELECT * FROM Buypackge WHERE opno =:id")
   suspend fun getUser(id: Int): Buypackge

   @Query("SELECT * FROM Buypackge")
   suspend fun GetAllData():  List<Buypackge>

   @Query("SELECT * FROM pencode WHERE buypackageid =:userId")
   suspend fun getPetList(userId: Int): List<Pencode>

   @Query("Delete  FROM Buypackge WHERE opno =:user")
   suspend fun deleteCard(user: Int)

}