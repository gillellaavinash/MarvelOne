package com.example.marvelone

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvel.api.Constant
import com.example.marvel.api.Marvel
import com.example.marvel.api.NetworkResponse
import com.example.marvel.api.RetrofitInstance
import kotlinx.coroutines.launch
import kotlin.random.Random

class ImagesViewModel() : ViewModel() {

    private val marvelApi = RetrofitInstance.marvelApi
    private val _marvelResult = MutableLiveData<NetworkResponse<Marvel>>()
    val marvelResult: LiveData<NetworkResponse<Marvel>> = _marvelResult

    fun getMarvel(id : Int ) {
        _marvelResult.value = NetworkResponse.Loading
        //println("getMarvel called with accessToken: $accessToken and random: $id")

        viewModelScope.launch {
            //println("getMarvel called with accessToken: $accessToken and random: $id")
            try{
                val response = marvelApi.getCharacters(Constant.access_Token,id)
                //val responseBody = response.body()
                //println("Response body: ${responseBody}")
                if (response.isSuccessful) {
                   // println("The response is ${NetworkResponse.Success(response.body()!!)}")
                    _marvelResult.value = NetworkResponse.Success(response.body()!!)
                   // println("the marvel is ${_marvelResult.value}")
                }else{
                   // println("The error is $response")
                    _marvelResult.value = NetworkResponse.Error("Error to load data")
                }
            } catch (e: Exception){
                _marvelResult.value = NetworkResponse.Error("Error in load data")
            }
        }
    }

}