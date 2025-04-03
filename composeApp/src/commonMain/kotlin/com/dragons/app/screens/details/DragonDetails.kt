import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dragons.app.entity.DragonModel
import com.dragons.app.entity.TransformationModel
import com.dragons.app.view_model.DragonDetailsViewModel
import com.seiko.imageloader.rememberImagePainter
import compose.icons.FeatherIcons
import compose.icons.feathericons.Calendar
import compose.icons.feathericons.Clock
import compose.icons.feathericons.Star
import compose.icons.feathericons.User
import dragons.composeapp.generated.resources.Res
import dragons.composeapp.generated.resources.loading_black
import org.jetbrains.compose.resources.painterResource

@Composable
fun DragonDetails(
    viewModel: DragonDetailsViewModel,
//    transformation: TransformationModel,
    dragonModel: DragonModel
) {

    LaunchedEffect(key1 = dragonModel) {
        viewModel.fetchDragonDetails(id = dragonModel.id.toString())
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isExpanded by rememberSaveable { mutableStateOf(value = false) }
    var isExpandedOrigin by rememberSaveable { mutableStateOf(value = false) }

    if (uiState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = Color.Black)
        }
    }

    if (uiState.error.isNotEmpty()) {
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = uiState.error,
                fontSize = 15.sp,
                color = Color.Black
            )
        }
    } else {
        uiState?.data?.let { response ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .verticalScroll(rememberScrollState())
//                    .horizontalScroll(rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(space = 8.dp)
                ) {
                    Row(
//                        size(width = 450.dp, height = 350.dp)
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                    ) {
                        response.transformations.forEach { images ->
//                        Row(
//                            modifier = Modifier
//                                .size(width = 450.dp, height = 350.dp)
//                        ) {
                            val painter = rememberImagePainter(
                                url = images.image,
//                        url = dragonModel.transformations.size,
                                filterQuality = FilterQuality.High,
                                placeholderPainter = { painterResource(resource = Res.drawable.loading_black) },
                                errorPainter = { painterResource(resource = Res.drawable.loading_black) }
                            )

                            Image(
//                            modifier = Modifier.size(width = 450.dp, height = 350.dp),
                                painter = painter,
                                contentDescription = "transformation dragon",
                            )
                        }
                    }
//                    response.transformations.forEach { images ->
////                        Row(
////                            modifier = Modifier
////                                .size(width = 450.dp, height = 350.dp)
////                        ) {
//                        val painter = rememberImagePainter(
//                            url = images.image,
////                        url = dragonModel.transformations.size,
//                            filterQuality = FilterQuality.High,
//                            placeholderPainter = { painterResource(resource = Res.drawable.loading_black) },
//                            errorPainter = { painterResource(resource = Res.drawable.loading_black) }
//                        )
//
//                        Image(
////                            modifier = Modifier.size(width = 450.dp, height = 350.dp),
//                            painter = painter,
//                            contentDescription = "transformation dragon",
//                        )
//                    }
//                    }
//                    for (image in response.transformations) {
//                        Row(
//                            modifier = Modifier
//                                .size(width = 450.dp, height = 350.dp)
//                        ) {
//                            val painter = rememberImagePainter(
//                                url = image.image,
////                        url = dragonModel.transformations.size,
//                                filterQuality = FilterQuality.High,
//                                placeholderPainter = { painterResource(resource = Res.drawable.loading_black) },
//                                errorPainter = { painterResource(resource = Res.drawable.loading_black) }
//                            )
//
//                            Image(
////                            modifier = Modifier.size(width = 450.dp, height = 350.dp),
//                                painter = painter,
//                                contentDescription = "transformation dragon",
//                            )
//                        }
//                        val painter = rememberImagePainter(
//                            url = image.image,
////                        url = dragonModel.transformations.size,
//                            filterQuality = FilterQuality.High,
//                            placeholderPainter = { painterResource(resource = Res.drawable.loading_black) },
//                            errorPainter = { painterResource(resource = Res.drawable.loading_black) }
//                        )
//
//                        Image(
////                            modifier = Modifier.size(width = 450.dp, height = 350.dp),
//                            painter = painter,
//                            contentDescription = "transformation dragon",
//                        )
//                    }
//                    val painter = rememberImagePainter(
//                        url = response.transformations,
////                        url = dragonModel.transformations.size,
//                        filterQuality = FilterQuality.High,
//                        placeholderPainter = { painterResource(resource = Res.drawable.loading_black) },
//                        errorPainter = { painterResource(resource = Res.drawable.loading_black) }
//                    )
//                    Image(
//                        modifier = Modifier.size(width = 450.dp, height = 350.dp),
//                        painter = painter,
//                        contentDescription = "transformation dragon",
//                    )

                    Column(
//                        modifier = Modifier
//                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(space = 8.dp)
                    ) {
//                    }
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = dragonModel.name,
                            color = Color.Black,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.Bold,
//                            textAlign = TextAlign.Center
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    isExpanded = isExpanded.not()
                                },
                            text = dragonModel.description,
                            color = Color.Black,
                            fontSize = 20.sp,
                            maxLines = if (isExpanded) Int.MAX_VALUE else 2
                        )
//                        response.originPlanet.image.forEach { imageOriginPlant ->
//                          Row(
//                              modifier = Modifier.fillMaxWidth(),
//                              horizontalArrangement = Arrangement.spacedBy(space = 8.dp)
//                          ) {
                              val originPlanet = rememberImagePainter(
                                  url = response.originPlanet.image,
                                  placeholderPainter = { painterResource(resource = Res.drawable.loading_black) },
                                  errorPainter = {painterResource(resource = Res.drawable.loading_black)})

                              Image(painter = originPlanet, contentDescription = "origin plant image")
//                          }
//                        }
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
//                                .clickable {
//                                    isExpanded = isExpanded.not()
//                                }
                            text = response.originPlanet.name,
                            color = Color.Black,
                            fontSize = 20.sp,
//                            maxLines = if (isExpanded) Int.MAX_VALUE else 2
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    isExpandedOrigin = isExpandedOrigin.not()
                                },
                            text = response.originPlanet.description,
                            color = Color.Black,
                            fontSize = 20.sp,
                            maxLines = if (isExpandedOrigin) Int.MAX_VALUE else 3
                        )

//                        val originPlanet = rememberImagePainter(
//                            url = response.originPlanet.image,
//                            placeholderPainter = { painterResource(resource = Res.drawable.loading_black) },
//                            errorPainter = {painterResource(resource = Res.drawable.loading_black)})
//
//                        Image(painter = originPlanet, contentDescription = "origin plant image")

                        Row(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Language: ",
                                color = Color.Black,
                                fontSize = 17.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = dragonModel.race,
                                color = Color.Black,
                                fontSize = 17.sp
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp).let {
                                Arrangement.SpaceBetween
                            }
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = FeatherIcons.User,
                                    contentDescription = "director of movie"
                                )
                                Text(
                                    text = dragonModel.affiliation,
                                    color = Color.Black,
                                    fontSize = 15.sp
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = FeatherIcons.Clock,
                                    contentDescription = "runTime of movie"
                                )
                                Text(
                                    text = dragonModel.affiliation,
                                    color = Color.Black,
                                    fontSize = 15.sp
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = FeatherIcons.Calendar,
                                    contentDescription = "year of movie"
                                )
                                Text(
                                    text = dragonModel.maxKi,
                                    color = Color.Black,
                                    fontSize = 15.sp
                                )
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp).let {
                                Arrangement.SpaceEvenly
                            }
                        ) {
//                        Column(
//                            horizontalAlignment = Alignment.CenterHorizontally
//                        ) {
//                            Icon(
//                                imageVector = FeatherIcons.Star,
//                                contentDescription = "rating of movie"
//                            )
//                            Text(
//                                text = "${response.imdbRating} / 10",
//                                color = Color.Black,
//                                fontSize = 15.sp
//                            )
//                        }

//                        Column(
//                            horizontalAlignment = Alignment.CenterHorizontally
//                        ) {
//                            Image(
//                                painter = painterResource(Res.drawable.voting),
//                                contentDescription = "voting of movie"
//                            )
//                            Text(
//                                text = response.imdbVotes,
//                                color = Color.Black,
//                                fontSize = 15.sp
//                            )
//                        }
                        }
                    }
                }
            }
        }
    }
}