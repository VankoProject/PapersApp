package com.kliachenko.papersapp.feature_paper.domain.util

import com.kliachenko.papersapp.feature_paper.domain.model.Paper

sealed class PaperOrder(val orderType: OrderType) {

    class Title(orderType: OrderType) : PaperOrder(orderType)

    class Date(orderType: OrderType) : PaperOrder(orderType)

    class Color(orderType: OrderType) : PaperOrder(orderType)

    fun copy(orderType: OrderType): PaperOrder {
        return when(this) {
            is Title -> Title(orderType)
            is Date -> Date(orderType)
            is Color -> Color(orderType)
        }
    }

}
