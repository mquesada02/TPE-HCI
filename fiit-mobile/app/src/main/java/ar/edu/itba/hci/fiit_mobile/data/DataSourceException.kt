package ar.edu.itba.hci.fiit_mobile.data

class DataSourceException(
    var code: Int?,
    message: String,
    var details: List<String>?
) : Exception(message)