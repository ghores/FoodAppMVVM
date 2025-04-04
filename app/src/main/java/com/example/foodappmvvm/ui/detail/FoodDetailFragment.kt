package com.example.foodappmvvm.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.foodappmvvm.R
import com.example.foodappmvvm.data.database.FoodEntity
import com.example.foodappmvvm.databinding.FragmentFoodDetailBinding
import com.example.foodappmvvm.ui.detail.player.PlayerActivity
import com.example.foodappmvvm.ui.list.FoodsListFragment.PageState
import com.example.foodappmvvm.utils.CheckConnection
import com.example.foodappmvvm.utils.MyResponse
import com.example.foodappmvvm.utils.VIDEO_ID
import com.example.foodappmvvm.utils.isVisible
import com.example.foodappmvvm.viewmodel.FoodDetailViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class FoodDetailFragment : Fragment() {
    //Binding
    private var _binding: FragmentFoodDetailBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var checkConnection: CheckConnection

    @Inject
    lateinit var entity: FoodEntity

    //Other
    private val viewModel: FoodDetailViewModel by viewModels()
    private val args: FoodDetailFragmentArgs by navArgs()
    private var foodId = 0
    private var isFavorite = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFoodDetailBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Get data
        foodId = args.foodId
        //InitView
        binding?.apply {
            //back
            detailBack.setOnClickListener { findNavController().navigateUp() }
            //Call api
            viewModel.getFoodDetails(foodId)
            viewModel.foodDetailData.observe(viewLifecycleOwner) {
                when (it.status) {
                    MyResponse.Status.LOADING -> {
                        detailLoading.isVisible(true, detailContentLay)
                    }

                    MyResponse.Status.SUCCESS -> {
                        detailLoading.isVisible(false, detailContentLay)
                        //Set data
                        it.data?.meals?.get(0)?.let { itMeal ->
                            //Entity
                            entity.id = itMeal.idMeal!!.toInt()
                            entity.title = itMeal.strMeal.toString()
                            entity.img = itMeal.strMealThumb.toString()
                            //Set data
                            foodCoverImg.load(itMeal.strMealThumb) {
                                crossfade(true)
                                crossfade(500)
                            }
                            foodCategoryTxt.text = itMeal.strCategory
                            foodAreaTxt.text = itMeal.strArea
                            foodTitleTxt.text = itMeal.strMeal
                            foodDescTxt.text = itMeal.strInstructions
                            //Play
                            if (itMeal.strYoutube != null) {
                                foodPlayImg.visibility = View.VISIBLE
                                foodPlayImg.setOnClickListener {
                                    val videoId = itMeal.strYoutube.split("=")[1]
                                    Intent(requireContext(), PlayerActivity::class.java).also {
                                        it.putExtra(VIDEO_ID, videoId)
                                        startActivity(it)
                                    }
                                }
                            } else {
                                foodPlayImg.visibility = View.GONE
                            }
                            //Source
                            if (itMeal.strSource != null) {
                                foodSourceImg.visibility = View.VISIBLE
                                foodSourceImg.setOnClickListener {
                                    startActivity(
                                        Intent(
                                            Intent.ACTION_VIEW,
                                            Uri.parse(itMeal.strSource)
                                        )
                                    )
                                }
                            } else {
                                foodSourceImg.visibility = View.GONE
                            }
                        }
                        //Json Array
                        val jsonData = JSONObject(Gson().toJson(it.data))
                        val meals = jsonData.getJSONArray("meals")
                        val meal = meals.getJSONObject(0)
                        //Ingredient
                        for (i in 1..15) {
                            val ingredient = meal.getString("strIngredient$i")
                            if (ingredient.isNullOrEmpty().not()) {
                                ingredientsTxt.append("$ingredient\n")
                            }
                        }
                        //Measure
                        for (i in 1..15) {
                            val measure = meal.getString("strMeasure$i")
                            if (measure.isNullOrEmpty().not()) {
                                measureTxt.append("$measure\n")
                            }
                        }
                    }

                    MyResponse.Status.ERROR -> {
                        detailLoading.isVisible(false, detailContentLay)
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            //Favorite
            viewModel.existsFood(foodId)
            viewModel.isFavoriteData.observe(viewLifecycleOwner) {
                isFavorite = it
                if (it) {
                    detailFav.setColorFilter(ContextCompat.getColor(requireContext(), R.color.tartOrange))
                } else {
                    detailFav.setColorFilter(ContextCompat.getColor(requireContext(), R.color.black))
                }
            }
            //Save / Delete
            detailFav.setOnClickListener {
                if (isFavorite) {
                    viewModel.deleteNote(entity)
                } else {
                    viewModel.saveNote(entity)
                }
            }
        }
        //Check internet
        checkConnection.observe(viewLifecycleOwner) {
            if (it) {
                checkConnectionOrEmpty(false, PageState.NONE)
            } else {
                checkConnectionOrEmpty(true, PageState.NETWORK)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        _binding = null
    }

    private fun checkConnectionOrEmpty(isShownError: Boolean, state: PageState) {
        binding?.apply {
            if (isShownError) {
                homeDisLay.isVisible(true, detailContentLay)
                when (state) {
                    PageState.EMPTY -> {
                        disconnectLay.disImg.setImageResource(R.drawable.box)
                        disconnectLay.disTxt.text = getString(R.string.emptyList)
                    }

                    PageState.NETWORK -> {
                        disconnectLay.disImg.setImageResource(R.drawable.disconnect)
                        disconnectLay.disTxt.text = getString(R.string.checkInternet)
                    }

                    else -> {}
                }
            } else {
                homeDisLay.isVisible(false, detailContentLay)
            }
        }
    }
}