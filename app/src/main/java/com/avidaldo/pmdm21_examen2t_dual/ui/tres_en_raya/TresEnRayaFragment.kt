package com.avidaldo.pmdm21_examen2t_dual.ui.tres_en_raya

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.avidaldo.pmdm21_examen2t_dual.R
import com.avidaldo.pmdm21_examen2t_dual.databinding.FragmentTresEnRayaBinding

class TresEnRayaFragment : Fragment() {
    private var _binding: FragmentTresEnRayaBinding? = null
    private val binding get() = _binding!!

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTresEnRayaBinding.inflate(inflater, container, false)
        return binding.root
    }


    /*************************************************************************/


    private val viewModel: TresEnRayaViewModel
            by navGraphViewModels(R.id.nav_graph_tres) {
                defaultViewModelProviderFactory
            }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonGrid.setOnClickListener(::onCellClicked)
        binding.buttonReset.setOnClickListener { viewModel.reset() }

        viewModel.model.reiniciar()


        viewModel.modelLiveData.observe(viewLifecycleOwner) {
            binding.buttonGrid.setModel(it.tablero)

            it.ganador?.let {
                findNavController().navigate(
                    TresEnRayaFragmentDirections.actionNavTresEnRayaToGanadorFragment()
                )
            }
        }
    }


    private fun GridLayout.setModel(celdas: Array<Array<TresEnRayaModel.Celda>>) {

        hideResetButton()

        for (i in 0 until childCount) {
            val boton = getChildAt(i) as Button
            val tag = boton.tag.toString().toCharArray()
            val row = tag[0].digitToInt()
            val col = tag[1].digitToInt()
            Log.d("--", celdas[row][col].toString())

            boton.text = celdas[row][col].toString()

            celdas[row][col].value?.let { showResetButton() }
        }
    }

    private fun hideResetButton() {
        binding.buttonReset.visibility = View.INVISIBLE
    }

    private fun showResetButton() {
        binding.buttonReset.visibility = View.VISIBLE
    }

    private fun GridLayout.setOnClickListener(onClick: (Button) -> Unit) {
        for (i in 0 until childCount) {
            val boton = getChildAt(i) as Button
            boton.setOnClickListener {
                onClick(boton)
            }
        }
    }

    private fun onCellClicked(button: Button) {
        val tag = button.tag.toString().toCharArray()
        val row = tag[0].digitToInt()
        val col = tag[1].digitToInt()

        viewModel.onCellClicked(row, col)

    }


}

