package com.example.network

import com.example.network.model.MineUserEntity
import io.reactivex.Observable
import retrofit2.http.GET

interface ApiInterface {

    @GET("employees")
    fun getemployeelist(): Observable<MineUserEntity.VoEmployeedata>

}