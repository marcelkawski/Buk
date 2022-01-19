package pl.android.buk.model.buks

import com.google.gson.annotations.SerializedName
import pl.android.buk.model.bookmaker.Query

data class BukerResponse(
    @SerializedName("query")
    var query: Query,
    @SerializedName("data")
    var data: List<BukDatum>
)
