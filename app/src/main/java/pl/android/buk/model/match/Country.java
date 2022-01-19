package pl.android.buk.model.match;

import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("country_id")
    Integer countryId;
    @SerializedName("name")
    String name;
    @SerializedName("country_code")
    String countryCode;
    @SerializedName("continent")
    String continent;
}