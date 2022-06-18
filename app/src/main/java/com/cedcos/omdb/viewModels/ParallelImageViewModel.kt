package com.cedcos.omdb.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cedcos.omdb.data.model.ResponseModel
import com.cedcos.omdb.data.repository.ImageRepository
import com.cedcos.omdb.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ParallelImageViewModel @Inject constructor(userRepository: ImageRepository) : ViewModel() {


    private val users = MutableLiveData<Resource<List<ResponseModel>>>()

    init {
        fetchUsers(userRepository)
    }

    private fun fetchUsers(userRepository: ImageRepository) {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            try {
                val usersFromApiDeferred =async { userRepository.getImages(1)}
                val moreUsersFromApiDeferred  =async { userRepository.getImages(2)}
                val moreUsersFromApisDeferred =async { userRepository.getImages(3)}


                val usersFromApi = usersFromApiDeferred.await()
                val moreUsersFromApi = moreUsersFromApiDeferred.await()
                val moreUsersFromApis = moreUsersFromApisDeferred.await()



                val allUsersFromApi = mutableListOf<ResponseModel>()
                allUsersFromApi.addAll(usersFromApi)
                allUsersFromApi.addAll(moreUsersFromApi)
                allUsersFromApi.addAll(moreUsersFromApis)
                users.postValue(Resource.success(allUsersFromApi))
            } catch (e: Exception) {
                users.postValue(Resource.error("Something Went Wrong", null))
            }
        }
    }

    fun getMovieImages(): LiveData<Resource<List<ResponseModel>>> {
        return users
    }
}
