package com.itis.weather.domain.modules

import android.content.Context
import androidx.room.Room
import com.itis.weather.data.database.KraDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun providesRoom(@ApplicationContext context: Context): KraDb {
        return Room.databaseBuilder(context, KraDb::class.java, "kra.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}