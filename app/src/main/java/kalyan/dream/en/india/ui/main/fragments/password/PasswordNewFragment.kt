package kalyan.dream.en.india.ui.main.fragments.password

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kalyan.dream.en.india.R
import kalyan.dream.en.india.web_service.model.PasswordReset
import kalyan.dream.en.india.databinding.FragmentPasswordNewBinding
import kalyan.dream.en.india.ui.main.viewmodel.MainViewModel
import kalyan.dream.en.india.basic_utils.BasicUtils.hideSoftKeyboard
import kalyan.dream.en.india.basic_utils.BasicUtils.showErrorSnackBar
import kalyan.dream.en.india.basic_utils.BasicUtils.toast
import kalyan.dream.en.india.basic_utils.Constants.OTP_NUMBER
import kalyan.dream.en.india.basic_utils.Status
import com.pixplicity.easyprefs.library.Prefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PasswordNewFragment : Fragment() {


    private val viewmodel: MainViewModel by viewModels()
    private lateinit var mView: View
    private lateinit var bindng: FragmentPasswordNewBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindng = FragmentPasswordNewBinding.inflate(inflater, container, false)
        return bindng.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mView = view
        observePasswordReset()
        bindng.buttonResetPassword.setOnClickListener {
            validateInputs()
        }
    }

    private fun observePasswordReset() {
        viewmodel.reset.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let { response ->
                        requireActivity().toast(response.message)
                        findNavController().navigate(R.id.action_passwordNewFragment_to_loginFragment)
                    }
                }
                Status.LOADING -> {
                    bindng.progressBar.visibility = View.VISIBLE

                }
                Status.ERROR -> {
                    bindng.progressBar.visibility = View.GONE
                    requireActivity().hideSoftKeyboard(mView)
                    requireActivity().showErrorSnackBar(it.message.toString())

                }
            }
        }

    }

    private fun validateInputs() {
        val password = bindng.passwordFirst.text.toString()
        val confirmPassword = bindng.passwordSecond.text.toString()
        val passwordError = bindng.passwordFirst
        val confirmPasswordError = bindng.passwordSecond



        if (password.isEmpty()) {
            passwordError.error = "Password is empty"
            return
        }

        if (confirmPassword.isEmpty()) {
            passwordError.error = "Password is empty"
            return
        }

        if (password.length > 6) {
            passwordError.error = "Enter 6 digit password"
            return
        }

        if (confirmPassword.length > 6) {
            confirmPasswordError.error = "Enter 6 digit password"
            return
        }

        if (password != confirmPassword) {
            confirmPasswordError.error = "Confirm password does not match"
            return
        }

        val number = Prefs.getString(OTP_NUMBER, null)
        if (number != null) {
            viewmodel.passwordReset(
                PasswordReset(
                    number,
                    password,
                    confirmPassword
                )
            )

        }


    }

}