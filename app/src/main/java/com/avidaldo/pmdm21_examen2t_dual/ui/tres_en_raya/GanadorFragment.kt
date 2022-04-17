package com.avidaldo.pmdm21_examen2t_dual.ui.tres_en_raya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.avidaldo.pmdm21_examen2t_dual.databinding.FragmentGanadorBinding

class GanadorFragment : Fragment() {
    private var _binding: FragmentGanadorBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGanadorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*************************************************************************/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        GanadorFragmentArgs.fromBundle(requireArguments()).ganador?.let {
            binding.textGanador.text = it
        }
    }

}