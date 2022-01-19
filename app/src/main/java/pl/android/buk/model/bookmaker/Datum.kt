package pl.android.buk.model.bookmaker

import com.google.gson.annotations.SerializedName

data class Datum(
    @SerializedName("bookmakers")
    var bookmakers: List<Bookmaker>
)
