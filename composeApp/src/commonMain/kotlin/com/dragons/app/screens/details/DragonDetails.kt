import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cafe.adriel.voyager.navigator.LocalNavigator
import com.dragons.app.compositions.states.StateData
import com.dragons.app.compositions.states.StateError
import com.dragons.app.compositions.states.StateLoading
import com.dragons.domain.entity.DragonModel
import com.dragons.app.screens.home.HomeDragonsScreen
import com.dragons.app.view_models.DragonDetailsViewModel
import com.seiko.imageloader.rememberImagePainter
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowLeft
import dragons.composeapp.generated.resources.Res
import dragons.composeapp.generated.resources.female
import dragons.composeapp.generated.resources.fighter
import dragons.composeapp.generated.resources.loading_black
import dragons.composeapp.generated.resources.male
import dragons.composeapp.generated.resources.not_found
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource

@Composable
fun DragonDetails(
    viewModel: DragonDetailsViewModel,
    dragonModel: DragonModel
) {
    LaunchedEffect(key1 = dragonModel) {
        viewModel.fetchDragonDetails(id = dragonModel.id.toString())
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var isExpandedDescri by remember { mutableStateOf(value = false) }
    var isExpandedOrigin by remember { mutableStateOf(value = false) }
    val navigator = LocalNavigator.current
    val scrollState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    if (uiState.isLoading) {
        StateLoading(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(all = 8.dp).fillMaxSize(),
            color = Color.Black
        )
    }

    if (uiState.error.isNotEmpty()) {
        StateError(
            contentAlignment = Alignment.Center,
            contents = {
                Text(
                    text = uiState.error, fontSize = 15.sp, color = Color.Black
                )
            },
            horizontal = Arrangement.Center,
            vertical = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().padding(all = 8.dp)
        )
    }

    else {
        uiState?.data?.let { response ->
            StateData(
                content = { vertical, horizontal ->
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Icon(
                            modifier = Modifier
                                .size(size = 24.dp).clickable {
                                    navigator?.push(item = HomeDragonsScreen)
                                },
                            imageVector = FeatherIcons.ArrowLeft,
                            contentDescription = "home of dragons",
                            tint = Color.Gray,
                        )

                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = vertical.apply { Arrangement.spacedBy(space = 8.dp) },
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = dragonModel.name,
                                color = Color.Black,
                                fontSize = 23.sp,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.Center
                            )

                            LazyRow(
                                state = scrollState,
                                modifier = Modifier
                                    .size(width = 900.dp, height = 400.dp).draggable(
                                        orientation = Orientation.Horizontal,
                                        state = rememberDraggableState { delta ->
                                            scope.launch {
                                                scrollState.scrollBy(value = -delta)
                                            }
                                        })
                            ) {
                                item {
                                    if (response?.transformations.isNullOrEmpty()) {
                                        Image(
                                            modifier = Modifier
                                                .size(width = 300.dp, height = 200.dp)
                                                .align(Alignment.CenterHorizontally),
                                            painter = painterResource(Res.drawable.not_found),
                                            contentDescription = "transformation dragon",
                                        )
                                    } else {
                                        response.transformations.forEach { images ->
                                            val painter = rememberImagePainter(url = images.image,
                                                filterQuality = FilterQuality.High,
                                                placeholderPainter = { painterResource(resource = Res.drawable.loading_black) },
                                                errorPainter = { painterResource(resource = Res.drawable.loading_black) })
                                            Image(
                                                painter = painter,
                                                contentDescription = "transformation dragon",
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        Column(
                            verticalArrangement = Arrangement.spacedBy(space = 8.dp)
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth().clickable {
                                    isExpandedDescri = isExpandedDescri.not()
                                },
                                text = dragonModel.description,
                                color = Color.Black,
                                fontSize = 20.sp,
                                maxLines = if (isExpandedDescri) Int.MAX_VALUE else 3
                            )
                            val originPlanet =
                                rememberImagePainter(url = response.originPlanet.image,
                                    placeholderPainter = { painterResource(resource = Res.drawable.loading_black) },
                                    errorPainter = { painterResource(resource = Res.drawable.loading_black) })
                            Image(
                                modifier = Modifier.fillMaxWidth()
                                    .align(alignment = Alignment.CenterHorizontally),
                                painter = originPlanet,
                                contentDescription = "origin plant image"
                            )
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = response.originPlanet.name,
                                color = Color.Black,
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center
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
                                maxLines = if (isExpandedOrigin) Int.MAX_VALUE else 2
                            )

                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "Race: ",
                                    color = Color.Black,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = dragonModel.race, color = Color.Black, fontSize = 17.sp
                                )
                            }

                            Row(modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(5.dp).let {
                                    Arrangement.SpaceEvenly
                                }) {
                                Column(
                                    horizontalAlignment = horizontal
                                ) {
                                    Image(
                                        modifier = Modifier.size(size = 24.dp),
                                        painter = painterResource(resource = Res.drawable.fighter),
                                        contentDescription = "fighter of dragon"
                                    )
                                    Text(
                                        text = dragonModel.affiliation,
                                        color = Color.Black,
                                        fontSize = 15.sp
                                    )
                                }

                                Column(
                                    horizontalAlignment = horizontal
                                ) {
                                    Image(
                                        modifier = Modifier.size(size = 24.dp),
                                        painter = painterResource(
                                            resource = if (dragonModel.gender == "Male") Res.drawable.male
                                            else Res.drawable.female
                                        ),
                                        contentDescription = "gender of dragon"
                                    )
                                    Text(
                                        text = dragonModel.gender,
                                        color = Color.Black,
                                        fontSize = 15.sp
                                    )
                                }
                            }
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