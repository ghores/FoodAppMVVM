package com.example.foodappmvvm.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.foodappmvvm.databinding.FragmentFoodsListBinding
import com.example.foodappmvvm.ui.list.adapter.CategoriesAdapter
import com.example.foodappmvvm.utils.MyResponse
import com.example.foodappmvvm.utils.isVisible
import com.example.foodappmvvm.utils.setupListWithAdapter
import com.example.foodappmvvm.utils.setupRecyclerView
import com.example.foodappmvvm.utils.showSnackBar
import com.example.foodappmvvm.viewmodel.FoodsListViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FoodsListFragment : Fragment() {
    //Binding
    private var _binding: FragmentFoodsListBinding? = null
    private val binding get() = _binding

    @Inject
    lateinit var categoriesAdapter: CategoriesAdapter

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
                    Toast.makeText(requireContext(), letter, Toast.LENGTH_SHORT).show()
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
        }
    }

    override fun onStop() {
        super.onStop()
        _binding = null
    }
}