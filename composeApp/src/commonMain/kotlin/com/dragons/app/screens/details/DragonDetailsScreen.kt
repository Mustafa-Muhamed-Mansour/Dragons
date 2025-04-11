import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.dragons.app.entity.DragonModel
import com.dragons.app.view_model.DragonDetailsViewModel
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

data class DragonDetailsScreen(
    val dragonModel: DragonModel
): Screen {

    @OptIn(KoinExperimentalAPI::class)
    @Composable
    override fun Content() {
        val viewModel = koinViewModel<DragonDetailsViewModel>()
        DragonDetails(viewModel = viewModel, dragonModel = dragonModel)
    }

}