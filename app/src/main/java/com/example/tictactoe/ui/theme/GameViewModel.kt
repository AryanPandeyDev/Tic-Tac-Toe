package com.example.tictactoe.ui.theme

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameViewModel(symbol : String) {
    private val firstSymbol = if (symbol == "❌") "⭕" else "❌"
    var state by mutableStateOf(firstSymbol)
    fun changeState() {
        state = if (state == "❌") "⭕" else "❌"
    }

    fun winner(list: MutableList<String>): String {
        return if (list.slice(0.. 2).all { it == list[0]}||
            list.slice(0..6 step 3).all { it == list[0]}){
            list[0]
        }else if (list.slice(2.. 8 step 3).all { it == list[8]}||
            list.slice(5..8).all { it == list[8] }) {
            list[8]
        }else if (list.slice(0.. 8 step 4).all { it == list[4]}||
            list.slice(2..6 step 2).all { it == list[4] }) {
            list[4]
        }else if (list.slice(1.. 7 step 3).all { it == list[4]}||
            list.slice(3..5).all { it == list[4]}) {
            list[4]
        }else ""
    }
}