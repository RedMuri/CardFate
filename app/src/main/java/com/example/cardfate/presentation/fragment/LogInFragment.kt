package com.example.cardfate.presentation.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.cardfate.CardFateApp
import com.example.cardfate.R
import com.example.cardfate.databinding.FragmentLogInBinding
import com.example.cardfate.presentation.state.AuthError
import com.example.cardfate.presentation.state.AuthProgress
import com.example.cardfate.presentation.state.AuthSuccess
import com.example.cardfate.presentation.viewmodel.LogInViewModel
import com.example.cardfate.presentation.viewmodel.ViewModelFactory
import javax.inject.Inject


class LogInFragment : Fragment() {

    private var _binding: FragmentLogInBinding? = null
    private val binding: FragmentLogInBinding
        get() = _binding ?: throw RuntimeException("FragmentLogInBinding == null")

    private var errorToast: Toast? = null

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val logInViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[LogInViewModel::class.java]
    }

    private val component by lazy {
        (requireActivity().application as CardFateApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLogInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        bindClickListeners()
    }

    private fun bindClickListeners() {
        binding.btLogIn.setOnClickListener {
            val login = binding.etLogin.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()
            logInViewModel.logIn(login, password)
        }
        binding.btSignIn.setOnClickListener {
            navigateToFragment(SignInFragment())
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, fragment)
            .commit()
    }

    private fun observeViewModel() {
        logInViewModel.authState.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = View.GONE
            binding.btLogIn.isEnabled = true

            when (it) {
                is AuthSuccess -> {
                    navigateToFragment(MainFragment())
                    updateSharedPreferences()
                }
                is AuthProgress -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btLogIn.isEnabled = false
                }
                is AuthError -> {
                    showError(it.errorCode)
                }
            }
        }
    }

    private fun updateSharedPreferences() {
        val pref = PreferenceManager.getDefaultSharedPreferences(requireContext())
        pref.edit().putBoolean(IS_LOGGED, true).apply()
    }

    private fun showError(errorCode: Int) {
        when (errorCode) {
            LogInViewModel.ERROR_EMPTY_LOGIN -> {
                showErrorToast("Все поля должны быть заполнены")
            }
            LogInViewModel.ERROR_EMPTY_PASSWORD -> {
                showErrorToast("Все поля должны быть заполнены")
            }
            LogInViewModel.ERROR_USER_DOES_NOT_EXISTS -> {
                showErrorToast("Пользователь с таким логином не существует")
            }
            LogInViewModel.ERROR_WRONG_PASSWORD -> {
                showErrorToast("Неверный пароль")
            }
        }
    }

    private fun showErrorToast(errorText: String) {
        errorToast?.cancel()
        errorToast = Toast.makeText(
            requireContext(),
            errorText, Toast.LENGTH_SHORT
        )
        errorToast?.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val IS_LOGGED = "is_logged"
    }
}
