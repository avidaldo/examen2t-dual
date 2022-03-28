package com.avidaldo.pmdm21_examen2t_dual.ui.adivina_palabra

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class AdivinaPalabraViewModel(private val wordList: MutableList<String>) : ViewModel() {

    private var model = AdivinaPalabraModel(wordList)

    var modelLiveData = MutableLiveData(model)

/*    override fun onCleared() {
        super.onCleared()
        modelLiveData.value = model.apply { reset() }
    }*/


    /** Métodos que reciben los eventos de la vista **/

    fun onSkip() {
        modelLiveData.value = model.apply {  // Actualizamos modelLiveData a model, después de
            nextWord()  // llamar a nextWord() (que asigna word=null si no quedan palabras en la lista)
            model.word?.also { model.score-- }  // y, si quedan palabras, decrementa el score
        }

    }

    fun onCorrect() {
        modelLiveData.value = model.apply {
            nextWord()
            model.word?.also { model.score++ }
        }
    }

    fun onReset() {
        modelLiveData.value = model.apply { reset() }
    }

}
