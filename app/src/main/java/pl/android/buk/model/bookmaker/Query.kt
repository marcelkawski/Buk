package pl.android.buk.model.bookmaker

import com.google.gson.annotations.SerializedName

data class Query(
    @SerializedName("apikey")
    var apikey: String,
    @SerializedName("country_id")
    var countryId: String
)
