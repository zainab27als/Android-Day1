package com.nbk.quizgame

import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nbk.quizgame.ui.theme.QuizGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuizScreen()
        }
    }
}

@Composable
fun QuizScreen() {
    val questionTexts = stringArrayResource(id = R.array.quiz_questions)
    val answers = listOf(true, false, true)

    var questionIndex by remember { mutableStateOf(0) }
    var selectedAnswer by remember { mutableStateOf<Boolean?>(null) }

    val currentQuestion = questionTexts[questionIndex]
    val correctAnswer = answers[questionIndex]

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = currentQuestion,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            if (selectedAnswer == null || selectedAnswer != correctAnswer) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { selectedAnswer = true },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                    ) {
                        Text(stringResource(R.string.answer_true_text))
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    Button(
                        onClick = { selectedAnswer = false },
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                    ) {
                        Text(stringResource(R.string.answer_false_text))
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (selectedAnswer == correctAnswer && selectedAnswer != null) {
                Button(
                    onClick = {
                        questionIndex = (questionIndex + 1) % questionTexts.size
                        selectedAnswer = null
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(50),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF007C91))
                ) {
                    val buttonText = if (questionIndex == questionTexts.lastIndex)
                        stringResource(R.string.restart_game)
                    else
                        stringResource(R.string.next_question)

                    Text(text = buttonText, color = Color.White)
                }
            }
        }

        if (selectedAnswer == correctAnswer && selectedAnswer != null) {
            CorrectAnswerText(modifier = Modifier.align(Alignment.Center))
        } else if (selectedAnswer != null && selectedAnswer != correctAnswer) {
            WrongAnswerText(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun CorrectAnswerText(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(200.dp)
            .background(Color.Green, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.correct_answer),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.correct_answer), color = Color.Black)
        }
    }
}

@Composable
fun WrongAnswerText(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(200.dp)
            .background(Color.Red, shape = CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.wrong_answer),
                contentDescription = null,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(R.string.wrong_answer), color = Color.Black)
        }
    }
}

