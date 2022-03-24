package com.example.cloudmessaging.data.repository

import com.example.cloudmessaging.data.Api
import com.example.cloudmessaging.data.model.PushNotification
import okhttp3.ResponseBody
import retrofit2.Response

class ApiRepository(
    private val api: Api
):Api {
    override suspend fun pushFirebase(pushNotification: PushNotification): Response<ResponseBody> = api.pushFirebase(pushNotification)
}