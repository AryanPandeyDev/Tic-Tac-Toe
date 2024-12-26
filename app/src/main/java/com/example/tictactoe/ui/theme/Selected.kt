package com.example.tictactoe.ui.theme

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class Selected():ViewModel(){
    var x by mutableStateOf(false)
        private set
    var o by mutableStateOf(false)
        private set
    fun Clicked(symbol : Char) {
        if (symbol == 'x') {
            x = true
            o = false
        }else if(symbol == 'o'){
            x = false
            o = true
        }
    }
}
