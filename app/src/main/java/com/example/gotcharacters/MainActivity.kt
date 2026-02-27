package com.example.gotcharacters

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.gotcharacters.navigation.AppNavHost
import com.example.gotcharacters.ui.theme.GoTCharactersTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoTCharactersTheme {
                Surface(modifier = Modifier) {
                    AppNavHost()
                }
            }
        }
    }
}