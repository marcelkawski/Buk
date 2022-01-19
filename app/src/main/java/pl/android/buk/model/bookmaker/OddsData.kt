package pl.android.buk.model.bookmaker

import com.google.gson.annotations.SerializedName

data class OddsData(
    @SerializedName("home")
    var home: String?,
    @SerializedName("draw")
    var draw: String?,
    @SerializedName("away")
    var away: String?,
    @SerializedName("handicap")
    var handicap: String?,
    @SerializedName("score")
    var score: String? = null,
    @SerializedName("minute")
    var minute: String? = null,
    @SerializedName("over")
    var over: String?,
    @SerializedName("under")
    var under: String?,

//    var winHome: Float,
//    var drawMatch: Float,
//    var winAway: Float,
)
