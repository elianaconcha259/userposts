package com.example.shoppingapp.domain.usecase

import com.example.shoppingapp.domain.model.ProductModel
import com.example.shoppingapp.util.common.AsyncResult

interface GetProductsUseCase {
    suspend operator fun invoke(): AsyncResult<List<ProductModel>>
}