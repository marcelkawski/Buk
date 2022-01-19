package pl.android.buk.model.match;

import com.google.gson.annotations.SerializedName;

public class MatchDatum {
    @SerializedName("match_id")
    Integer matchId;
    @SerializedName("status_code")
    Integer statusCode;
    @SerializedName("status")
    String status;
    @SerializedName("match_start")
    String matchStart;
    @SerializedName("match_start_iso")
    String matchStartIso;
    @SerializedName("minute")
    Object minute;
    @SerializedName("league_id")
    Integer leagueId;
    @SerializedName("season_id")
    Integer seasonId;
    @SerializedName("stage")
    Object stage;
    @SerializedName("group")
    Object group;
    @SerializedName("round")
    Object round;
    @SerializedName("referee_id")
    Integer refereeId;
    @SerializedName("home_team")
    Team homeTeam;
    @SerializedName("away_team")
    Team awayTeam;
    @SerializedName("stats")
    Object stats;
    @SerializedName("venue")
    Object venue;

    public Integer getMatchId() {
        return matchId;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getStatus() {
        return status;
    }

    public String getMatchStart() {
        return matchStart;
    }

    public String getMatchStartIso() {
        return matchStartIso;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public Integer getSeasonId() {
        return seasonId;
    }

    public Integer getRefereeId() {
        return refereeId;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }
}
