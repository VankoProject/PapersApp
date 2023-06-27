package com.kliachenko.papersapp.feature_paper.presentation.papers.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kliachenko.papersapp.feature_paper.domain.util.OrderType
import com.kliachenko.papersapp.feature_paper.domain.util.PaperOrder


@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    paperOrder: PaperOrder = PaperOrder.Date(OrderType.Descending),
    onOrderChange: (PaperOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Title",
                selected = paperOrder is PaperOrder.Title,
                onSelect = { onOrderChange(PaperOrder.Title(paperOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Date",
                selected = paperOrder is PaperOrder.Date,
                onSelect = { onOrderChange(PaperOrder.Date(paperOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Color",
                selected = paperOrder is PaperOrder.Color,
                onSelect = { onOrderChange(PaperOrder.Color(paperOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(
                text = "Ascending",
                selected = paperOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(paperOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DefaultRadioButton(
                text = "Descending",
                selected = paperOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(paperOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}