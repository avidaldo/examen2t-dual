package com.avidaldo.pmdm21_examen2t_dual.ui.tres_en_raya


class TresEnRayaModel() {

    enum class Jugador { X, O }

    internal class Celda {
        var value: Jugador? = null
    }

    private val celdas = Array(3) { arrayOfNulls<Celda>(3) }


    var ganador: Jugador? = null

    private enum class GameState {
        JUGANDO, TERMINADO
    }

    private var estado: GameState? = null

    private var jugadorEnTurno: Jugador? = null


    init {
        reiniciar()
    }


    fun reiniciar() {
        clearCells()
        ganador = null
        jugadorEnTurno = Jugador.X
        estado = GameState.JUGANDO
    }


    fun jugarTurno(row: Int, col: Int): Boolean {
        if (estado == GameState.TERMINADO) return false // No se sigue marcando si el juego ha terminado
        if (!marcar(row, col)) return false
        if (isMovimientoGana(row, col)) {
            estado = GameState.TERMINADO
            ganador = jugadorEnTurno
        } else {
            cambiarTurno() // Cambia el Jugador en turno
        }
        return true
    }

    private fun marcar(row: Int, col: Int): Boolean {
        if (!isValida(row, col)) return false // Celda inválida (la vista ya no debería permitirlo)
        celdas[row][col]!!.value = jugadorEnTurno
        return true
    }


    private fun clearCells() {
        for (i in 0..2) {
            for (j in 0..2) {
                celdas[i][j] = Celda()
            }
        }
    }

    private fun isValida(row: Int, col: Int): Boolean {
        return !(isOutOfBounds(row) || isOutOfBounds(col) ||
                isCeldaConValor(row, col))
    }

    private fun isOutOfBounds(idx: Int): Boolean {
        return idx < 0 || idx > 2
    }

    private fun isCeldaConValor(row: Int, col: Int): Boolean {
        return getplayerInCell(row, col) != null
    }

    fun getplayerInCell(row: Int, col: Int): Jugador? {
        return celdas[row][col]!!.value
    }


    /**
     * Devuelve true si el movimiento gana
     */
    private fun isMovimientoGana(fila: Int, columna: Int): Boolean {
        return (celdas[fila][0]!!.value == jugadorEnTurno // 3-in-the-row
                && celdas[fila][1]!!.value == jugadorEnTurno && celdas[fila][2]!!.value == jugadorEnTurno
                ) || (celdas[0][columna]!!.value == jugadorEnTurno // 3-in-the-column
                && celdas[1][columna]!!.value == jugadorEnTurno && celdas[2][columna]!!.value == jugadorEnTurno
                ) || (fila == columna // 3-in-the-diagonal
                && celdas[0][0]!!.value == jugadorEnTurno && celdas[1][1]!!.value == jugadorEnTurno && celdas[2][2]!!.value == jugadorEnTurno
                ) || (fila + columna == 2 // 3-in-the-opposite-diagonal
                && celdas[0][2]!!.value == jugadorEnTurno && celdas[1][1]!!.value == jugadorEnTurno && celdas[2][0]!!.value == jugadorEnTurno)
    }

    private fun cambiarTurno() {
        jugadorEnTurno = if (jugadorEnTurno == Jugador.X) Jugador.O else Jugador.X
    }

}