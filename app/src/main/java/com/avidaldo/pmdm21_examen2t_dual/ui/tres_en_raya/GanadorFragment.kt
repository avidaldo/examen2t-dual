package com.avidaldo.pmdm21_examen2t_dual.ui.tres_en_raya

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.avidaldo.pmdm21_examen2t_dual.R
import com.avidaldo.pmdm21_examen2t_dual.databinding.FragmentGanadorBinding

class GanadorFragment : Fragment() {
    private var _binding: FragmentGanadorBinding? = null
    private val binding get() = _binding!!

    private val viewModel: TresEnRayaViewModel
            by navGraphViewModels(R.id.nav_graph_tres) {
                defaultViewModelProviderFactory
            }

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

        viewModel.modelLiveData.observe(viewLifecycleOwner) {
            binding.textSlideshow.text = it.ganador.toString()
        }

    }

    override fun onDestroy() {
        viewModel.reset()
        super.onDestroy()
    }


}