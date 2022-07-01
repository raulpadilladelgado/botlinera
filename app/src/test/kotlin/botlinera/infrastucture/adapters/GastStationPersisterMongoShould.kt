package botlinera.infrastucture.adapters

import botlinera.domain.valueobject.GasType.*
import botlinera.domain.valueobject.MaximumCoordinates
import botlinera.infrastructure.adapters.GastStationPersisterMongo
import botlinera.infrastructure.dtos.GasStationDto
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.litote.kmongo.KMongo
import org.litote.kmongo.getCollectionOfName
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName
import kotlin.Double.Companion.NaN


class GastStationPersisterMongoShould() {
    private val mongoDBContainer: MongoDBContainer = MongoDBContainer(DockerImageName.parse("mongo:4.0.10"))
        .withExposedPorts(27017)
    private lateinit var client: MongoClient
    private lateinit var database: MongoDatabase
    private lateinit var collection: MongoCollection<GasStationDto>


    @BeforeEach
    fun setUp() {
        mongoDBContainer.start()
        client = KMongo.createClient(mongoDBContainer.replicaSetUrl)
        database = client.getDatabase("botlinera")
        collection = database.getCollectionOfName("gas_stations")
        collection.insertMany(multipleGasStationsDtosWithinAFiveKilometersRadius())
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
        assertEquals(3, gasStations.size)
        assertEquals("GasStation6", gasStations[0].name)
        assertEquals("GasStation4", gasStations[1].name)
        assertEquals("GasStation1", gasStations[2].name)

    }

    @Test
    fun `Search for nearest gas station ordered by gas95E10 ascendant and limited to 3`() {
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
                .queryNearGasStations(coordinates, GASOLINA_95_E10)
        assertEquals(3, gasStations.size)
        assertEquals("GasStation4", gasStations[0].name)
        assertEquals("GasStation6", gasStations[1].name)
        assertEquals("GasStation1", gasStations[2].name)
    }

    @Test
    fun `Search for nearest gas station ordered by gas95Premium ascendant and limited to 3`() {
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
                .queryNearGasStations(coordinates, GASOLINA_95_E5_PREMIUM)
        assertEquals(3, gasStations.size)
        assertEquals("GasStation1", gasStations[0].name)
        assertEquals("GasStation4", gasStations[1].name)
        assertEquals("GasStation6", gasStations[2].name)
    }

    @Test
    fun `Search for nearest gas station ordered by gas98E5 ascendant and limited to 3`() {
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
                .queryNearGasStations(coordinates, GASOLINA_98_E5)
        assertEquals(3, gasStations.size)
        assertEquals("GasStation1", gasStations[0].name)
        assertEquals("GasStation6", gasStations[1].name)
        assertEquals("GasStation4", gasStations[2].name)
    }

    @Test
    fun `Search for nearest gas station ordered by gas98E10 ascendant and limited to 3`() {
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
                .queryNearGasStations(coordinates, GASOLINA_98_E10)
        assertEquals(3, gasStations.size)
        assertEquals("GasStation4", gasStations[0].name)
        assertEquals("GasStation1", gasStations[1].name)
        assertEquals("GasStation6", gasStations[2].name)
    }

    @Test
    fun `Search for nearest gas station ordered by gasoilA ascendant and limited to 3`() {
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
                .queryNearGasStations(coordinates, GASOIL_A)
        assertEquals(3, gasStations.size)
        assertEquals("GasStation6", gasStations[0].name)
        assertEquals("GasStation1", gasStations[1].name)
        assertEquals("GasStation4", gasStations[2].name)
    }

    @Test
    fun `Search for nearest gas station ordered by gasoilB ascendant and limited to 3`() {
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
                .queryNearGasStations(coordinates, GASOIL_B)
        assertEquals(2, gasStations.size)
        assertEquals("GasStation1", gasStations[0].name)
        assertEquals("GasStation6", gasStations[1].name)
    }

    @Test
    fun `Search for nearest gas station ordered by gasoilPremium ascendant and limited to 3`() {
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
                .queryNearGasStations(coordinates, GASOIL_PREMIUM)
        assertEquals(2, gasStations.size)
        assertEquals("GasStation6", gasStations[0].name)
        assertEquals("GasStation1", gasStations[1].name)
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
                1.30,
                1.538,
                1.10,
                1.20,
                1.10,
                "GasStation1",
                "GasStation1",
                1.20,
                1.10,
                1.20
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
                1.10,
                1.238,
                1.20,
                1.10,
                1.30,
                "GasStation4",
                "GasStation4",
                1.30,
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
                1.20,
                1.138,
                1.30,
                1.30,
                1.20,
                "GasStation6",
                "GasStation6",
                1.10,
                1.20,
                1.10
            )

        )
    }

}
