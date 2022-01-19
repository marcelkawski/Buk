package pl.android.buk.model.bookmaker

import com.google.gson.annotations.SerializedName

data class BukResponse(
    @SerializedName("query")
    var query: Query,
    @SerializedName("data")
    var data: OddProvider
)
