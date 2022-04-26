package botlinera.infrastructure.dtos

import com.google.gson.annotations.SerializedName

data class GasStation(
    @SerializedName("C.P.")                          var postalCode: String,
    @SerializedName("Dirección")                     var address: String,
    @SerializedName("Horario")                       var time: String,
    @SerializedName("Latitud")                       var latitude: String,
    @SerializedName("Localidad")                     var locality: String,
    @SerializedName("Longitud (WGS84)")              var length: String,
    @SerializedName("Municipio")                     var municipality: String,
    @SerializedName("Precio Gasolina 95 E10")        var gas95E10Price: String,
    @SerializedName("Precio Gasolina 95 E5")         var gas95E5Price: String,
    @SerializedName("Precio Gasolina 95 E5 Premium") var gas95E5PremiumPrice: String,
    @SerializedName("Precio Gasolina 98 E10")        var gas98E10Price: String,
    @SerializedName("Precio Gasolina 98 E5")         var gas98E5Price: String,
    @SerializedName("Provincia")                     var province: String,
    @SerializedName("Rótulo")                        var name: String
)
