package pl.android.buk.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.android.buk.R
import pl.android.buk.activities.MatchChoseActivity.Companion.CHOSEN_MATCHES
import pl.android.buk.api.Rest
import pl.android.buk.model.MappedBookmakersTypes
import pl.android.buk.model.TYPE
import pl.android.buk.model.bookmaker.BukResponse
import pl.android.buk.model.buks.BukDatum
import pl.android.buk.model.buks.BukerResponse
import pl.android.buk.model.match.MatchDatum
import pl.android.buk.utils.BookmakerMapper
import pl.android.buk.utils.BookmakerTypesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookmakersTypesActivity : AppCompatActivity() {

    private var recyclerList: RecyclerView? = null
    private lateinit var bookmakersAdapter: BookmakerTypesAdapter
    private var bookmakers: List<MappedBookmakersTypes> = ArrayList()
    private var buks: ArrayList<BukDatum> = ArrayList()
    private val bookmakerMatch1Map = hashMapOf<String, Double>()
    private val bookmakerMatch2Map = hashMapOf<String, Double>()
    private val bookmakerMatch3Map = hashMapOf<String, Double>()
    private var bookmakerMatchResultsMap = hashMapOf<String, Double>()


    private fun findViews() {
        recyclerList = findViewById(R.id.list_compare)
        recyclerList?.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookmakers_types)
        findViews()

        fetchValuesData()
    }

    private fun fetchValuesData() {
        val match1 = CHOSEN_MATCHES[0]
        when (match1.matchType) {
            TYPE.WIN -> {
                repeat(match1.matchBookmakers.size) {
                    bookmakerMatch1Map[match1.matchBookmakers[it].bookmakerName] =
                        match1.matchBookmakers[it].oddsData.home!!.toDouble()
                }
            }
            TYPE.DRAW -> {
                repeat(match1.matchBookmakers.size) {
                    bookmakerMatch1Map[match1.matchBookmakers[it].bookmakerName] =
                        match1.matchBookmakers[it].oddsData.draw!!.toDouble()
                }
            }
            else -> {
                repeat(match1.matchBookmakers.size) {
                    bookmakerMatch1Map[match1.matchBookmakers[it].bookmakerName] =
                        match1.matchBookmakers[it].oddsData.away!!.toDouble()
                }
            }
        }

        if (CHOSEN_MATCHES.size >= 2) {
            val match2 = CHOSEN_MATCHES[1]
            when (match2.matchType) {
                TYPE.WIN -> {
                    repeat(match2.matchBookmakers.size) {
                        bookmakerMatch2Map[match2.matchBookmakers[it].bookmakerName] =
                            match2.matchBookmakers[it].oddsData.home!!.toDouble()
                    }
                }
                TYPE.DRAW -> {
                    repeat(match2.matchBookmakers.size) {
                        bookmakerMatch2Map[match2.matchBookmakers[it].bookmakerName] =
                            match2.matchBookmakers[it].oddsData.draw!!.toDouble()
                    }
                }
                else -> {
                    repeat(match2.matchBookmakers.size) {
                        bookmakerMatch2Map[match2.matchBookmakers[it].bookmakerName] =
                            match2.matchBookmakers[it].oddsData.away!!.toDouble()
                    }
                }
            }
        }

        if (CHOSEN_MATCHES.size == 3) {
            val match3 = CHOSEN_MATCHES[2]
            when (match3.matchType) {
                TYPE.WIN -> {
                    repeat(match3.matchBookmakers.size) {
                        bookmakerMatch3Map[match3.matchBookmakers[it].bookmakerName] =
                            match3.matchBookmakers[it].oddsData.home!!.toDouble()
                    }
                }
                TYPE.DRAW -> {
                    repeat(match3.matchBookmakers.size) {
                        bookmakerMatch3Map[match3.matchBookmakers[it].bookmakerName] =
                            match3.matchBookmakers[it].oddsData.draw!!.toDouble()
                    }
                }
                else -> {
                    repeat(match3.matchBookmakers.size) {
                        bookmakerMatch3Map[match3.matchBookmakers[it].bookmakerName] =
                            match3.matchBookmakers[it].oddsData.away!!.toDouble()
                    }
                }
            }
        }

        for (b1 in bookmakerMatch1Map) {
            for (b2 in bookmakerMatch2Map) {
                for (b3 in bookmakerMatch3Map) {
                    bookmakerMatchResultsMap[b1.key] = b1.value + b2.value + b3.value
                }
            }
        }


        fetchAllBookmakers()
    }

    private fun fetchBookmakersData() {
        if (bookmakers.isNullOrEmpty()) {
            Rest.getRest().odds.enqueue(object : Callback<BukResponse> {
                override fun onResponse(call: Call<BukResponse>, response: Response<BukResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        if (data != null) {
                            bookmakers = BookmakerMapper.mapBookmakersTypes(
                                data.fullTimeResult.bookmakers,
                                buks,
                                bookmakerMatchResultsMap
                            )
                            setBookmakersList()
                        }
                    }
                }

                override fun onFailure(call: Call<BukResponse>, t: Throwable) {
                }
            })
        }
    }

    private fun fetchAllBookmakers() {
        if (buks.isNullOrEmpty()) {
            Rest.getRest().bookmakers.enqueue(object : Callback<BukerResponse> {
                override fun onResponse(
                    call: Call<BukerResponse>,
                    response: Response<BukerResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        if (!data.isNullOrEmpty()) {
                            buks.addAll(data)
                            fetchBookmakersData()
                        }
                    }
                }

                override fun onFailure(call: Call<BukerResponse>, t: Throwable) {
                }
            })
        }
    }

    private fun setBookmakersList() {
        if (bookmakers.isNotEmpty() && bookmakerMatchResultsMap.isNotEmpty()) {
            val sorted = bookmakers.sortedBy { it.amount }.reversed()

            bookmakersAdapter = BookmakerTypesAdapter(sorted, this)
            recyclerList?.adapter = bookmakersAdapter
        }
    }
}