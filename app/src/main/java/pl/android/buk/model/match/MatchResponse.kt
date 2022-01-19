package pl.android.buk.model.match

import com.google.gson.annotations.SerializedName

data class MatchResponse(
    @SerializedName("query")
    var query: MatchQuery,
    @SerializedName("data")
    var data: List<MatchDatum>
)
