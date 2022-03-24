package com.example.cloudmessaging.data

import com.example.cloudmessaging.data.model.PushNotification
import com.example.cloudmessaging.data.utils.Constants.PUSH_FIREBASE_URL
import com.example.cloudmessaging.data.utils.Constants.SERVER_KEY
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface Api {

    @Headers("Authorization: key=$SERVER_KEY", "Content-Type:application/json")
    @POST(PUSH_FIREBASE_URL)
    suspend fun pushFirebase(
        @Body pushNotification: PushNotification
    ):Response<ResponseBody>
}