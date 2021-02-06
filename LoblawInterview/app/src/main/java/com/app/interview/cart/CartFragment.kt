package com.app.interview.cart

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.interview.LoblawInterviewApplication
import com.app.interview.databinding.FragCartListBinding
import com.app.interview.main.MainActivityViewModel
import com.app.interview.repository.entity.Product
import com.app.interview.utils.ConnectivityChangeLiveData
import javax.inject.Inject

class CartFragment : Fragment() {

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    private val mViewModel by viewModels<CartViewModel> { mViewModelFactory }

    private val mActivityViewModel: MainActivityViewModel by activityViewModels()

    private val onItemClick = object : CartListAdapter.OnProductClick {
        override fun onClick(item: Product) {
            mActivityViewModel.onProductClicked(item)
        }
    }

    private lateinit var mLayoutBinding: FragCartListBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as LoblawInterviewApplication).mAppComponent
            .getCartSubComponent().create().inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mLayoutBinding = FragCartListBinding
            .inflate(inflater, container, false)
        mLayoutBinding.lifecycleOwner = viewLifecycleOwner
        mLayoutBinding.viewModel = mViewModel

        setupObservers()
        setupViews()
        return mLayoutBinding.root
    }

    private fun setupObservers() {
        context?.let {
            ConnectivityChangeLiveData.getInstance(it)
                .observe(viewLifecycleOwner, {
                    mViewModel.updateState(it)
                })
        }

    }

    private fun setupViews() {
        mLayoutBinding.list.apply {
            this.adapter = CartListAdapter(onItemClick)

            this.layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL,
                false
            )
            this.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

}