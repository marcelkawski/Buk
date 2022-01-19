package pl.android.buk.model

import java.math.BigDecimal

data class MappedBookmakersTypes(
    var bookmakerName: String,
    var logo: String,
    var amount: BigDecimal,
)
