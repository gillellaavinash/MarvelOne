package com.example.marvel.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface MarvelApi {

    @GET("/api.php/{accessToken}/{id}")
    suspend fun getCharacters(
        @Path("accessToken") accessToken: String,
        @Path("id") id: Int
    ) : Response<Marvel>

}