package botlinera.infrastucture.adapters

import botlinera.domain.valueobject.GasType.GASOLINA_95_E5
import botlinera.domain.valueobject.MaximumCoordinates
import botlinera.infrastructure.dtos.GasStationDto
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoDatabase
import org.junit.Before
import org.junit.Test
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollectionOfName
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName
import kotlin.Double.Companion.NaN
import kotlin.test.assertEquals
import kotlin.test.assertTrue


class GastStationPersisterMongoShould {
    private val mongoDBContainer: MongoDBContainer = MongoDBContainer(DockerImageName.parse("mongo:4.0.10"))
        .withExposedPorts(27017)

    @Before
    fun setUp() {
        mongoDBContainer.start()
        val client: MongoClient = KMongo.createClient(mongoDBContainer.replicaSetUrl)
        val database: MongoDatabase = client.getDatabase("botlinera")
        val collection = database.getCollectionOfName<GasStationDto>("gas_stations")
        collection.insertMany(multipleGasStationsDtosWithinAFiveKilometersRadius())
    }

    @Test
    fun searchForNearestGasStationByMaximumCoordinates() {
        val maximumSouthCoordinate = "27.997816135794025".toDouble()
        val maximumNorthCoordinate = "28.087647664205974".toDouble()
        val maximumWestCoordinate = "-16.762560744378813".toDouble()
        val maximumEastCoordinate = "-16.66077985562119".toDouble()
        val coordinates = MaximumCoordinates(
            maximumSouthCoordinate,
            maximumNorthCoordinate,
            maximumWestCoordinate,
            maximumEastCoordinate
        )
        val gasStations = GastStationPersisterMongo(mongoDBContainer.replicaSetUrl).queryNearGasStations(
            coordinates,
            GASOLINA_95_E5
        )
        assertTrue(gasStations.size == 2)
    }

    @Test
    fun searchForNearestGasStationOrderedByGas95E5AscAndLimit3() {
        val maximumSouthCoordinate = "4.997816135794025".toDouble()
        val maximumNorthCoordinate = "50.087647664205974".toDouble()
        val maximumWestCoordinate = "-18.762560744378813".toDouble()
        val maximumEastCoordinate = "-1.56077985562119".toDouble()
        val coordinates = MaximumCoordinates(
            maximumSouthCoordinate,
            maximumNorthCoordinate,
            maximumWestCoordinate,
            maximumEastCoordinate
        )

        val gasStations =
            GastStationPersisterMongo(mongoDBContainer.replicaSetUrl)
                .queryNearGasStations(coordinates, GASOLINA_95_E5)

        assertEquals(gasStations[0].prices.gas95.e5, 1.138)
        assertEquals(gasStations[1].prices.gas95.e5, 1.238)
        assertEquals(gasStations[2].prices.gas95.e5, 1.538)
        assertEquals(3, gasStations.size)

    }

    private fun multipleGasStationsDtosWithinAFiveKilometersRadius(): List<GasStationDto> {
        return listOf(
            GasStationDto(
                "38660",
                "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                "L-D: 08:00-17:30",
                48.045632,
                "SANTA CRUZ DE TENERIFE",
                -16.737889,
                "Adeje",
                NaN,
                1.538,
                NaN,
                NaN,
                NaN,
                "GasStation1",
                "GasStation1",
                NaN,
                NaN,
                NaN
            ),
            GasStationDto(
                "38660",
                "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                "L-D: 08:00-17:30",
                28.069,
                "SANTA CRUZ DE TENERIFE",
                -20.7845454,
                "Adeje",
                NaN,
                1.648,
                NaN,
                NaN,
                NaN,
                "GasStation2",
                "GasStation2",
                NaN,
                NaN,
                NaN
            ),
            GasStationDto(
                "38660",
                "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                "L-D: 08:00-17:30",
                28.011861,
                "SANTA CRUZ DE TENERIFE",
                -16.662639,
                "Adeje",
                NaN,
                1.648,
                NaN,
                NaN,
                NaN,
                "GasStation3",
                "GasStation3",
                NaN,
                NaN,
                NaN
            ),
            GasStationDto(
                "38660",
                "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                "L-D: 08:00-17:30",
                28.053583,
                "SANTA CRUZ DE TENERIFE",
                -16.714611,
                "Adeje",
                NaN,
                1.238,
                NaN,
                NaN,
                NaN,
                "GasStation4",
                "GasStation4",
                NaN,
                NaN,
                NaN
            ),

            GasStationDto(
                "38660",
                "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                "L-D: 08:00-17:30",
                5.053583,
                "SANTA CRUZ DE TENERIFE",
                -16.714611,
                "Adeje",
                NaN,
                1.938,
                NaN,
                NaN,
                NaN,
                "GasStation5",
                "GasStation5",
                NaN,
                NaN,
                NaN
            ),

            GasStationDto(
                "38660",
                "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                "L-D: 08:00-17:30",
                28.069,
                "SANTA CRUZ DE TENERIFE",
                -1.714611,
                "Adeje",
                NaN,
                1.138,
                NaN,
                NaN,
                NaN,
                "GasStation6",
                "GasStation6",
                NaN,
                NaN,
                NaN
            )

        )
    }

}
