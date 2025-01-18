package com.example.tictactoe.ui.theme

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class GameViewModel(private var symbol : String){
    private val mySymbol = if (symbol == "❌") "⭕" else "❌"
    var state by mutableStateOf(mySymbol)
    var symbolList = MutableList(9) { ""}
    private val symbols = SymbolStore(symbolList)
    val symbolState = MutableList(9) { mutableStateOf(false) }
    fun changeState() {
        state = if (state == "❌") "⭕" else "❌"
    }

    private fun blockWin(): Boolean{
        if (!canWin()) {
            if (symbolList.count { it != "" } % 2 == 1){
                for (map in symbols.newSymbol){
                    val list = map.values.toList()
                    if (list.count {it == symbol} == 2 && list.all { it != mySymbol }){
                        var i = 0
                        for (index in map) if (index.value == "") i = index.key
                        symbolList[i] = mySymbol
                        symbolState[i].value = true
                        return true
                    }
                }
            }
        }
        return false
    }

    private fun canWin(): Boolean{
        for (map in symbols.newSymbol){
            val list = map.values.toList() // ['X','','X']
            if (symbolList.count { it != "" } % 2 == 1){
                if (list.count {it == mySymbol} == 2 && list.all { it != symbol }){
                    var i = 0
                    for (index in map) if (index.value == "") i = index.key
                    symbolList[i] = mySymbol
                    symbolState[i].value = true
                    return true
                }
            }
        }
        return false
    }



    fun computer() {
        if (symbolList.count{ it == ""} != 1) {
            var indexCorner : Int? = null
            var indexEdge : Int? = null
            for (i in listOf(0,2,6,8)) if (symbolList[i] == "" ) indexCorner = i
            for (i in listOf(1,3,5,7)) if (symbolList[i] == "" ) indexEdge = i
            if (symbolList.count { it != "" } % 2 == 1 && winner() == ""){
                if (symbolList.count { it != "" } == 1) {
                    var index = 0
                    if (symbolList[4] == symbol) {
                        symbolList[index] = mySymbol
                        symbolState[index].value = true
                    } else{
                        symbolList[4] = mySymbol
                        symbolState[4].value = true
                    }
                }else {
                    if (!blockWin()) {
                        if (symbolList.count { it != "" } % 2 == 1){
                            if (indexCorner != null){
                                symbolList[indexCorner] = mySymbol
                                symbolState[indexCorner].value = true
                            }
                            else {
                                if (indexEdge != null) {
                                    symbolList[indexEdge] = mySymbol
                                    symbolState[indexEdge].value = true
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    fun winner(): String {
        val symbols = SymbolStore(symbolList)
        for (map in symbols.newSymbol){
            val list = map.values.toList()
            if (list.all { it == list[0] && it != ""}) {
                return list[0]
            }
        }
        return ""
    }
}