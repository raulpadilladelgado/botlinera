package botlinera.infrastructure.schedulers

import botlinera.application.usecases.UpdateGasStations
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import java.lang.Thread.sleep
import java.util.concurrent.TimeUnit.MINUTES
import java.util.concurrent.TimeUnit.SECONDS

class GasStationSchedulerShould {
    @Test
    fun `execute job at start`() {
        val fakeUpdateGasStations = mockk<UpdateGasStations>(relaxed = true)

        GasStationScheduler.start({ fakeUpdateGasStations.execute() }, 0, 30, MINUTES)

        verify(exactly = 1) { fakeUpdateGasStations.execute() }
    }

    @Test
    fun `execute job at every execution interval`() {
        val fakeUpdateGasStations = mockk<UpdateGasStations>(relaxed = true)

        GasStationScheduler.start({ fakeUpdateGasStations.execute() }, 0, 4, SECONDS)

        sleep(6000)
        verify(exactly = 2) { fakeUpdateGasStations.execute() }
    }

    @Test
    fun `do not stop the scheduler if something fails at the job`() {
        val fakeUpdateGasStations = mockk<UpdateGasStations>(relaxed = true)
        every { fakeUpdateGasStations.execute() } throws Exception()

        GasStationScheduler.start({ fakeUpdateGasStations.execute() }, 0, 4, SECONDS)

        sleep(6000)
        verify(exactly = 2) { fakeUpdateGasStations.execute() }
    }
}
