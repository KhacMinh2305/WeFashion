package com.minhdk.wefashion.infrastructure.repositoryimpl
import com.minhdk.wefashion.domain.data.RequestResult
import com.minhdk.wefashion.domain.repository.authentication.AuthenticationRepository
import com.minhdk.wefashion.infrastructure.datasource.authentication.RemoteAuthenticationDataSource
import com.minhdk.wefashion.infrastructure.model.token.DataAccessToken
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthenticationRepositoryImpl @Inject constructor(
    private val remoteSource: RemoteAuthenticationDataSource
): AuthenticationRepository {
    override suspend fun getToken(): RequestResult<DataAccessToken?> {
        return try {
            RequestResult.Success(remoteSource.getToken())
        } catch (e: Exception) {
            return RequestResult.Error(e)
        }
    }
}