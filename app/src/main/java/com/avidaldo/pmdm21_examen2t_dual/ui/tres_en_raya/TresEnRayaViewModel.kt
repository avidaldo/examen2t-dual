package com.avidaldo.pmdm21_examen2t_dual.ui.tres_en_raya

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TresEnRayaViewModel: ViewModel() {

    private var model = TresEnRayaModel()
    var modelLiveData = MutableLiveData(model)




    fun reset() {
        model.reiniciar()
        modelLiveData.value=model
    }

    fun onCellClicked(row: Int, col: Int) {
        model.jugarTurno(row, col)
        modelLiveData.value=model

    }


}