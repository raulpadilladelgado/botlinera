package botlinera.infrastucture.adapters

import botlinera.domain.valueobject.Coordinates
import botlinera.domain.valueobject.GasStation
import org.junit.Before
import org.junit.Test
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName
import kotlin.test.assertEquals


class GastStationPersisterMongoShould {
    val mongoDBContainer =  MongoDBContainer(DockerImageName.parse("mongo:4.0.10"))
        .withExposedPorts(27017)

    @Before
    fun setUp() {
        mongoDBContainer.start()
    }

    @Test
    fun aguacate() {
        val latitude = "28.0427319".toDouble()
        val longitude = "-16.7116703".toDouble()
        val coordinates = Coordinates(latitude, longitude)

        val gasStations = GastStationPersisterMongo(mongoDBContainer.replicaSetUrl).queryNearGasStations(coordinates)

        val expectedGasStations = listOf<GasStation>()
        assertEquals(expectedGasStations, gasStations)
    }
}
