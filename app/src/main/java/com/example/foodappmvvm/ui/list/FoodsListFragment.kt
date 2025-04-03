package com.example.foodappmvvm.ui.list

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.foodappmvvm.R
import com.example.foodappmvvm.databinding.FragmentFoodsListBinding
import com.example.foodappmvvm.ui.list.adapter.CategoriesAdapter
import com.example.foodappmvvm.ui.list.adapter.FoodsAdapter
import com.example.foodappmvvm.utils.CheckConnection
import com.example.foodappmvvm.utils.MyResponse
import com.example.foodappmvvm.utils.isVisible
import com.example.foodappmvvm.utils.setupListWithAdapter
import com.example.foodappmvvm.utils.setupRecyclerView
import com.example.foodappmvvm.utils.showSnackBar
import com.example.foodappmvvm.viewmodel.FoodsListViewModel
import com.google.android.youtube.player.internal.f
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FoodsListFragment : Fragment() {
    //Binding
    private var _binding: FragmentFoodsListBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter

    @Inject
    lateinit var foodsAdapter: FoodsAdapter

    @Inject
    lateinit var checkConnection: CheckConnection

    enum class PageState { EMPTY, NETWORK, NONE }

    //Other
    private val viewModel: FoodsListViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFoodsListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding?.apply {
            //Random food
            viewModel.getFoodRandom()
            viewModel.randomFoodData.observe(viewLifecycleOwner) {
                it[0].let { meal ->
                    headerImg.load(meal.strMealThumb) {
                        crossfade(true)
                        crossfade(500)
                    }
                }
            }
            //Filters
            viewModel.getFilterList()
            viewModel.filtersListData.observe(viewLifecycleOwner) {
                filterSpinner.setupListWithAdapter(it) { letter ->
                    viewModel.getFoodList(letter)
                }
            }
            //Category
            viewModel.getCategoriesFoodList()
            viewModel.categoriesListData.observe(viewLifecycleOwner) {
                when (it.status) {
                    MyResponse.Status.LOADING -> {
                        homeCategoryLoading.isVisible(true, categoryList)
                    }

                    MyResponse.Status.SUCCESS -> {
                        homeCategoryLoading.isVisible(false, categoryList)
                        categoriesAdapter.setData(it.data!!.categories)
                        categoryList.setupRecyclerView(
                            LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false),
                            categoriesAdapter
                        )
                    }

                    MyResponse.Status.ERROR -> {
                        homeCategoryLoading.isVisible(false, categoryList)
                        binding!!.root.showSnackBar(it.message.toString())
                    }
                }
            }
            categoriesAdapter.setOnItemClickListener {
                viewModel.getFoodsByCategory(it.strCategory.toString())
            }
            //Foods
            viewModel.getFoodList("A")
            viewModel.foodListData.observe(viewLifecycleOwner) {
                when (it.status) {
                    MyResponse.Status.LOADING -> {
                        homeFoodsLoading.isVisible(true, foodsList)
                    }

                    MyResponse.Status.SUCCESS -> {
                        homeFoodsLoading.isVisible(false, foodsList)
                        if (it.data!!.meals != null) {
                            if (it.data.meals.isNotEmpty()) {
                                checkConnectionOrEmpty(false, PageState.NONE)
                                foodsAdapter.setData(it.data.meals)
                                foodsList.setupRecyclerView(LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false),
                                    foodsAdapter
                                )
                            }
                        } else {
                            checkConnectionOrEmpty(true, PageState.EMPTY)
                        }
                    }

                    MyResponse.Status.ERROR -> {
                        homeCategoryLoading.isVisible(false, categoryList)
                        binding!!.root.showSnackBar(it.message.toString())
                    }
                }
            }
            //Search
            searchEdt.addTextChangedListener {
                if (it.toString().length > 2) {
                    viewModel.getSearchFoodList(it.toString())
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
    }

    override fun onStop() {
        super.onStop()
        _binding = null
    }

    private fun checkConnectionOrEmpty(isShownError: Boolean, state: PageState) {
        binding?.apply {
            if (isShownError) {
                homeDisLay.isVisible(true, homeContent)
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
                homeDisLay.isVisible(false, homeContent)
            }
        }
    }
}

