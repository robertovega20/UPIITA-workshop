package com.example.myapplicationcontrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collection.MutableVector
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplicationcontrol.ui.theme.MyApplicationcontrolTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationcontrolTheme {
                val messages: MutableList<String> = remember { mutableStateListOf() }
                Column(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(.5f)
                            .verticalScroll(rememberScrollState())
                            .background(color = Color.Gray),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        messages.forEach { message ->
                            Text(text = message)
                        }
                    }
                    Column(
                        modifier = Modifier.weight(.5f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Row(Modifier.fillMaxWidth().background(color = Color.Green), horizontalArrangement = Arrangement.Center) {
                            CustomImage(
                                modifier = Modifier,
                                message = "UP",
                                degrees = 90f,
                                onClick = { messages.addMessage(it) })
                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .background(color = Color.Red)
                                .padding(40.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CustomImage(
                                modifier = Modifier,
                                message = "Left",
                                degrees = 0f,
                                onClick = { messages.addMessage(it) })
                            CustomImage(
                                modifier = Modifier,
                                message = "Right",
                                degrees = 180f,
                                onClick = { messages.addMessage(it) })
                        }
                        Row(Modifier.fillMaxWidth().background(color = Color.Cyan), horizontalArrangement = Arrangement.Center) {
                            CustomImage(
                                modifier = Modifier,
                                message = "down",
                                degrees = -90f,
                                onClick = { messages.addMessage(it) })
                        }
                    }
                }
            }
        }
    }

    fun MutableList<String>.addMessage(message: String) {
        this.add(message)
    }

    @Composable
    fun CustomImage(
        modifier: Modifier,
        message: String,
        degrees: Float,
        onClick: (String) -> Unit
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_left),
            contentDescription = "",
            modifier = modifier
                .size(100.dp)
                .rotate(degrees)
                .clickable { onClick(message) }
        )
    }
}
