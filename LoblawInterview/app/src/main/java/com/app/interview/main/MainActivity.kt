package com.app.interview.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.app.interview.LoblawInterviewApplication
import com.app.interview.R
import com.app.interview.cart.CartFragment
import com.app.interview.details.ProductDetailsFragment

class MainActivity : AppCompatActivity() {
    private val mActivityViewModel: MainActivityViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as LoblawInterviewApplication).mAppComponent
            .getMainActivitySubComponent()
            .create()
            .inject(this)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, CartFragment())
                .commit()
        }
        setupObservers()
    }

    private fun setupObservers() {
        mActivityViewModel.clickedProduct.observe(this, { item ->
            item.getItemIfNotConsumed()?.let {
                val fragment = ProductDetailsFragment()
                val bundle = Bundle()
                bundle.putParcelable("product", it)
                fragment.arguments = bundle
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.container, fragment, "product_details")
                    .addToBackStack(null)
                    .commit()
            }
        })
    }


}