package pl.android.buk.model.match


import pl.android.buk.model.TYPE
import pl.android.buk.model.bookmaker.Bookmaker

data class MatchForm(
    var matchId: Int,
    var matchBookmakers: List<Bookmaker>,
    var matchType: TYPE,
    var datum: MatchDatum,
)

