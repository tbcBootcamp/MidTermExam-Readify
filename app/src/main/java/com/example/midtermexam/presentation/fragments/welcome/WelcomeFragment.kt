package com.example.midtermexam.presentation.fragments.welcome

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.midtermexam.databinding.FragmentWelcomeBinding
import com.example.midtermexam.presentation.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {
    private val viewModel: WelcomeViewModel by viewModels()

    override fun listeners() {
        binding.apply {
            btnRegister.setOnClickListener {
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToRegisterFragment())
            }
            btnLogin.setOnClickListener {
                findNavController().navigate(WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment())
            }
        }
    }

    override fun observers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isUserLoggedIn.collect {
                    if (it)
                        findNavController().navigate(
                            WelcomeFragmentDirections.actionWelcomeFragmentToMainFragment()
                        )
                }
            }
        }
    }
}