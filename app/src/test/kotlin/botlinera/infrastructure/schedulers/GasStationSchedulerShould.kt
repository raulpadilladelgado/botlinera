package botlinera.infrastructure.schedulers

import botlinera.application.usecases.UpdateGasStations
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class GasStationSchedulerShould {
    @Test
    fun updateGasStationsAtStartup() {
        val fakeUpdateGasStations = mockk<UpdateGasStations>(relaxed = true)

        GasStationScheduler().start { fakeUpdateGasStations.execute() }

        verify(exactly = 1) { fakeUpdateGasStations.execute() }
    }
}
