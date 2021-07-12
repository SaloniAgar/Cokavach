package com.risingstar.cokavach.models

data class ModelStat(
        var country : String,
        var countryCode : String,
        var newConfirmed : String,
        var totalConfirmed : String,
        var newDeaths : String,
        var totalDeaths : String,
        var newRecovered : String,
        var totalRecovered : String
)