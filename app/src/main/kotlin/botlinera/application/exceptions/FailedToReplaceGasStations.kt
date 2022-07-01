package botlinera.application.exceptions

class FailedToReplaceGasStations(cause: Throwable) :
    RuntimeException(
        "Failed to replace gas stations in database",
        cause
    ) {

}
