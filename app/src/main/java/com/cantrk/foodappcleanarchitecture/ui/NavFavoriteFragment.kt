package com.cantrk.foodappcleanarchitecture.ui

import android.os.Bundle
import android.view.View
import com.cantrk.foodappcleanarchitecture.util.BaseFragment
import com.cantrk.foodappcleanarchitecture.databinding.FragmentNavFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NavFavoriteFragment : BaseFragment<FragmentNavFavoriteBinding>(FragmentNavFavoriteBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}