package project.irvandandung.bioskopmovie.core.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import project.irvandandung.bioskopmovie.core.data.source.local.LocalDataSource
import project.irvandandung.bioskopmovie.core.data.source.remote.RemoteDataSource
import project.irvandandung.bioskopmovie.core.data.source.remote.network.ApiResponse
import project.irvandandung.bioskopmovie.core.data.source.remote.response.MovieResponse
import project.irvandandung.bioskopmovie.core.domain.model.Movie
import project.irvandandung.bioskopmovie.core.domain.repository.IMovieRepository
import project.irvandandung.bioskopmovie.core.utils.AppExecutors
import project.irvandandung.bioskopmovie.core.utils.DataMapper

class MovieRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors : AppExecutors
) : IMovieRepository{

    override fun getAllMovie() :Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(){
            override fun loadFromDB(): Flow<List<Movie>> = localDataSource.getAllMovie().map { DataMapper.mapEntitiesToDomain(it) }

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> = remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertListMovie(movieList)
            }

        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>> = localDataSource.getFavoriteMovie().map { DataMapper.mapEntitiesToDomain(it) }

    override fun setFavoriteMovie(movie : Movie, state : Boolean){
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute{ localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}