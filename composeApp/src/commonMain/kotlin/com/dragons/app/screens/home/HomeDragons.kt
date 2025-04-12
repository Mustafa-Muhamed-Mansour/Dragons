package com.dragons.app.screens.home

import DragonDetailsScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.LocalNavigator
import com.dragons.app.compositions.FABComposition
import com.dragons.app.compositions.states.StateData
import com.dragons.app.compositions.states.StateError
import com.dragons.app.compositions.states.StateLoading
import com.dragons.app.screens.search.SearchDragonsScreen
import com.dragons.app.view_models.DragonsViewModel
import com.seiko.imageloader.rememberImagePainter
import compose.icons.FeatherIcons
import compose.icons.feathericons.Search
import dragons.composeapp.generated.resources.Res
import dragons.composeapp.generated.resources.loading_white
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeDragons(
    viewModel: DragonsViewModel
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val updateQuery by remember { mutableStateOf(value = "") }
    val navigator = LocalNavigator.current
    val scope = rememberCoroutineScope()

    Scaffold(floatingActionButton = {
        FABComposition(
            onClick = {
                navigator?.push(item = SearchDragonsScreen)
            },
            content = {
                Icon(
                    imageVector = FeatherIcons.Search,
                    contentDescription = "search icon",
                    tint = Color.Gray
                )
            },
            modifier = Modifier.padding(all = 16.dp)
        )
    }, floatingActionButtonPosition = FabPosition.End) {
        if (uiState.isLoading) {
            StateLoading(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(all = 8.dp)
                    .fillMaxSize(),
                color = Color.Black
            )
        }

        if (uiState.error.isNotEmpty()) {
            StateError(
                contentAlignment = Alignment.Center,
                contents = {
                    Text(
                        text = " $updateQuery",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                },
                horizontal = Arrangement.Center,
                vertical = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            )
        }
        else {
            LazyVerticalGrid(
                modifier = Modifier.background(color = Color.White),
                columns = GridCells.Fixed(count = 2)
            ) {
                uiState?.data?.let { response ->
                    items(response) {
                        StateData(
                            content = { vertical, horizontal ->
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(all = 8.dp),
                                    verticalArrangement = vertical,
                                    horizontalAlignment = horizontal
                                ) {
                                    val painter = rememberImagePainter(url = it.image,
                                        filterQuality = FilterQuality.High,
                                        placeholderPainter = { painterResource(Res.drawable.loading_white) },
                                        errorPainter = { painterResource(Res.drawable.loading_white) })
                                    Image(
                                        modifier = Modifier
                                            .size(width = 600.dp, height = 400.dp),
                                        painter = painter,
                                        contentDescription = "dragon image",
                                        contentScale = ContentScale.Crop
                                    )

                                    Text(
                                        text = it.name,
                                        modifier = Modifier.fillMaxWidth(),
                                        color = Color.Black,
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.ExtraBold,
                                        textAlign = TextAlign.Center
                                    )

                                    Row(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "Ki:",
                                            color = Color.Black,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = " ${it.ki}",
                                            color = Color.Black,
                                            fontSize = 15.sp
                                        )
                                    }
                                    Row(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(
                                            text = "Max Ki:",
                                            color = Color.Black,
                                            fontSize = 15.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                        Text(
                                            text = " ${it.maxKi}",
                                            color = Color.Black,
                                            fontSize = 15.sp
                                        )
                                    }
                                }
                            },
                            modifier = Modifier.padding(all = 8.dp),
                            onClick = {
                                scope.launch(Dispatchers.IO) {
                                    navigator?.push(item = DragonDetailsScreen(dragonModel = it))
                                }
                            },
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                            color = CardDefaults.cardColors(
                                containerColor = Color.White,
                                contentColor = Color.White
                            )
                        )
                    }
                }
            }
        }
    }
}