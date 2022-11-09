package kalyan.dream.en.india.ui.main.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import kalyan.dream.en.india.R
import kalyan.dream.en.india.databinding.FragmentHistoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setOnClick()
    }

    private fun setOnClick() {
        binding.buttonBetHistory.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_historyFragment_to_bidHistory)
        }
        binding.buttonWinHistory.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_historyFragment_to_winHistory)
        }
    }
}