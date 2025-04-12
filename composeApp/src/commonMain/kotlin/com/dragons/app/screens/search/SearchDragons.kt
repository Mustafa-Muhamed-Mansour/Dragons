package com.dragons.app.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.dragons.app.screens.home.HomeDragonsScreen
import com.dragons.app.view_models.SearchDragonsViewModel
import com.seiko.imageloader.rememberImagePainter
import compose.icons.FeatherIcons
import compose.icons.feathericons.Home
import dragons.composeapp.generated.resources.Res
import dragons.composeapp.generated.resources.female
import dragons.composeapp.generated.resources.loading_black
import dragons.composeapp.generated.resources.male
import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchDragons(
    searchDragonsViewModel: SearchDragonsViewModel
) {

    val uiState by searchDragonsViewModel.uiState.collectAsStateWithLifecycle()
    var updateQuery by rememberSaveable {
        mutableStateOf(value = "")
    }
//    val navigator = LocalNavigator.current

    Scaffold(
        topBar = {
            TextField(
                value = updateQuery,
                onValueChange = {
                    updateQuery = it
                    searchDragonsViewModel.updateQuery(s = updateQuery)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Gray,
                    unfocusedIndicatorColor = Color.Black
                ),
                placeholder = {
                    Text(
                        text = "Search of dragon",
                        color = Color.Black,
                        fontSize = 13.sp
                    )
                },
                trailingIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                            updateQuery = String()
                            searchDragonsViewModel.updateQuery(s = String())
                            searchDragonsViewModel.getSearchDragons(String())
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = "close of search"
                    )
                }
            )
        },
        floatingActionButton = {
            val navigator = LocalNavigator.current
            FABComposition(
                onClick = {
                    navigator?.push(item = HomeDragonsScreen)
                },
                content = {
                    Icon(
                        imageVector = FeatherIcons.Home,
                        contentDescription = "home icon",
                        tint = Color.Gray
                    )
                },
                modifier = Modifier.padding(all = 16.dp)
            )
        }, floatingActionButtonPosition = FabPosition.Start
    ) {
        if (uiState.isLoading) {
            StateLoading(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(all = 8.dp)
                    .fillMaxSize()
            )
        }

        if (uiState.error.isNotEmpty()) {
            StateError(
                modifier =  Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
                contents = {
                    Text(
                        text = "Not found dragons with this name",
                        fontSize = 20.sp,
                        color = Color.Black
                    )

                    Text(
                        text = updateQuery,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                },
                horizontal = Arrangement.Center,
                vertical = Alignment.CenterVertically
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
                                    Card(
                                        modifier = Modifier
                                            .padding(all = 8.dp),
                                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color.White, contentColor = Color.White
                                        )
                                    ) {
                                        val painter = rememberImagePainter(url = it.image,
                                            filterQuality = FilterQuality.High,
                                            placeholderPainter = { painterResource(Res.drawable.loading_black) },
                                            errorPainter = { painterResource(Res.drawable.loading_black) })
                                        Image(
                                            modifier = Modifier.size(width = 700.dp, height = 400.dp),
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

                                        Image(
                                            modifier = Modifier
                                                .align(horizontal)
                                                .size(size = 24.dp),
                                            painter = painterResource(
                                                resource = if (it.gender == "Male") Res.drawable.male
                                                else Res.drawable.female
                                            ),
                                            contentDescription = "gender of dragon"
                                        )
                                    }

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