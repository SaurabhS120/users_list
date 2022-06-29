package com.example.users.users_data.repos

import com.example.user.users_domain.entities.UsersEntity
import com.example.user.users_domain.repos.UsersRepo
import io.reactivex.rxjava3.core.Single
import kotlin.coroutines.CoroutineContext

interface UsersRemoteRepo : UsersRepo {
    override suspend fun getUsers(coroutineContext: CoroutineContext): Single<List<UsersEntity>>
}