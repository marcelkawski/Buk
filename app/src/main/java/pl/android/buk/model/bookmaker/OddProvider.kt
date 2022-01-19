package pl.android.buk.model.bookmaker

import com.google.gson.annotations.SerializedName

data class OddProvider(
    @SerializedName("1X2, Full Time Result")
    var fullTimeResult: Datum,
    @SerializedName("Asian Handicap")
    var asianHandicap: Datum,
    @SerializedName("Over/Under, Goal Line")
    var overUnderGoalLine: Datum
)
