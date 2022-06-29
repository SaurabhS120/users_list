package com.example.users.users_data.repoImpl.local

import android.util.Log
import com.example.users.users_data.PageConfig
import com.example.users.users_data.api.local.UsersRoomDatabase
import com.example.users.users_data.db_entities.UserDbEntity
import com.example.users.users_data.repos.UsersLocalRepo
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class UsersRoomRepoImpl @Inject constructor(val roomDatabase: UsersRoomDatabase) : UsersLocalRepo {
    override suspend fun getUsers(
        coroutineContext: CoroutineContext,
        compositeDisposable: CompositeDisposable
    ): Maybe<List<UserDbEntity>> {
        return roomDatabase.userDao().getUsers()
    }

    override fun getUsersPage(
        pageNo: Int,
        coroutineContext: CoroutineContext,
        compositeDisposable: CompositeDisposable
    ): Maybe<List<UserDbEntity>> {
        val start = ((pageNo - 1) * PageConfig.PAGE_SIZE) + 1
        val end = ((pageNo) * PageConfig.PAGE_SIZE)
        Log.d("state", "getUserRoom start:$start end:$end")
        return roomDatabase.userDao().getUsersBetween(start, end)
    }

    override suspend fun insertAll(users: List<UserDbEntity>) {
        roomDatabase.userDao().insertAll(users)
    }

}