package pl.android.buk.model.bookmaker

import com.google.gson.annotations.SerializedName

data class Bookmaker(
    @SerializedName("bookmaker_id")
    var bookmakerId: Int,
    @SerializedName("bookmaker_name")
    var bookmakerName: String,
    @SerializedName("odds_data")
    var oddsData: OddsData,
    @SerializedName("last_updated")
    var lastUpdated: String
)
