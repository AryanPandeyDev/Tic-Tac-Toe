package com.example.tictactoe.ui.theme

data class SymbolStore(private var symbol : MutableList<String>){
    val newSymbol: MutableList<Map<Int,String>>
        get() = mutableListOf(
            // Rows
            mapOf(0 to symbol[0], 1 to symbol[1], 2 to symbol[2]),  // First row
            mapOf(3 to symbol[3], 4 to symbol[4], 5 to symbol[5]),  // Second row
            mapOf(6 to symbol[6], 7 to symbol[7], 8 to symbol[8]),  // Third row

            // Columns
            mapOf(0 to symbol[0], 3 to symbol[3], 6 to symbol[6]),  // First column
            mapOf(1 to symbol[1], 4 to symbol[4], 7 to symbol[7]),  // Second column
            mapOf(2 to symbol[2], 5 to symbol[5], 8 to symbol[8]),  // Third column

            // Diagonals
            mapOf(0 to symbol[0], 4 to symbol[4], 8 to symbol[8]),  // Left-to-right diagonal
            mapOf(2 to symbol[2], 4 to symbol[4], 6 to symbol[6])   // Right-to-left diagonal
        )

    fun updateSymbolList(newList: MutableList<String>) {
        symbol = newList
    }
}
