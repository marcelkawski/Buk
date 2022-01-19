package pl.android.buk.model.match;

import com.google.gson.annotations.SerializedName;

public class MatchQuery {
    @SerializedName("apikey")
    String apikey;
    @SerializedName("season_id")
    String seasonId;
    @SerializedName("date_from")
    String dateFrom;
    @SerializedName("date_to")
    String dateTo;
}
