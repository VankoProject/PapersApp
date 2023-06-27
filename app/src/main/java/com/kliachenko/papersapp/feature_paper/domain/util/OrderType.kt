package com.kliachenko.papersapp.feature_paper.domain.util

sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
