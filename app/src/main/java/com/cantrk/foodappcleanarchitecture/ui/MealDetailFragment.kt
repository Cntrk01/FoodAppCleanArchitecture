package com.cantrk.foodappcleanarchitecture.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.bumptech.glide.Glide
import com.cantrk.foodappcleanarchitecture.util.BaseFragment
import com.cantrk.foodappcleanarchitecture.databinding.FragmentMealDetailBinding
import com.cantrk.foodappcleanarchitecture.dataclass.FoodSaveEntity
import com.cantrk.foodappcleanarchitecture.dataclass.RandomMeal
import com.cantrk.foodappcleanarchitecture.viewmodel.MealDetailViewModel
import com.cantrk.foodappcleanarchitecture.viewmodel.MealGetFromDatabaseViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MealDetailFragment :
    BaseFragment<FragmentMealDetailBinding>(FragmentMealDetailBinding::inflate) {
    private lateinit var webView: WebView
    private val viewModel: MealDetailViewModel by viewModels()
    private val mealDatabaseViewModel: MealGetFromDatabaseViewModel by viewModels()
    private var foodSaveEntity = FoodSaveEntity()
    private var isSavedData = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = binding.webView
        getDataFromViewModel()
        checkFavoriteButton()
    }
    private fun getDataFromViewModel() {
        with(mealDatabaseViewModel) {
            getMealClickedItem(MealDetailViewModel.MEAL_ID)

            //buradaki problem şuydu isSaved data true olmasına ragmen if blogu calısmıyor else çalışıyordu.Ben bu değeri scope içerisinde güncelliyorum
            //sanırım oradan dışarıdaki değer güncellenmiyor . Sanırım scope içinde oldugu için değer birtek orada kalıyor.Bundan dolayı if else blogunu scope içine
            //aldım artık roomda veri varsa if blogundan roomdan geliyor yoksa apiden geliyor.
            viewModelScope.launch {
                getMealClickedItem.collectLatest {
                    isSavedData = it.isHave!!
                    binding.favoriteButton.isChecked = isSavedData

                    if (isSavedData) {
                        mealDatabaseViewModel.viewModelScope.launch {
                            mealDatabaseViewModel.getMealClickedItemDetail.collectLatest {
                                binding.apply {
                                    if (it.isLoading == true) {
                                        mealDataConstraint.visibility = View.GONE
                                        progressBar.isVisible = true
                                    }
                                    if (it.error != "") {
                                        mealDataConstraint.visibility = View.GONE
                                        progressBar.isVisible = false
                                        errorText.isVisible = true
                                        errorText.text = it.error
                                    }
                                    if (it.data != null) {
                                        mealDataConstraint.isVisible = true
                                        progressBar.isVisible = false
                                        setDbDataForXml(it.data)
                                        Log.e("ROOM", "Roomdan geldi")
                                    }
                                }
                            }
                        }
                    } else {
                        viewModel.viewModelScope.launch {
                            viewModel.mealDetail.collectLatest {
                                binding.apply {
                                    if (it.isLoading) {
                                        mealDataConstraint.visibility = View.GONE
                                        progressBar.isVisible = true
                                    }
                                    if (it.error != "") {
                                        mealDataConstraint.visibility = View.GONE
                                        progressBar.isVisible = false
                                        errorText.isVisible = true
                                        errorText.text = it.error
                                    }
                                    if (it.category?.isNotEmpty() == true) {
                                        mealDataConstraint.isVisible = true
                                        progressBar.isVisible = false
                                        it.category?.let {categoryIt->
                                            categoryIt.map { its ->
                                                setDataForXml(its)
                                                foodSaveEntity = FoodSaveEntity(
                                                    null,
                                                    its.strMealThumb,
                                                    its.strMeal,
                                                    its.strCategory,
                                                    its.strArea,
                                                    its.strInstructions,
                                                    its.strYoutube,
                                                    its.idMeal
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    private fun setDbDataForXml(data: FoodSaveEntity) {
        binding.apply {
            mealDetailName.text = data.title
            Glide.with(requireContext()).load(data.image).into(mealImage)
            categoryName.text = "Category : ${data.category}"
            locationName.text = "Location : ${data.location}"
            mealName.text = data.title
            mealDescription.text = data.desc

            youtubeIcon.setOnClickListener {
                openYoutube(data.youtubeUrl!!)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setDataForXml(its: RandomMeal) {
        binding.apply {
            mealDetailName.text = its.strMeal
            Glide.with(requireContext()).load(its.strMealThumb).into(mealImage)
            categoryName.text = "Category : ${its.strCategory}"
            locationName.text = "Location : ${its.strArea}"
            mealName.text = its.strMeal
            mealDescription.text = its.strInstructions

            youtubeIcon.setOnClickListener {
                openYoutube(its.strYoutube)
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun openYoutube(yt: String) {
        println(yt)
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.loadUrl(yt)
        webView.isVisible = true
    }

    private fun checkFavoriteButton() {
        binding.favoriteButton.setOnClickListener {
            isSavedData = !isSavedData
            if (isSavedData) {
                mealDatabaseViewModel.addMeal(foodSaveEntity)
            } else {
                mealDatabaseViewModel.deleteMeal(foodSaveEntity.mealId!!)
            }
            binding.favoriteButton.isChecked = isSavedData
        }
    }
}