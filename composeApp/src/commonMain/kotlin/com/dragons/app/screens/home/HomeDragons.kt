package com.dragons.app.screens.home

import DragonDetailsScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.LocalNavigator
import com.dragons.app.screens.search.SearchDragonsScreen
import com.dragons.app.view_model.DragonsViewModel
import com.seiko.imageloader.rememberImagePainter
import compose.icons.FeatherIcons
import compose.icons.feathericons.Home
import compose.icons.feathericons.Search
import dragons.composeapp.generated.resources.Res
import dragons.composeapp.generated.resources.loading_white
import org.jetbrains.compose.resources.painterResource

@Composable
fun HomeDragons(
    viewModel: DragonsViewModel
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    var updateQuery by rememberSaveable {
        mutableStateOf(value = "")
    }

    Scaffold(floatingActionButton = {
        val navigator = LocalNavigator.current

        FloatingActionButton(
            modifier = Modifier.padding(all = 16.dp),
            onClick = {
                navigator?.push(item = SearchDragonsScreen)
            },
            backgroundColor = Color.White
        ) {
            Icon(
                imageVector = FeatherIcons.Search,
                contentDescription = "search icon",
                tint = Color.Gray
            )
        }
    }, floatingActionButtonPosition = FabPosition.End) {
        if (uiState.isLoading) {
            Box(
                modifier = Modifier.padding(8.dp).fillMaxSize(), contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    color = Color.Black
                )
            }
        }

        if (uiState.error.isNotEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Not found movies with this name",
                        fontSize = 20.sp,
                        color = Color.Black
                    )

                    Text(
                        text = " $updateQuery",
                        fontSize = 20.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        } else {

            val navigator = LocalNavigator.current

            viewModel.fetchDragons(page = "1")
            LazyVerticalGrid(
                modifier = Modifier.background(color = Color.White),
                columns = GridCells.Fixed(count = 2)
            ) {
                uiState?.data?.let { response ->
                    items(response) {

                        Card(
                            modifier = Modifier.padding(all = 8.dp).clickable {
                                navigator?.push(DragonDetailsScreen(dragonModel = it))
//                                        Napier.v(tag = "Id of dragon", message = "this is id = ${dragon.id}")
                            },
                            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color.Black, contentColor = Color.Black
                            )
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize().padding(all = 8.dp),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                val painter = rememberImagePainter(url = it.image,
                                    filterQuality = FilterQuality.High,
                                    placeholderPainter = { painterResource(Res.drawable.loading_white) },
                                    errorPainter = { painterResource(Res.drawable.loading_white) })
                                Image(
                                    modifier = Modifier.size(width = 600.dp, height = 400.dp)
//                                        .height(height = 500.dp)
//                                        .graphicsLayer(translationX = 25f, translationY = 40f)
                                    ,
                                    painter = painter,
                                    contentDescription = "dragon image",
                                    contentScale = ContentScale.Crop
                                )

                                Text(
                                    text = it.name,
                                    modifier = Modifier.fillMaxWidth(),
                                    color = Color.White,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    textAlign = TextAlign.Center
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth()

                                ) {
                                    Text(
                                        text = "Ki:",
                                        color = Color.White,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = " ${it.ki}", color = Color.White, fontSize = 15.sp
                                    )
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth()

                                ) {
                                    Text(
                                        text = "Max Ki:",
                                        color = Color.White,
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    Text(
                                        text = " ${it.maxKi}", color = Color.White, fontSize = 15.sp
                                    )
                                }

//                                Text(
//                                    text = "Race: ${it.race}",
//                                    modifier = Modifier.fillMaxWidth(),
//                                    color = Color.White,
//                                    fontSize = 15.sp,
//                                    fontWeight = FontWeight.Normal,
//                                    textAlign = TextAlign.Start
//                                )

//                                Row(
//                                    modifier = Modifier
//                                        .fillMaxWidth(),
//                                    horizontalArrangement = Arrangement.spacedBy(5.dp).let {
//                                        Arrangement.SpaceBetween
//                                    }
//                                ) {
////                                    Text(
////                                        text = "Ki: ${it.ki}",
////                                        color = Color.White,
////                                        fontSize = 15.sp,
////                                        fontWeight = FontWeight.SemiBold,
////                                    )
////
////                                    Text(
////                                        text = "Max Ki: ${it.maxKi}",
////                                        color = Color.White,
////                                        fontSize = 15.sp,
////                                        fontWeight = FontWeight.SemiBold,
////                                    )
//
//                                    Text(
//                                        text = "Race: ${it.race}",
////                                        modifier = Modifier.fillMaxWidth(),
//                                        color = Color.White,
//                                        fontSize = 13.sp,
//                                        fontWeight = FontWeight.SemiBold
////                                        ,
////                                        textAlign = TextAlign.Start
//                                    )
//
//                                    Text(
//                                        text = "Affiliation: ${it.affiliation}",
////                                        modifier = Modifier.fillMaxWidth(),
//                                        color = Color.White,
//                                        fontSize = 13.sp,
//                                        fontWeight = FontWeight.SemiBold
////                                        textAlign = TextAlign.Start,
//                                    )
//                                }
                            }
                        }
                    }
                }
            }
//            uiState?.data?.let { response ->
//                LazyColumn {
//                    items(response) {
//                        Card(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .padding(all = 16.dp)
//                                .clickable {
////                                    navigator?.push(MovieDetailsScreen(imdbId = it.imdbID))
//                                },
//                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
//                            colors = CardDefaults.cardColors(
//                                containerColor = Color.White, contentColor = Color.White
//                            )
//                        ) {
//                            Column(
//                                modifier = Modifier
//                                    .padding(start = 16.dp, top = 8.dp, end = 16.dp, bottom = 8.dp)
//                            ) {
//
////                                val painter = rememberImagePainter(
////                                    url = it.Poster, filterQuality = FilterQuality.High,
////                                    placeholderPainter = { painterResource(Res.drawable.loading_black) },
////                                    errorPainter = { painterResource(Res.drawable.loading_black) }
////                                )
////                                Image(
////                                    modifier = Modifier.fillMaxWidth(),
////                                    painter = painter,
////                                    contentDescription = "movies image",
////                                    contentScale = ContentScale.Crop
////                                )
//
////                                Text(
////                                    modifier = Modifier.fillMaxWidth(),
////                                    text = it.Title,
////                                    color = Color.Black,
////                                    fontSize = 20.sp,
////                                    fontWeight = FontWeight.Bold,
////                                    textAlign = TextAlign.Center
////                                )
//
//                                Row(
//                                    modifier = Modifier
//                                        .fillMaxWidth(),
//                                    horizontalArrangement = Arrangement.spacedBy(5.dp).let {
//                                        Arrangement.SpaceBetween
//                                    }
//                                ) {
//                                    Column(
//                                        horizontalAlignment = Alignment.CenterHorizontally
//                                    ) {
//                                        Icon(
//                                            imageVector = FeatherIcons.Type,
//                                            contentDescription = "type of movie",
//                                            tint = Color.Gray
//                                        )
////                                        Text(
////                                            text = it.Type,
////                                            color = Color.Black,
////                                            fontSize = 15.sp
////                                        )
//                                    }
//
//                                    Column(
//                                        horizontalAlignment = Alignment.CenterHorizontally,
//                                    ) {
//                                        Icon(
//                                            imageVector = FeatherIcons.Calendar,
//                                            contentDescription = "year of movie",
//                                            tint = Color.Gray
//                                        )
////                                        Text(
////                                            text = it.Year,
////                                            color = Color.Black,
////                                            fontSize = 15.sp
////                                        )
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
        }
    }
}