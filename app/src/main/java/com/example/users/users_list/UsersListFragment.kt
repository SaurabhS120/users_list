package com.example.users.users_list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.user.users_domain.responses.UsersResponse
import com.example.user.users_domain.usecases.GetUsersUsecase
import com.example.users.R
import com.example.users.databinding.FragmentUsersListBinding
import com.example.users.users_data.api.remote.RetrofitApi
import com.example.users.users_data.repoImpl.UsersRetrofitRepoImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsersListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentUsersListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val getUsersUsecase = GetUsersUsecase(
            repo = UsersRetrofitRepoImpl(
                RetrofitApi.getRetrofitApi()
            )
        )
        lifecycleScope.launchWhenStarted {
            val response = withContext(Dispatchers.IO){
                getUsersUsecase.invoke()
            }
            when(response){
                is UsersResponse.Success->{
                    Log.i("response","succeess")
                    response.response?.forEach {
                        Log.i("response","${it.firstName} ${it.medianName} ${it.lastName}")
                    }
                }
                is UsersResponse.Failure->{
                    Log.i("response","error")
                    Log.i("response", response.errorMsg)
                }
            }
        }
    }
}