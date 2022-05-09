package botlinera.infrastucture.adapters

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
    fun aguacate() {
        val maximumSouthCoordinate = "27.997629".toDouble()
        val maximumNorthCoordinate = "28.08784".toDouble()
        val maximumWestCoordinate = "-16.76256".toDouble()
        val maximumEastCoordinate = "-16.66078".toDouble()
        val coordinates = MaximumCoordinates(
            maximumSouthCoordinate,
            maximumNorthCoordinate,
            maximumWestCoordinate,
            maximumEastCoordinate
        )
        val gasStations = GastStationPersisterMongo(mongoDBContainer.replicaSetUrl).queryNearGasStations(coordinates)
        assertTrue(gasStations.size == 2)
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
                Double.NaN,
                1.538,
                Double.NaN,
                Double.NaN,
                Double.NaN,
                "GasStation1",
                "GasStation1",
                Double.NaN,
                Double.NaN,
                Double.NaN
            ),
            GasStationDto(
                "38660",
                "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                "L-D: 08:00-17:30",
                28.069,
                "SANTA CRUZ DE TENERIFE",
                -20.7845454,
                "Adeje",
                Double.NaN,
                1.538,
                Double.NaN,
                Double.NaN,
                Double.NaN,
                "GasStation2",
                "GasStation2",
                Double.NaN,
                Double.NaN,
                Double.NaN
            ),
            GasStationDto(
                "38660",
                "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                "L-D: 08:00-17:30",
                28.011861,
                "SANTA CRUZ DE TENERIFE",
                -16.662639,
                "Adeje",
                Double.NaN,
                1.538,
                Double.NaN,
                Double.NaN,
                Double.NaN,
                "GasStation3",
                "GasStation3",
                Double.NaN,
                Double.NaN,
                Double.NaN
            ),
            GasStationDto(
                "38660",
                "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                "L-D: 08:00-17:30",
                28.053583,
                "SANTA CRUZ DE TENERIFE",
                -16.714611,
                "Adeje",
                Double.NaN,
                1.538,
                Double.NaN,
                Double.NaN,
                Double.NaN,
                "GasStation4",
                "GasStation4",
                Double.NaN,
                Double.NaN,
                Double.NaN
            ),

            GasStationDto(
                "38660",
                "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                "L-D: 08:00-17:30",
                5.053583,
                "SANTA CRUZ DE TENERIFE",
                -16.714611,
                "Adeje",
                Double.NaN,
                1.538,
                Double.NaN,
                Double.NaN,
                Double.NaN,
                "GasStation5",
                "GasStation5",
                Double.NaN,
                Double.NaN,
                Double.NaN
            ),

            GasStationDto(
                "38660",
                "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS",
                "L-D: 08:00-17:30",
                28.069,
                "SANTA CRUZ DE TENERIFE",
                -1.714611,
                "Adeje",
                Double.NaN,
                1.538,
                Double.NaN,
                Double.NaN,
                Double.NaN,
                "GasStation6",
                "GasStation6",
                Double.NaN,
                Double.NaN,
                Double.NaN
            )

        )
    }

}
