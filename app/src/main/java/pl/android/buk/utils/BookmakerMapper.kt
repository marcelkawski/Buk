package pl.android.buk.utils

import pl.android.buk.model.MappedBookmakersTypes
import pl.android.buk.model.bookmaker.Bookmaker
import pl.android.buk.model.bookmaker.BookmakerLogoTitle
import pl.android.buk.model.buks.BukDatum
import java.math.BigDecimal
import java.math.RoundingMode

object BookmakerMapper {

    fun mapBookmakersTypes(
        bookmakers: List<Bookmaker>,
        buks: ArrayList<BukDatum>,
        bookmakerMap: HashMap<String, Double>
    ): List<MappedBookmakersTypes> {
        val mappedItems = ArrayList<MappedBookmakersTypes>()
        val bookmakersList = ArrayList<BookmakerLogoTitle>()
        val list1 = bookmakers.sortedBy { it.bookmakerName }
        val list2 = buks.sortedBy { it.name }
        list1.forEach { bookmaker ->
            list2.forEach { buk ->
                if (bookmaker.bookmakerName == buk.name) {
                    bookmakersList.add(BookmakerLogoTitle(bookmaker.bookmakerName, buk.img))
                }
            }
        }

        bookmakersList.forEach { bookmaker ->
            bookmakerMap.entries.forEach { mapItem ->
                if (bookmaker.name == mapItem.key) {
                    mappedItems.add(
                        MappedBookmakersTypes(
                            bookmakerName = bookmaker.name,
                            logo = bookmaker.logo,
                            amount = BigDecimal(mapItem.value).setScale(
                                2,
                                RoundingMode.HALF_EVEN
                            ),
                        )
                    )
                }
            }
        }
        return mappedItems
    }
}