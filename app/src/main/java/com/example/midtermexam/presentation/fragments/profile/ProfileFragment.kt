package com.example.midtermexam.presentation.fragments.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.midtermexam.databinding.FragmentProfileBinding
import com.example.midtermexam.presentation.base.BaseFragment
import com.example.midtermexam.presentation.fragments.main.MainFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {


    private val viewModel: ProfileViewModel by viewModels()

    override fun listeners() {

        binding.btnLogOut.setOnClickListener {
            viewModel.logOut()
        }

    }


    override fun observers() {

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.logOut.collect {
                    if (it) {
                        parentFragment?.parentFragment?.findNavController()
                            ?.navigate(MainFragmentDirections.actionMainFragmentToWelcomeFragment())
                    }
                }
            }
        }
    }

}