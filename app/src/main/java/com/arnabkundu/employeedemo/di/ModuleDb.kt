package com.arnabkundu.employeedemo.di

import android.content.Context
import androidx.room.Room
import com.arnabkundu.employeedemo.data.db.AppDatabase
import com.arnabkundu.employeedemo.data.db.dao.EmployeeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleDb {

    @Provides
    @Singleton
    fun provideAppDataBase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "employeedemo.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(database: AppDatabase): EmployeeDao {
        return database.getEmployeeDao()
    }
}