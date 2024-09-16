package com.example.androidcourse_6_lemonadeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androidcourse_6_lemonadeapp.ui.theme.AndroidCourse_6_LemonadeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LemonadeApp()
}

@Composable
fun LemonadeApp() {
    TitleHeader()
    ClickableLemons()
}

@Composable
fun TitleHeader(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(Color.Yellow),
        contentAlignment = Alignment.Center
        ) {
        Text(
            text = stringResource(R.string.lemonade_text),
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.padding(top = 20.dp)
        )
    }
}

@Composable
fun ClickableLemons(modifier: Modifier = Modifier) {
    var result by remember { mutableIntStateOf(1) }
    val lemonStage = lemonImage(result)
    val lemonInfo = lemonInfo(result)
    var squeezeCount by remember { mutableIntStateOf(1) }
    var squeezes by remember { mutableIntStateOf(0) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxSize()
    ) {
        Button(
            modifier = modifier
                .background(Color.Transparent),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xff98FF98)),
            shape = RoundedCornerShape(50.dp),
            onClick = {
                when (result) {
                    1 -> {
                        squeezeCount = (2..4).random()
                        result = 2
                    }
                    2 -> {
                        squeezes++
                        result = if (squeezes == squeezeCount) {
                            squeezes = 0
                            3
                        } else 2
                    }
                    3 -> result = 4
                    4 -> result = 1 } }
        ) {
            Image(
                painter = painterResource(lemonStage),
                contentDescription = lemonInfo["desc"]
            )
        }
        Spacer(modifier = modifier.height(32.dp))
        Text(
            text = lemonInfo["buttonText"].toString(),
            fontSize = 24.sp
        )
    }
}

@Composable
fun lemonImage(result: Int): Int {
    val lemonStage = when (result) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    return lemonStage
}

@Composable
fun lemonInfo(result: Int): Map<String, String> {
    val lemonInfo: Map<String, String> = when (result) {
        1 -> mapOf(
            "desc" to stringResource(R.string.lemon_tree),
            "buttonText" to stringResource(R.string.click_lemon_tree_to_get_lemon))
        2 -> mapOf(
            "desc" to stringResource(R.string.lemon_slice),
            "buttonText" to stringResource(R.string.keep_clicking_to_squeeze_lemon)
        )
        3 -> mapOf(
            "desc" to stringResource(R.string.lemonade),
            "buttonText" to stringResource(R.string.click_to_drink_lemonade)
        )
        else -> mapOf(
            "desc" to stringResource(R.string.empty_glass),
            "buttonText" to stringResource(R.string.click_to_restart)
        )
    }
    return lemonInfo
}
