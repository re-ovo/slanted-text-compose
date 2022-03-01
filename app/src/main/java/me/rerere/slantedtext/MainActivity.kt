package me.rerere.slantedtext

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Slider
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.rerere.slantedtext.ui.theme.SlantedtextcomposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlantedtextcomposeTheme {
                Scaffold(
                    topBar = {
                        SmallTopAppBar(
                            title = { Text("SlantedTextDemo") }
                        )
                    }
                ) {
                    var width by remember { mutableStateOf(0.2f) }

                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            SlantedText(
                                text = "Kotlin",
                                textSize = 15.sp,
                                bold = false,
                                thickness = (width * 100f).dp,
                                padding = 16.dp,
                                slantedMode = SlantedMode.TOP_RIGHT
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(150.dp, 100.dp)
                                        .background(Color.Black)
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Text(text = "Thickness: ${(width * 100f).toInt()} dp")
                                Slider(value = width, onValueChange = { width = it })
                            }
                        }
                    }
                }
            }
        }
    }
}