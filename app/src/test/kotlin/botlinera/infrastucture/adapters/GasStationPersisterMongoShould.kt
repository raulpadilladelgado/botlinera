package botlinera.infrastucture.adapters

import botlinera.domain.valueobject.GasType.*
import botlinera.domain.valueobject.MaximumCoordinates
import botlinera.infrastructure.adapters.GasStationPersisterMongo
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.testcontainers.containers.MongoDBContainer
import org.testcontainers.utility.DockerImageName


class GasStationPersisterMongoShould() {
    private val mongoDBContainer: MongoDBContainer = MongoDBContainer(DockerImageName.parse("mongo:4.0.10"))
        .withExposedPorts(27017)

    private lateinit var client: MongoClient
    private lateinit var database: MongoDatabase
    private lateinit var collection: MongoCollection<Document>


    @BeforeEach
    fun setUp() {
        mongoDBContainer.start()
        client = MongoClients.create(mongoDBContainer.replicaSetUrl)
        database = client.getDatabase("botlinera")
        collection = database.getCollection("gas_stations")
        collection.insertMany(
            listOf(
                Document("postalCode", "38660")
                    .append("address", "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS")
                    .append("time", "L-D: 08:00-17:30")
                    .append("latitude", 48.045632)
                    .append("locality", "SANTA CRUZ DE TENERIFE")
                    .append("longitude", -16.737889)
                    .append("municipality", "Adeje")
                    .append("gas95E10Price", 1.30)
                    .append("gas95E5Price", 1.538)
                    .append("gas95E5PremiumPrice", 1.10)
                    .append("gas98E10Price", 1.20)
                    .append("gas98E5Price", 1.10)
                    .append("province", "GasStation1")
                    .append("name", "GasStation1")
                    .append("gasoilA", 1.20)
                    .append("gasoilB", 1.10)
                    .append("gasoilPremium", 1.20),
                Document("postalCode", "38660")
                    .append("address", "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS")
                    .append("time", "L-D: 08:00-17:30")
                    .append("latitude", 28.069,)
                    .append("locality", "SANTA CRUZ DE TENERIFE")
                    .append("longitude", -20.7845454,)
                    .append("municipality", "Adeje")
                    .append("gas95E10Price", Double.NaN,)
                    .append("gas95E5Price", 1.648,)
                    .append("gas95E5PremiumPrice", Double.NaN,)
                    .append("gas98E10Price", Double.NaN,)
                    .append("gas98E5Price", Double.NaN,)
                    .append("province", "GasStation2")
                    .append("name", "GasStation2")
                    .append("gasoilA", Double.NaN,)
                    .append("gasoilB", Double.NaN,)
                    .append("gasoilPremium", Double.NaN),
                Document("postalCode", "38660")
                    .append("address", "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS")
                    .append("time", "L-D: 08:00-17:30")
                    .append("latitude", 28.011861,)
                    .append("locality", "SANTA CRUZ DE TENERIFE")
                    .append("longitude", -16.662639,)
                    .append("municipality", "Adeje")
                    .append("gas95E10Price", Double.NaN,)
                    .append("gas95E5Price", 1.648,)
                    .append("gas95E5PremiumPrice", Double.NaN,)
                    .append("gas98E10Price", Double.NaN,)
                    .append("gas98E5Price", Double.NaN,)
                    .append("province", "GasStation3")
                    .append("name", "GasStation3")
                    .append("gasoilA", Double.NaN,)
                    .append("gasoilB", Double.NaN,)
                    .append("gasoilPremium", Double.NaN),
                Document("postalCode", "38660")
                    .append("address", "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS")
                    .append("time", "L-D: 08:00-17:30")
                    .append("latitude", 28.053583,)
                    .append("locality", "SANTA CRUZ DE TENERIFE")
                    .append("longitude", -16.714611,)
                    .append("municipality", "Adeje")
                    .append("gas95E10Price", 1.10,)
                    .append("gas95E5Price", 1.238,)
                    .append("gas95E5PremiumPrice", 1.20,)
                    .append("gas98E10Price", 1.10,)
                    .append("gas98E5Price", 1.30,)
                    .append("province", "GasStation4")
                    .append("name", "GasStation4")
                    .append("gasoilA", 1.30,)
                    .append("gasoilB", Double.NaN,)
                    .append("gasoilPremium", Double.NaN),
                Document("postalCode", "38660")
                    .append("address", "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS")
                    .append("time", "L-D: 08:00-17:30")
                    .append("latitude", 5.053583,)
                    .append("locality", "SANTA CRUZ DE TENERIFE")
                    .append("longitude", -16.714611,)
                    .append("municipality", "Adeje")
                    .append("gas95E10Price", Double.NaN,)
                    .append("gas95E5Price", 1.938,)
                    .append("gas95E5PremiumPrice", Double.NaN,)
                    .append("gas98E10Price", Double.NaN,)
                    .append("gas98E5Price", Double.NaN,)
                    .append("province", "GasStation5")
                    .append("name", "GasStation5")
                    .append("gasoilA", Double.NaN,)
                    .append("gasoilB", Double.NaN,)
                    .append("gasoilPremium", Double.NaN),
                Document("postalCode", "38660")
                    .append("address", "URBANIZACIÓN SAN EUGENIO, PLAYA DE LAS AMERICAS")
                    .append("time", "L-D: 08:00-17:30")
                    .append("latitude", 28.069,)
                    .append("locality", "SANTA CRUZ DE TENERIFE")
                    .append("longitude", -1.714611,)
                    .append("municipality", "Adeje")
                    .append("gas95E10Price", 1.20,)
                    .append("gas95E5Price", 1.138,)
                    .append("gas95E5PremiumPrice", 1.30,)
                    .append("gas98E10Price", 1.30,)
                    .append("gas98E5Price", 1.20,)
                    .append("province", "GasStation6")
                    .append("name", "GasStation6")
                    .append("gasoilA", 1.10,)
                    .append("gasoilB", 1.20,)
                    .append("gasoilPremium", 1.10)
            )
        )
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
            GasStationPersisterMongo(mongoDBContainer.replicaSetUrl)
                .queryNearGasStations(coordinates, GASOLINA_95_E5)
                .getOrThrow()
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
            GasStationPersisterMongo(mongoDBContainer.replicaSetUrl)
                .queryNearGasStations(coordinates, GASOLINA_95_E10)
                .getOrThrow()
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
            GasStationPersisterMongo(mongoDBContainer.replicaSetUrl)
                .queryNearGasStations(coordinates, GASOLINA_95_E5_PREMIUM)
                .getOrThrow()
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
            GasStationPersisterMongo(mongoDBContainer.replicaSetUrl)
                .queryNearGasStations(coordinates, GASOLINA_98_E5)
                .getOrThrow()
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
            GasStationPersisterMongo(mongoDBContainer.replicaSetUrl)
                .queryNearGasStations(coordinates, GASOLINA_98_E10)
                .getOrThrow()
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
            GasStationPersisterMongo(mongoDBContainer.replicaSetUrl)
                .queryNearGasStations(coordinates, GASOIL_A)
                .getOrThrow()
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
            GasStationPersisterMongo(mongoDBContainer.replicaSetUrl)
                .queryNearGasStations(coordinates, GASOIL_B)
                .getOrThrow()
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
            GasStationPersisterMongo(mongoDBContainer.replicaSetUrl)
                .queryNearGasStations(coordinates, GASOIL_PREMIUM)
                .getOrThrow()
        assertEquals(2, gasStations.size)
        assertEquals("GasStation6", gasStations[0].name)
        assertEquals("GasStation1", gasStations[1].name)
    }

}
