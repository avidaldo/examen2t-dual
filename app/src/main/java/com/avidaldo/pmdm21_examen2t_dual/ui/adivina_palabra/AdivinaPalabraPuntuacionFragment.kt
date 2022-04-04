package com.avidaldo.pmdm21_examen2t_dual.ui.adivina_palabra

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.avidaldo.pmdm21_examen2t_dual.R
import com.avidaldo.pmdm21_examen2t_dual.databinding.FragmentAdivinaPalabraPuntuacionBinding


class AdivinaPalabraPuntuacionFragment : Fragment() {
    private var _binding: FragmentAdivinaPalabraPuntuacionBinding? = null
    private val binding get() = _binding!!


    private val viewModel: AdivinaPalabraViewModel
            by navGraphViewModels(R.id.nav_graph_avivina) {
                defaultViewModelProviderFactory
            }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentAdivinaPalabraPuntuacionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.modelLiveData.observe(viewLifecycleOwner) {
            binding.scoreText.text = it.score.toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onReset()
    }

}
