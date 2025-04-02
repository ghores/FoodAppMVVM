package com.example.foodappmvvm.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.foodappmvvm.databinding.FragmentFoodsListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodsListFragment : Fragment() {
    //Binding
    private var _binding: FragmentFoodsListBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodsListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //InitViews
        binding?.apply {

        }
    }

    override fun onStop() {
        super.onStop()
        _binding = null
    }
}