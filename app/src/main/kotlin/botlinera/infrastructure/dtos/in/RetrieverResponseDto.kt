package botlinera.infrastructure.dtos.`in`

import com.google.gson.annotations.SerializedName

data class RetrieverResponseDto(
    @SerializedName("Fecha") var date: String? = null,
    @SerializedName("ListaEESSPrecio") var prices: ArrayList<GasStationDto> = arrayListOf(),
    @SerializedName("Nota") var note: String? = null,
    @SerializedName("ResultadoConsulta") var result: String? = null
)