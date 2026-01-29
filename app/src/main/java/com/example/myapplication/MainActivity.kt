package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var count by remember { mutableIntStateOf(0) }

                    AndroidView(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        factory = { context ->
                            val view = LayoutInflater.from(context).inflate(R.layout.counter_layout, null)
                            view.layoutParams = ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            val button = view.findViewById<Button>(R.id.button2)
                            button.setOnClickListener {
                                count++
                            }

                            view
                        },
                        update = { view ->
                            val counterText = view.findViewById<TextView>(R.id.counterText)
                            counterText?.text = count.toString()
                        }
                    )
                }
            }
        }
    }
}