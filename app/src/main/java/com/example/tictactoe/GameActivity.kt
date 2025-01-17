package com.example.tictactoe


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tictactoe.ui.theme.GameViewModel
import com.example.tictactoe.ui.theme.OpenSansCondensedExtraBold
import com.example.tictactoe.ui.theme.OpenSansCondensedLight
import com.example.tictactoe.ui.theme.OpenSansLight
import com.example.tictactoe.ui.theme.OpenSansMedium
import com.example.tictactoe.ui.theme.OpenSansRegular
import com.example.tictactoe.ui.theme.OpenSansSemiCondensedBold
import com.example.tictactoe.ui.theme.OpenSansSemiCondensedMedium
import com.example.tictactoe.ui.theme.OpenSansSemiCondensedRegular
import com.example.tictactoe.ui.theme.Selected
import com.example.tictactoe.ui.theme.SymbolStore

class GameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val symbol = intent?.getStringExtra("symbol") ?: "❌"
        val mode = intent?.getStringExtra("mode")
        enableEdgeToEdge()
        setContent {
            if (mode != null) {
                Game(symbol,mode)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ShowGamePreview() {
    Game("❌","s")
}

@SuppressLint("MutableCollectionMutableState")
@Composable
fun Game(symbol: String,mode : String) {
    val context = LocalContext.current
    val gameViewModel = remember { GameViewModel(symbol) }
    val symbolStore = SymbolStore(gameViewModel.symbolList)
    var winnerState by remember { mutableStateOf(false) }
    var drawState by remember { mutableStateOf(false) }
    var reset by remember { mutableStateOf(false) }
    if (reset && context is Activity) context.recreate()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFd9d9e0))
            .padding(16.dp)
    ) {
        LazyVerticalGrid(
            modifier = Modifier.align(Alignment.Center),
            columns = GridCells.Fixed(3),
            content = {
                items(9) {
                    Box(
                        modifier = Modifier
                            .aspectRatio(1F)
                            .padding(8.dp)
                            .border(
                                width = 3.dp,
                                brush = Brush.linearGradient(listOf(Color.Gray, Color.DarkGray)),
                                shape = RectangleShape
                            )
                            .clickable {
                                if (mode == "m") {
                                    if (!gameViewModel.symbolState[it].value) {
                                        gameViewModel.symbolState[it].value = true
                                        gameViewModel.changeState()
                                        gameViewModel.symbolList[it]= gameViewModel.state
                                        symbolStore.updateSymbolList(gameViewModel.symbolList)
                                        winnerState = gameViewModel.winner()=="❌"|| gameViewModel.winner()=="⭕"
                                        drawState = !gameViewModel.symbolList.any { it == "" }
                                        Log.d("symbols","${symbolStore.newSymbol.map { it.values.toList() }}," +
                                                "List = ${gameViewModel.symbolList}")
                                    }
                                }else {
                                    if (!gameViewModel.symbolState[it].value) {
                                        gameViewModel.symbolState[it].value = true
                                        gameViewModel.symbolList[it]= symbol
                                        gameViewModel.computer()
                                        symbolStore.updateSymbolList(gameViewModel.symbolList)
                                        winnerState = gameViewModel.winner()=="❌"|| gameViewModel.winner()=="⭕"
                                        drawState = !gameViewModel.symbolList.any { it == "" }
                                        Log.d("symbols","${symbolStore.newSymbol.map { it.values.toList() }}," +
                                                "List = ${gameViewModel.symbolList}")
                                    }
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        AnimatedVisibility(
                            visible = gameViewModel.symbolState[it].value,
                            enter = fadeIn(tween(500))
                        ) {

                            Text (
                                gameViewModel.symbolList[it],
                                fontSize = 60.sp
                            )
                        }
                    }

                }
            }
        )
        AnimatedVisibility(
            visible = winnerState||drawState,
            enter = fadeIn(tween(1000)) + expandIn(expandFrom = Alignment.CenterStart)
        ) {WinnerBox(gameViewModel.winner()){reset = it}}
    }

}

@Composable
fun WinnerBox(winner : String,resetActivity : (Boolean) -> Unit = {}) {
    val context = LocalContext.current
    val disWinner = if (winner == "❌") "✕" else if (winner == "⭕") "◯" else ""
    val display = if (winner == "") "Its a Draw" else "$disWinner  WON"
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = Color.Black.copy(0F))
    ){
        Box(
            modifier = Modifier.size(360.dp,400.dp)
                .align(Alignment.Center)
                .shadow(5.dp, RoundedCornerShape(25.dp))
                .clip(RoundedCornerShape(25.dp))
                .background(color = Color(0xFFeee2bc))
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(display,
                    fontFamily = OpenSansLight,
                    fontSize = 70.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(120.dp))
                Button(
                    elevation = ButtonDefaults.buttonElevation(pressedElevation = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0B1B32),
                        contentColor = Color.White,
                        disabledContentColor = Color.White,
                        disabledContainerColor = Color(0xFF0B1B32)
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .size(height = 70.dp, width = 300.dp),
                    onClick = {
                        resetActivity(true)
                    }
                ){
                    Text(
                        "PLAY AGAIN",
                        fontFamily = OpenSansMedium,
                        fontSize = 35.sp,
                    )
                }
                Spacer(Modifier.height(20.dp))
                Button(
                    elevation = ButtonDefaults.buttonElevation(pressedElevation = 16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF0B1B32),
                        contentColor = Color.White,
                        disabledContentColor = Color.White,
                        disabledContainerColor = Color(0xFF0B1B32)
                    ),
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .size(height = 70.dp, width = 300.dp),
                    onClick = {
                        Intent(context,MainActivity::class.java).also {
                            context.startActivity(it)
                        }
                    }
                ){
                    Text(
                        "MAIN MENU",
                        fontFamily = OpenSansMedium,
                        fontSize = 35.sp,
                    )
                }

            }
        }
    }

}