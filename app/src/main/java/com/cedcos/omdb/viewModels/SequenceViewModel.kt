package com.cedcos.omdb.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.cedcos.omdb.data.model.ResponseModel
import com.cedcos.omdb.data.repository.ImageRepository
import com.cedcos.omdb.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

    @HiltViewModel
    class SequenceViewModel @Inject constructor(userRepository: ImageRepository) : ViewModel() {

        private val users = MutableLiveData<Resource<List<ResponseModel>>>()

        init {
            fetchUsers(userRepository,2)
        }

        private fun fetchUsers(userRepository: ImageRepository, b:Int) {
            viewModelScope.launch {
                users.postValue(Resource.loading(null))
                try {
                    val usersFromApi = userRepository.getImages(1)
                    val moreUsersFromApi =userRepository.getImages(2)
                    val moreUsersFromApis=userRepository.getImages(3)

                    val allUsersFromApi = mutableListOf<String>()
                    val tempUsersFromApi=conditionalFilter(usersFromApi)
                    val tempUsersFromApi1=conditionalFilter(moreUsersFromApi)
                    val tempUsersFromApi2=conditionalFilter(moreUsersFromApis)
                    allUsersFromApi.addAll(tempUsersFromApi)
                    allUsersFromApi.addAll(tempUsersFromApi1)
                    allUsersFromApi.addAll(tempUsersFromApi2)
                    val imgModel=ArrayList<ResponseModel>()
                    imgModel.add(ResponseModel(allUsersFromApi.toString(),b.toString(),"","","","",0.0))
                    users.postValue(Resource.success(imgModel))
                } catch (e: Exception) {
                    users.postValue(Resource.error("Something Went Wrong", null))
                }
            }

        }

         fun conditionalFilter(usersFromApi: List<ResponseModel>): List<String> {
            var datAsString:ArrayList<String> = ArrayList<String>()
            usersFromApi.forEachIndexed{index,element->
                if(!element.description[0].isUpperCase())
                  usersFromApi.drop(index)
                else {
                    element.amount *= 2

                }
            }
            usersFromApi.forEachIndexed { index, imageModel -> datAsString[index]=imageModel.string() }
            return datAsString
        }

        fun getMovieImages(): LiveData<Resource<List<ResponseModel>>> {
            return users
        }
    }
