package kalyan.dream.en.india.ui.main.fragments.starline

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kalyan.dream.en.india.databinding.FragmentStarChartBinding
import kalyan.dream.en.india.basic_utils.Constants


class StarChartFragment : Fragment() {

    private lateinit var binding: FragmentStarChartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStarChartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            binding.webviewChart.loadUrl(Constants.SERVER_URL+"starline_chart" )
        } catch (e: Exception) {
            throw e
        }
    }
}