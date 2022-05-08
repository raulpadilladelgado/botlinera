package botlinera.infrastructure.dtos

import com.google.gson.annotations.SerializedName

data class GasStationDto(
    @SerializedName("C.P.")                          var postalCode: String,
    @SerializedName("Dirección")                     var address: String,
    @SerializedName("Horario")                       var time: String,
    @SerializedName("Latitud")                       var latitude: Double,
    @SerializedName("Localidad")                     var locality: String,
    @SerializedName("Longitud (WGS84)")              var longitude: Double,
    @SerializedName("Municipio")                     var municipality: String,
    @SerializedName("Precio Gasolina 95 E10")        var gas95E10Price: Double,
    @SerializedName("Precio Gasolina 95 E5")         var gas95E5Price: Double,
    @SerializedName("Precio Gasolina 95 E5 Premium") var gas95E5PremiumPrice: Double,
    @SerializedName("Precio Gasolina 98 E10")        var gas98E10Price: Double,
    @SerializedName("Precio Gasolina 98 E5")         var gas98E5Price: Double,
    @SerializedName("Provincia")                     var province: String,
    @SerializedName("Rótulo")                        var name: String,
    @SerializedName("Precio Gasoleo A")              var gasoilA: Double,
    @SerializedName("Precio Gasoleo B")              var gasoilB: Double,
    @SerializedName("Precio Gasoleo Premium")        var gasoilPremium: Double
) {
}
