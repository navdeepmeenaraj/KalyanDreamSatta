package kalyan.dream.en.india.ui.main.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import kalyan.dream.en.india.R
import kalyan.dream.en.india.databinding.FragmentProfileBinding
import kalyan.dream.en.india.ui.main.viewmodel.SecondViewModel
import kalyan.dream.en.india.basic_utils.BasicUtils.bearerToken
import kalyan.dream.en.india.basic_utils.Constants.PREFS_USER_ID
import com.pixplicity.easyprefs.library.Prefs
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var mContext: Context
    private val secondViewModel: SecondViewModel by viewModels()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClick()
        fetchProfile()
        observeProfileData()
    }

    private fun initOnClick() {
        binding.buttonChangePassword.setOnClickListener {
           Navigation.findNavController(it)
               .navigate(R.id.changePasswordFragment)
        }
    }

    private fun observeProfileData() {
        secondViewModel.profile.observe(viewLifecycleOwner, {
            if (it.data != null) {
                it.data.apply {
                    binding.customerName.text = name
                    binding.customerId.text = user_id.toString()
                    binding.customerUsername.text = username
                }
            }
        })
    }

    private fun fetchProfile() {
        secondViewModel.fetchUserProfile(
            bearerToken(),
            Prefs.getInt(PREFS_USER_ID, 20)
        )
    }
}