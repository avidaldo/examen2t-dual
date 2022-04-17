package com.avidaldo.pmdm21_examen2t_dual.ui.tres_en_raya


class TresEnRayaModel() {

    enum class Jugador { X, O }

    class Celda {
        var value: Jugador? = null // Cada celda puede estar vacía (a null) o con el valor de un jugador ('O' o 'X')
        override fun toString(): String {
            return value?.toString() ?: ""  // Si está a null devolvemos string vacío
        }
    }

    var tablero = Array(3) { Array<Celda>(3) { Celda() } }


    var ganador: Jugador? = null


    private var jugadorEnTurno = Jugador.X




    fun reiniciar() {
        clearCells()
        ganador = null
        jugadorEnTurno = Jugador.X
    }


    fun jugarTurno(row: Int, col: Int) {
        ganador?.let { return } //Si hay ganador no hacemos nada. Equivalente a linea anterior
        if (marcar(row, col)) {
            if (isMovimientoGana(jugadorEnTurno, row, col)) {
                ganador = jugadorEnTurno
            } else {
                cambiarTurno() // Cambia el Jugador en turno
            }
        }
    }

    private fun marcar(row: Int, col: Int): Boolean {
        if (!isValida(row, col)) return false // Celda inválida (la vista ya no debería permitirlo)
        tablero[row][col].value = jugadorEnTurno
        return true
    }


    private fun clearCells() {
        for (i in 0..2) {
            for (j in 0..2) {
                tablero[i][j].value = null
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
        return tablero[row][col].value != null
    }


    /** Devuelve true si el movimiento gana  */
    private fun isMovimientoGana(player: Jugador, fila: Int, columna: Int) =
        // 3-in-the-row
        (tablero[fila][0].value == player
                && tablero[fila][1].value == player
                && tablero[fila][2].value == player)
                || // 3-in-the-column
                (tablero[0][columna].value == player
                        && tablero[1][columna].value == player
                        && tablero[2][columna].value == player)
                || // 3-in-the-diagonal
                (fila == columna
                        && tablero[0][0].value == player
                        && tablero[1][1].value == player
                        && tablero[2][2].value == player)
                || // 3-in-the-opposite-diagonal
                (fila + columna == 2
                        && tablero[0][2].value == player
                        && tablero[1][1].value == player
                        && tablero[2][0].value == player)


    private fun cambiarTurno() {
        jugadorEnTurno = if (jugadorEnTurno == Jugador.X) Jugador.O else Jugador.X
    }

}