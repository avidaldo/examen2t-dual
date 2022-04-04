package com.avidaldo.pmdm21_examen2t_dual.ui.adivina_palabra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import com.avidaldo.pmdm21_examen2t_dual.R
import com.avidaldo.pmdm21_examen2t_dual.databinding.FragmentAdivinaPalabraBinding
import com.google.android.material.snackbar.Snackbar


class AdivinaPalabraFragment : Fragment() {
    private var _binding: FragmentAdivinaPalabraBinding? = null
    private val binding get() = _binding!!


    private val viewModel: AdivinaPalabraViewModel
            by navGraphViewModels(R.id.nav_graph_avivina) {
                AdivinaPalabraViewModelFactory(resources.getStringArray(R.array.palabras).toMutableList())
            }
/*    private val viewModel: AdivinaPalabraViewModel by viewModels() {
        AdivinaPalabraViewModelFactory(resources.getStringArray(R.array.palabras).toMutableList())
    }*/

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAdivinaPalabraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.correctButton.setOnClickListener { viewModel.onCorrect() }
        binding.skipButton.setOnClickListener { viewModel.onSkip() }
        binding.endGameButton.setOnClickListener { onEndGame() }


        viewModel.modelLiveData.observe(viewLifecycleOwner) { model ->

            // Cambios en la palabra:
            model.word?.let {  // Si la palabra no es null
                    binding.wordText.text = it // Se asigna la palabra al textView
                } ?: onEndOfWordList() // Si es null, índica que no quedan palabras

            // Cambios en la puntuación (score)
            binding.scoreText.text = model.score.toString()
        }


    }

    private fun onEndOfWordList() {
        Snackbar.make(binding.root, R.string.end_word_list, Snackbar.LENGTH_SHORT)
            .setAction("Reiniciar") { reset() }
            .show()
    }

    private fun reset() {
        viewModel.onReset()
    }


    private fun onEndGame() {
        viewModel.modelLiveData.value!!.score.let {
            findNavController().navigate(AdivinaPalabraFragmentDirections.actionGameToScore())
        }
    }

}

