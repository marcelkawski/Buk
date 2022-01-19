package pl.android.buk.model.match;

import com.google.gson.annotations.SerializedName;

public class Team {
    @SerializedName("team_id")
    Integer teamId;
    @SerializedName("name")
    String name;
    @SerializedName("short_code")
    String shortCode;
    @SerializedName("common_name")
    String commonName;
    @SerializedName("logo")
    String logo;
    @SerializedName("country")
    Country country;

    public Integer getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getLogo() {
        return logo;
    }

    public Country getCountry() {
        return country;
    }
}
