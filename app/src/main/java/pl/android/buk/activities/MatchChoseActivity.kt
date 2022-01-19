package pl.android.buk.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pl.android.buk.R
import pl.android.buk.api.Rest
import pl.android.buk.model.TYPE
import pl.android.buk.model.bookmaker.BukResponse
import pl.android.buk.model.bookmaker.Datum
import pl.android.buk.model.match.MatchDatum
import pl.android.buk.model.match.MatchForm
import pl.android.buk.model.match.MatchResponse
import pl.android.buk.utils.MatchAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MatchChoseActivity : AppCompatActivity() {

    private var matches: ArrayList<MatchDatum> = ArrayList()
    private var recyclerList: RecyclerView? = null
    private var info: TextView? = null
    private var typerBtn: Button? = null
    private var downloading: TextView? = null
    lateinit var matchesAdapter: MatchAdapter
    private var bookmakers: List<Datum> = ArrayList()
    private var chosenMatches: ArrayList<MatchDatum> = ArrayList()
    private var chosenMatchesForm: ArrayList<MatchForm> = ArrayList()
    val sdf = SimpleDateFormat("yyyy-MM-dd")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_chose)
        Rest.init()
        findViews()

        val startDate = sdf.format(Date())
        val endDate = sdf.format(sevenDaysFutureDate())

        info?.text = "Mecze Premier League od $startDate do $endDate"

        fetchMatchesData(startDate, endDate)
        fetchBookmakersData()
        matchesAdapter = MatchAdapter(matches, this)
    }

    private fun sevenDaysFutureDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, 7)

        return calendar.time
    }

    private fun findViews() {
        recyclerList = findViewById(R.id.list_items)
        recyclerList!!.layoutManager = LinearLayoutManager(this)
        info = findViewById(R.id.tvInfoDate)
        downloading = findViewById(R.id.tvDownloading)
        typerBtn = findViewById<Button>(R.id.btnType)
        setListener()
    }

    private fun setListener() {
        typerBtn?.setOnClickListener {
            CHOSEN_MATCHES.addAll(chosenMatchesForm)
            if (CHOSEN_MATCHES.size >= 3) {
                goToTyper()
            } else {
                Toast.makeText(this, "Nie wybrano wszystkich meczów", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun fetchMatchesData(startDate: String, endDate: String) {
        if (matches.isNullOrEmpty()) {
            Rest.getRest().getMatches(startDate, endDate).enqueue(object : Callback<MatchResponse> {
                override fun onResponse(
                    call: Call<MatchResponse>,
                    response: Response<MatchResponse>
                ) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        if (!data.isNullOrEmpty()) {
                            matches.addAll(data)
                            setMatchesList()
                        }
                    }
                }

                override fun onFailure(call: Call<MatchResponse>, t: Throwable) {
                    Log.e("ERROR", t.message.toString())
                    //Toast.makeText(, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        } else {
            setMatchesList()
        }
    }

    private fun fetchBookmakersData() {
        if (bookmakers.isNullOrEmpty()) {
            Rest.getRest().odds.enqueue(object : Callback<BukResponse> {
                override fun onResponse(call: Call<BukResponse>, response: Response<BukResponse>) {
                    if (response.isSuccessful) {
                        val data = response.body()?.data
                        if (data != null) {
                            bookmakers = listOf(
                                // data.asianHandicap,
                                data.fullTimeResult,
                                // data.overUnderGoalLine
                            )
                        }
                    }
                }

                override fun onFailure(call: Call<BukResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun setMatchesList() {
        downloading?.visibility = GONE
        recyclerList?.visibility = VISIBLE
        matchesAdapter = MatchAdapter(matches, this)
        recyclerList?.adapter = matchesAdapter
        matchesAdapter.onItemClick = { match ->
            if (chosenMatchesForm.size < 3) {
                setMatchData(match, false)
                Toast.makeText(this, "Wybrano mecz", Toast.LENGTH_SHORT).show()
//            } else if (chosenMatchesForm.size == 2) {
//                setMatchData(match, true)
            }
        }
    }

    private fun setMatchData(match: MatchDatum, isLast: Boolean) {
        Rest.getRest().getMatchOdds(match.matchId).enqueue(object : Callback<BukResponse> {
            override fun onResponse(call: Call<BukResponse>, response: Response<BukResponse>) {
                if (response.isSuccessful) {
                    val oddData = response.body()?.data
                    if (oddData != null) {
                        chosenMatchesForm.add(
                            MatchForm(
                                match.matchId,
                                oddData.fullTimeResult.bookmakers,
                                TYPE.WIN,
                                match
                            )
                        )
//                        if (isLast) {
//                            CHOSEN_MATCHES.addAll(chosenMatchesForm)
//                            goToTyper()
//                        }
                    }
                }
            }

            override fun onFailure(call: Call<BukResponse>, t: Throwable) {
            }
        }
        )
    }

    private fun goToTyper() {
        startActivity(Intent(this, TyperActivity::class.java))
    }

    override fun onPause() {
        super.onPause()
        chosenMatches.clear()
    }

    companion object {
        var CHOSEN_MATCHES = ArrayList<MatchForm>()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

}