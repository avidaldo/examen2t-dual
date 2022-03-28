package com.avidaldo.pmdm21_examen2t_dual.ui.adivina_palabra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.avidaldo.pmdm21_examen2t_dual.databinding.FragmentAdivinaPalabraPuntuacionBinding


class AdivinaPalabraPuntuacionFragment : Fragment() {
    private var _binding: FragmentAdivinaPalabraPuntuacionBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAdivinaPalabraPuntuacionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** Al mostrar datos que llegan como argumentos en el Bundle de creación,
         * no es necesario el ViewModel para mantener el estado, y tratándose símplemente de
         * un int, no parece necesario añadir más complejidad */
        binding.scoreText.text = AdivinaPalabraPuntuacionFragmentArgs.fromBundle(requireArguments()).score.toString()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
