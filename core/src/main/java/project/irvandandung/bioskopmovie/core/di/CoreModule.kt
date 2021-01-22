package project.irvandandung.bioskopmovie.core.di

import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import project.irvandandung.bioskopmovie.core.BuildConfig
import project.irvandandung.bioskopmovie.core.data.MovieRepository
import project.irvandandung.bioskopmovie.core.data.source.local.LocalDataSource
import project.irvandandung.bioskopmovie.core.data.source.local.room.MovieDB
import project.irvandandung.bioskopmovie.core.data.source.remote.RemoteDataSource
import project.irvandandung.bioskopmovie.core.data.source.remote.network.ApiService
import project.irvandandung.bioskopmovie.core.domain.repository.IMovieRepository
import project.irvandandung.bioskopmovie.core.utils.AppExecutors
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDB>().movieDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieDB::class.java, "movie.db"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModul = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IMovieRepository> {
        MovieRepository(
            get(),
            get(),
            get()
        )
    }
}