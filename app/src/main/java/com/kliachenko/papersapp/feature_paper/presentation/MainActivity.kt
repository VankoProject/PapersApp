package com.kliachenko.papersapp.feature_paper.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.kliachenko.papersapp.ui.theme.PapersAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PapersAppTheme {

            }
        }
    }
}


