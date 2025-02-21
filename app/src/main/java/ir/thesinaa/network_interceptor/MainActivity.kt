package ir.thesinaa.network_interceptor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import ir.thesinaa.network_interceptor.ui.theme.NetworkinterceptorTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NetworkinterceptorTheme {
                Surface(
                    modifier = Modifier.padding(100.dp)
                ) {
                    val viewModel: SampleViewModel = hiltViewModel()

                    Button({
                        //                        viewModel.getSlideShow()
//                        viewModel.getStatusCode()
                        viewModel.createUser()
                    }) { }

                }
            }
        }
    }
}