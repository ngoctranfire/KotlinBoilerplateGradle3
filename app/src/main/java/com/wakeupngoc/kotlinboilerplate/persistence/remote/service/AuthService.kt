package com.wakeupngoc.kotlinboilerplate.persistence.remote.service

import com.wakeupngoc.kotlinboilerplate.persistence.remote.model.AuthResponse
import io.reactivex.Maybe
import retrofit2.http.*

/**
 * Created by ngoctranfire on 9/4/17.
 */
interface AuthService {

    @POST("/authentication/") @FormUrlEncoded
    fun authenticate(@Field("email") email: String?, @Field("password") password: String?, @Field("strategy") strategy: String): Maybe<AuthResponse>


    @POST("/users/") @FormUrlEncoded
    fun create(@Field("email") email: String, @Field("password") password: String)
}