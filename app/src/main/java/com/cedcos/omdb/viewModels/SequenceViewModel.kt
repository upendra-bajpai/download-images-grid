package com.cedcos.omdb.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.cedcos.omdb.data.model.ImageModel
import com.cedcos.omdb.data.repository.ImageRepository
import com.cedcos.omdb.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

    @HiltViewModel
    class SequenceViewModel @Inject constructor(userRepository: ImageRepository) : ViewModel() {

        private val users = MutableLiveData<Resource<List<ImageModel>>>()

        init {
            fetchUsers(userRepository)
        }

        private fun fetchUsers(userRepository: ImageRepository) {
            viewModelScope.launch {
                users.postValue(Resource.loading(null))
                try {
                    val usersFromApi = userRepository.getImages(1)
                    val moreUsersFromApi =userRepository.getImages(2)
                    val moreUsersFromApis=userRepository.getImages(3)
                    val allUsersFromApi = mutableListOf<ImageModel>()
                    allUsersFromApi.addAll(usersFromApi)
                    allUsersFromApi.addAll(moreUsersFromApi)
                    allUsersFromApi.addAll(moreUsersFromApis)
                    users.postValue(Resource.success(allUsersFromApi))
                } catch (e: Exception) {
                    users.postValue(Resource.error("Something Went Wrong", null))
                }
            }
        }

        fun getMovieImages(): LiveData<Resource<List<ImageModel>>> {
            return users
        }
    }
