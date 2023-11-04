package com.cantrk.foodappcleanarchitecture.di

import android.content.Context
import android.provider.DocumentsContract.Root
import androidx.room.Room
import com.cantrk.foodappcleanarchitecture.db.FoodDao
import com.cantrk.foodappcleanarchitecture.db.FoodDatabase
import com.cantrk.foodappcleanarchitecture.network.FoodApi
import com.cantrk.foodappcleanarchitecture.repositoryimpl.FoodDatabaseImpl
import com.cantrk.foodappcleanarchitecture.repositoryimpl.FoodRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Di {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    @Singleton
    fun provideMovieApi(okHttpClient: OkHttpClient) : FoodApi{
        return Retrofit.Builder().baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(FoodApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: FoodApi) : FoodRepositoryImpl {
        return FoodRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideFoodDatabaseImpl(dao:FoodDao) : FoodDatabaseImpl{
        return FoodDatabaseImpl(foodDatabase = dao)
    }

    @Provides
    @Singleton
    fun provideFoodDatabase(@ApplicationContext context: Context) : FoodDatabase{
        return Room.databaseBuilder(context,FoodDatabase::class.java,"saved_food")
            .fallbackToDestructiveMigration().build()
    }
    @Provides
    @Singleton
    fun provideDatabase(foodDatabase:FoodDatabase) : FoodDao{
        return foodDatabase.foodDao()
    }
}