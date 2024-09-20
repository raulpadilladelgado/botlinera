package botlinera.infrastructure.schedulers

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class GasStationScheduler {
    companion object {
        private val LOG: Logger = LoggerFactory.getLogger(GasStationScheduler::class.java)
        fun start(job: () -> Unit, initialDelay: Long, executionInterval: Long, timeUnit: TimeUnit) {
            val executorService = Executors.newSingleThreadScheduledExecutor()
            executorService.scheduleAtFixedRate(
                {
                    try {
                        job()
                    } catch (exception: Exception) {
                        LOG.error("Error occurred while scheduling job", exception)
                    }
                }, initialDelay, executionInterval, timeUnit
            )
        }
    }

}
