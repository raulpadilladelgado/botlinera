package botlinera.infrastructure.schedulers

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

private const val INITIAL_DELAY = 0L
private const val PERIOD = 4L

class GasStationScheduler {
    fun start(job: () -> Unit) {
        val executorService = Executors.newSingleThreadScheduledExecutor()
        executorService.scheduleAtFixedRate(
            {
                job()
            }, INITIAL_DELAY, PERIOD, TimeUnit.HOURS
        )
    }
}
