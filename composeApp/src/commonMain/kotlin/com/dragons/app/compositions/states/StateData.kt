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
import androidx.compose.ui.graphics.Color

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
//            Modifier.fillMaxSize().padding(all = 8.dp),
//                Arrangement.Center,
//                Alignment.CenterHorizontally
        ) {
            content(vertical, horizontal)
//            val painter = rememberImagePainter(url = it.image,
//                filterQuality = FilterQuality.High,
//                placeholderPainter = { painterResource(Res.drawable.loading_white) },
//                errorPainter = { painterResource(Res.drawable.loading_white) })
//            Image(
//                modifier = Modifier
//                    .size(width = 600.dp, height = 400.dp),
//                painter = painter,
//                contentDescription = "dragon image",
//                contentScale = ContentScale.Crop
//            )
//
//            Text(
//                text = it.name,
//                modifier = Modifier.fillMaxWidth(),
//                color = Color.Black,
//                fontSize = 17.sp,
//                fontWeight = FontWeight.ExtraBold,
//                textAlign = TextAlign.Center
//            )
//
//            Row(
//                modifier = Modifier.fillMaxWidth()
//
//            ) {
//                Text(
//                    text = "Ki:",
//                    color = Color.Black,
//                    fontSize = 15.sp,
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = " ${it.ki}",
//                    color = Color.Black,
//                    fontSize = 15.sp
//                )
//            }
//            Row(
//                modifier = Modifier.fillMaxWidth()
//            ) {
//                Text(
//                    text = "Max Ki:",
//                    color = Color.Black,
//                    fontSize = 15.sp,
//                    fontWeight = FontWeight.Bold
//                )
//                Text(
//                    text = " ${it.maxKi}",
//                    color = Color.Black,
//                    fontSize = 15.sp
//                )
//            }
        }
    }
}