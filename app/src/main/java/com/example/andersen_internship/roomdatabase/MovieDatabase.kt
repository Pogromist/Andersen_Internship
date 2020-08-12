package com.example.andersen_internship.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieData::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDataDao(): MovieDataDao

    companion object {
        @Volatile
        private var databaseInstance: MovieDatabase? = null

        fun getDatabaseInstance(mContext: Context): MovieDatabase =
            databaseInstance ?: synchronized(this) {
                databaseInstance ?: buildDatabaseInstance(mContext).also {
                    databaseInstance = it
                }
            }

        private fun buildDatabaseInstance(mContext: Context) =
            Room.databaseBuilder(mContext, MovieDatabase::class.java, "DatabaseSample")
                .fallbackToDestructiveMigration()
                .build()
    }
}
