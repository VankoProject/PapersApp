package com.kliachenko.papersapp.feature_paper.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kliachenko.papersapp.ui.theme.*

@Entity
data class Paper(

    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val content: String,
    val timestamp: Long,
) {
    companion object {
        val paperColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}
