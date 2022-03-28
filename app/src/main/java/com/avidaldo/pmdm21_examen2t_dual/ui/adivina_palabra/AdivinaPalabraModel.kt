package com.avidaldo.pmdm21_examen2t_dual.ui.adivina_palabra


class AdivinaPalabraModel(private val wordList: MutableList<String>) {


    private var wordListRound = wordList.toMutableList().apply { shuffle() }
    /** Uso toMutableList() para clonar el objeto (en lugar de tener dos punteros al mismo */

    var score = 0
    var word: String? = wordListRound.removeAt(0)
    /** Dejo word como nullable, para indicar cuándo se acaban las palabras de la lista */


    fun reset() {
        score = 0
        wordListRound = wordList.toMutableList().apply { shuffle() }
        nextWord()
    }

    fun nextWord() {
        word = (if (wordListRound.isNotEmpty()) wordListRound.removeAt(0) else null)

    }
}