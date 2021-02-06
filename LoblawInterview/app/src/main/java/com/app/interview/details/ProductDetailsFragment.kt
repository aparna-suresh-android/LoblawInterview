package com.app.interview.details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.app.interview.LoblawInterviewApplication
import com.app.interview.databinding.FragProductDetailsBinding
import com.app.interview.details.di.GenericAssistedViewModelFactory
import javax.inject.Inject

class ProductDetailsFragment : Fragment() {

    @Inject
    lateinit var mViewModelFactory: ProductDetailsViewModel.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as LoblawInterviewApplication).mAppComponent
            .getDetailsSubComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val layoutBinding = FragProductDetailsBinding
            .inflate(inflater, container, false)
        layoutBinding.lifecycleOwner = viewLifecycleOwner
        val viewModel: ProductDetailsViewModel by viewModels {
            GenericAssistedViewModelFactory(mViewModelFactory, this, arguments)
        }
        layoutBinding.viewModel = viewModel
        return layoutBinding.root
    }

}