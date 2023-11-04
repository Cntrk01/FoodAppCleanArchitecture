package com.cantrk.foodappcleanarchitecture.ui

import android.os.Bundle
import android.view.View
import com.cantrk.foodappcleanarchitecture.BaseFragment
import com.cantrk.foodappcleanarchitecture.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NavFavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}