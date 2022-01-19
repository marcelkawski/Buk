package pl.android.buk.api;

import pl.android.buk.model.bookmaker.BukResponse;
import pl.android.buk.model.buks.BukerResponse;
import pl.android.buk.model.match.MatchResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RestInterface {

    @GET(Api.BASE_URL + "odds/120423?apikey=38964030-8bd5-11eb-84ae-7967a19e509c&type=prematch")
    Call<BukResponse> getOdds();

    @GET(Api.BASE_URL + "matches?apikey=38964030-8bd5-11eb-84ae-7967a19e509c&season_id=1980")
    Call<MatchResponse> getMatches(@Query("startDate") String startDate, @Query("endDate") String endDate);

    @GET(Api.BASE_URL + "bookmakers?apikey=38964030-8bd5-11eb-84ae-7967a19e509c")
    Call<BukerResponse> getBookmakers();

    @GET("odds/{idMatch}?apikey=38964030-8bd5-11eb-84ae-7967a19e509c&type=prematch")
    Call<BukResponse> getMatchOdds(@Path("idMatch") int idMatch);

}
