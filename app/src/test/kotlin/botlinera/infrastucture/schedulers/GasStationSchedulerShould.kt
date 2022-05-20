package botlinera.infrastucture.schedulers

import botlinera.application.usecases.UpdateGasStations
import botlinera.infrastructure.schedulers.GasStationScheduler
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class GasStationSchedulerShould {
    @Test
    fun updateGasStationsAtStartup() {
        val fakeUpdateGasStations = mockk<UpdateGasStations>()

        GasStationScheduler().start { fakeUpdateGasStations.execute() }

        verify(exactly = 1) { fakeUpdateGasStations.execute() }
    }
}
