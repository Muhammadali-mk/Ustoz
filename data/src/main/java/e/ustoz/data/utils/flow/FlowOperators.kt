@file:Suppress("EXPERIMENTAL_API_USAGE")
package e.ustoz.data.utils.flow

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.TimeUnit

fun flowInterval(
    currentPeriod: Long = 0,
    period: Long, delay: Long, unit: TimeUnit = TimeUnit.MILLISECONDS
): Flow<Long> {
    val tickerChannel: ReceiveChannel<Unit> =
        ticker(delayMillis = unit.toMillis(delay), initialDelayMillis = 0)
    var value: Long = currentPeriod
    return flow {
        for (event: Unit in tickerChannel) {
            value += delay
            if (value == period) tickerChannel.cancel()
            emit(value)
        }
    }
}