package com.example.tictactoe

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoe.ui.theme.Selected
import com.example.tictactoe.ui.theme.TicTacToeTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StartPage()
        }
    }
}


@Composable
fun StartPage() {
    val context = LocalContext.current
    val selected = viewModel<Selected>()
    var sendData = if (selected.x) "❌" else if (selected.o) "⭕" else null
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(R.drawable.background2),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(50.dp))
            Row {
                Text(
                    "Tic-",
                    style = TextStyle(color = Color(0xFF00695C), fontSize = 50.sp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Tac-",
                    style = TextStyle(color = Color(0xFFEF6C00), fontSize = 50.sp),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "Toe",
                    style = TextStyle(color = Color(0xFF6A1B9A), fontSize = 50.sp),
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.size(20.dp))
            Text(
                "CHOOSE YOUR SIDE",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF26415E),
                            Color(0xFF83A6CE)
                        )
                    ),
                    shadow = Shadow(
                        color = Color.Gray,
                        offset = Offset(8f, 8f),
                        blurRadius = 8f
                    )
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Bottom
            ) {
                SymbolText("❌", selected.x, 'x') { selected.Clicked(it) }
                SymbolText("⭕", selected.o, 'o') { selected.Clicked(it) }
            }
            Button(
                elevation = ButtonDefaults.buttonElevation(pressedElevation = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0B1B32),
                    contentColor = Color.White,
                    disabledContentColor = Color.White,
                    disabledContainerColor = Color(0xFF0B1B32)
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .size(height = 70.dp, width = 300.dp),
                onClick = {
                    if (sendData != null) {
                        Intent(context,GameActivity::class.java).also {
                            it.putExtra("symbol",sendData)
                            context.startActivity(it)
                        }
                    }else {
                        Toast.makeText(context,"Please Choose A Side",Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(
                    "START GAME",
                    fontSize = 30.sp,
                )
            }
        }
    }
}

@Composable
fun SymbolText(symbol : String, state : Boolean,symbol2 : Char,changeState : (Char) -> Unit = {}){
    val borderWidth  by animateDpAsState(
        targetValue = if (state) 8.dp else 0.dp,
        label = "border width"
    )
    val textSize  by animateFloatAsState(
        targetValue = if (state) 90F else 100F,
        label = "text Size"
    )


    Text(
        symbol,
        fontSize = textSize.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .size(130.dp)
            .border(
                width = borderWidth,
                color = Color.Red,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(top = 8.dp)
            .clickable {
                changeState(symbol2)
            },
        fontWeight = FontWeight.Bold
    )
}

@Preview(showSystemUi = true)
@Composable
fun GreetingPreview() {
    TicTacToeTheme {
        StartPage()
    }
}