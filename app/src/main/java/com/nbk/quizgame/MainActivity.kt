package com.nbk.quizgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.nbk.quizgame.ui.theme.QuizGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuizGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column (modifier = Modifier){
                        QuestionOne(
                        question = "Android is an operating system.",
                        modifier = Modifier.padding(innerPadding)
                    )

                        Button(onClick = {}) {
                            Text(text = "True")
                        }
                        Button(onClick = {}) {
                            Text(text = "False")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun QuestionOne(question: String, modifier: Modifier = Modifier) {
    Text(
        text = "$question",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    QuizGameTheme {
        QuestionOne("Android is an operating system.")
    }
}