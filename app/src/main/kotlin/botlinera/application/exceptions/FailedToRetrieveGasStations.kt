package botlinera.application.exceptions

class FailedToRetrieveGasStations(cause: Throwable) :
    RuntimeException(
        "Failed to retrieve gas stations from the URL source",
        cause
    )
