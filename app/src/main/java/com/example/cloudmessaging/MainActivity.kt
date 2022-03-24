package com.example.cloudmessaging

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.cloudmessaging.data.MainViewModel
import com.example.cloudmessaging.data.MainViewModelFactory
import com.example.cloudmessaging.data.RetrofitInst.Companion.api
import com.example.cloudmessaging.data.model.NotificationData
import com.example.cloudmessaging.data.model.PushNotification
import com.example.cloudmessaging.data.repository.ApiRepository
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.messaging.FirebaseMessaging

class MainActivity : ComponentActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = ApiRepository(api)
        val viewModelFactory = MainViewModelFactory(repository)
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        setContent {
            var title by remember { mutableStateOf("") }
            var message by remember { mutableStateOf("") }
            var token by remember { mutableStateOf("") }

            MyFirebaseMessagingService.sharedPref = getSharedPreferences("token",Context.MODE_PRIVATE)
            FirebaseInstallations.getInstance().getToken(true).addOnSuccessListener  {
                MyFirebaseMessagingService.token = it.token
                token = it.token
            }
            FirebaseMessaging.getInstance().subscribeToTopic("/topics/myTopic")

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = {
                        Text(text = "Title")
                    }, modifier = Modifier.padding(5.dp)
                )

                OutlinedTextField(
                    value = message,
                    onValueChange = { message = it },
                    label = {
                        Text(text = "Message")
                    }, modifier = Modifier.padding(5.dp)
                )

                OutlinedButton(onClick = {
                    PushNotification(
                        data = NotificationData(
                            title = title,
                            message = message
                        ),
                        to = "/topics/myTopic"
                    ).also {
                        mainViewModel.poshFirebase(
                            pushNotification = it
                        )
                    }
                }) {
                    Text(text = "Push")
                }
            }
        }
    }
}