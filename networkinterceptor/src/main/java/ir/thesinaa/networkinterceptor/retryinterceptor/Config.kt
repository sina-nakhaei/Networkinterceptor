package ir.thesinaa.networkinterceptor.retryinterceptor

import androidx.annotation.IntRange
import ir.thesinaa.networkinterceptor.StatusCode

data class Config(
    @IntRange(1, Long.MAX_VALUE) val maxRetryAttempts: Int,
    val delayMillis: Long,
    val exclusions: List<StatusCode>
)