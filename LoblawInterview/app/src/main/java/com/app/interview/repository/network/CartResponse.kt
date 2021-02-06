package com.app.interview.repository.network

import com.app.interview.repository.entity.Product

data class CartResponse(
    val entries : Array<Product>
);