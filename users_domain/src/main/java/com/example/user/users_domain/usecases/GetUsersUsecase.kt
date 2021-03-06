package com.example.user.users_domain.usecases

import android.util.Log
import com.example.user.users_domain.entities.UsersEntityPage
import com.example.user.users_domain.repos.UsersMediatorRepo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetUsersUsecase @Inject constructor(val repo: UsersMediatorRepo) {
    suspend fun invoke(
        compositeDisposable: CompositeDisposable
    ): Observable<UsersEntityPage> {
        return withContext(Dispatchers.IO) {
            Log.d("state", "getUsersUsecase Invoke")
            return@withContext repo.getUsers(coroutineContext, compositeDisposable)
        }
    }
}