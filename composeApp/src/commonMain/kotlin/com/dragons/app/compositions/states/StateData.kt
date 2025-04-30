package com.dragons.app.compositions.states

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun StateData(
    content: @Composable ColumnScope.(Arrangement.Vertical, Alignment.Horizontal) -> Unit,
    onClick: () -> Unit = {},
    vertical: Arrangement.Vertical = Arrangement.Center,
    horizontal: Alignment.Horizontal = Alignment.CenterHorizontally,
    elevation: CardElevation = CardDefaults.cardElevation(),
    color: CardColors = CardDefaults.cardColors(),
    modifier: Modifier = Modifier
) {

    Card(
        modifier = modifier
            .clickable {
                onClick()
            },
        elevation = elevation,
        colors = color
    ) {
        Column(
            modifier = modifier,
        ) {
            content(vertical, horizontal)
        }
    }
}