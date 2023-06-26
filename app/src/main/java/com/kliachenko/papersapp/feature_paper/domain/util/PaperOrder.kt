package com.kliachenko.papersapp.feature_paper.domain.util

sealed class PaperOrder(val orderType: OrderType) {

    class Title(orderType: OrderType) : PaperOrder(orderType)

    class Date(orderType: OrderType) : PaperOrder(orderType)

    class Color(orderType: OrderType) : PaperOrder(orderType)

}
