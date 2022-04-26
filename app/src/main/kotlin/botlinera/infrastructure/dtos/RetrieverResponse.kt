package botlinera.infrastructure.dtos

import com.google.gson.annotations.SerializedName

data class RetrieverResponse(
    @SerializedName("Fecha"             ) var date     : String?               = null,
    @SerializedName("ListaEESSPrecio"   ) var prices   : ArrayList<GasStation> = arrayListOf(),
    @SerializedName("Nota"              ) var note     : String?               = null,
    @SerializedName("ResultadoConsulta" ) var result   : String?               = null
)
