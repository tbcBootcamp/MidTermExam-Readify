package com.example.midtermexam.presentation.fragments.main

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.midtermexam.R
import com.example.midtermexam.databinding.FragmentMainBinding
import com.example.midtermexam.presentation.base.BaseFragment


class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {


    override fun setUp() {
        val bottomNavigationView = binding.bottomNavigationView
        val navHostFragment = childFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
        val navController = navHostFragment.navController
        bottomNavigationView.setupWithNavController(navController)


    }


}