package ir.thesinaa.network_interceptor

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.thesinaa.network_interceptor.repository.SampleRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SampleViewModel @Inject constructor(
    private val repo: SampleRepository
) : ViewModel() {
    fun getSlideShow() {
        viewModelScope.launch {
            repo.getSlideShow().also { Log.d(TAG, "slide-show response: $it") }
        }
    }
}