package com.avidaldo.pmdm21_examen2t_dual.ui.adivina_palabra

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AdivinaPalabraViewModelFactory (private val wordList: MutableList<String>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdivinaPalabraViewModel::class.java)) {
            return AdivinaPalabraViewModel(wordList) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}
