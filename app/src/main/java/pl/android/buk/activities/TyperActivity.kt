package pl.android.buk.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.RenderProcessGoneDetail
import android.widget.*
import com.bumptech.glide.Glide
import pl.android.buk.R
import pl.android.buk.activities.MatchChoseActivity.Companion.CHOSEN_MATCHES
import pl.android.buk.model.TYPE

class TyperActivity : AppCompatActivity() {

    private var away: TextView? = null
    private var awayImage: ImageView? = null
    private var home: TextView? = null
    private var homeImage: ImageView? = null

    private var away2: TextView? = null
    private var awayImage2: ImageView? = null
    private var home2: TextView? = null
    private var homeImage2: ImageView? = null

    private var away3: TextView? = null
    private var awayImage3: ImageView? = null
    private var home3: TextView? = null
    private var homeImage3: ImageView? = null

    private lateinit var win: CheckBox
    private lateinit var draw: CheckBox
    private lateinit var lose: CheckBox
    private lateinit var win2: CheckBox
    private lateinit var draw2: CheckBox
    private lateinit var lose2: CheckBox
    private lateinit var win3: CheckBox
    private lateinit var draw3: CheckBox
    private lateinit var lose3: CheckBox

    private lateinit var m1: RelativeLayout
    private lateinit var m2: RelativeLayout
    private lateinit var m3: RelativeLayout

    private lateinit var check: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_typer)
        findViews()
        setListeners()
        fillMatchData()
    }

    private fun findViews() {
        win = findViewById(R.id.cbWin1)
        draw = findViewById(R.id.cbDraw1)
        lose = findViewById(R.id.cbLose1)

        win3 = findViewById(R.id.cbWin3)
        draw3 = findViewById(R.id.cbDraw3)
        lose3 = findViewById(R.id.cbLose3)

        win2 = findViewById(R.id.cbWin2)
        draw2 = findViewById(R.id.cbDraw2)
        lose2 = findViewById(R.id.cbLose2)

        away = findViewById(R.id.tvTypeMatchAway1)
        awayImage = findViewById(R.id.ivTypeMatchAway1)
        home = findViewById(R.id.tvTypeMatchHome1)
        homeImage = findViewById(R.id.ivTypeMatchHome1)

        away2 = findViewById(R.id.tvTypeMatchAway2)
        awayImage2 = findViewById(R.id.ivTypeMatchAway2)
        home2 = findViewById(R.id.tvTypeMatchHome2)
        homeImage2 = findViewById(R.id.ivTypeMatchHome2)

        away3 = findViewById(R.id.tvTypeMatchAway3)
        awayImage3 = findViewById(R.id.ivTypeMatchAway3)
        home3 = findViewById(R.id.tvTypeMatchHome3)
        homeImage3 = findViewById(R.id.ivTypeMatchHome3)

        check = findViewById<Button>(R.id.btnCheck)

        setListeners()
    }

    private fun setListeners() {
        win.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                draw.isChecked = false
                lose.isChecked = false
                CHOSEN_MATCHES[0].matchType = TYPE.WIN
            }
        }

        draw.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                win.isChecked = false
                lose.isChecked = false
                CHOSEN_MATCHES[0].matchType = TYPE.DRAW
            }
        }

        lose.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                draw.isChecked = false
                win.isChecked = false
                CHOSEN_MATCHES[0].matchType = TYPE.LOSE
            }
        }

        win2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                draw2.isChecked = false
                lose2.isChecked = false
                CHOSEN_MATCHES[1].matchType = TYPE.WIN
            }
        }

        draw2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                win2.isChecked = false
                lose2.isChecked = false
                CHOSEN_MATCHES[1].matchType = TYPE.DRAW
            }
        }

        lose2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                draw2.isChecked = false
                win2.isChecked = false
                CHOSEN_MATCHES[1].matchType = TYPE.LOSE
            }
        }

        win3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                draw3.isChecked = false
                lose3.isChecked = false
                CHOSEN_MATCHES[2].matchType = TYPE.WIN
            }
        }

        draw3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                win3.isChecked = false
                lose3.isChecked = false
                CHOSEN_MATCHES[2].matchType = TYPE.DRAW
            }
        }

        lose3.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                draw3.isChecked = false
                win3.isChecked = false
                CHOSEN_MATCHES[2].matchType = TYPE.LOSE
            }
        }

        check.setOnClickListener {
            startActivity(Intent(this, BookmakersTypesActivity::class.java))
        }
    }

    private fun fillMatchData() {
            home?.text = CHOSEN_MATCHES[0].datum.homeTeam.name
            away?.text = CHOSEN_MATCHES[0].datum.awayTeam.name

            Glide.with(this)
                .load(CHOSEN_MATCHES[0].datum.homeTeam.logo)
                .placeholder(R.drawable.ic_placeholder)
                .override(50, 50)
                .centerCrop()
                .into(homeImage!!)

            Glide.with(this)
                .load(CHOSEN_MATCHES[0].datum.awayTeam.logo)
                .placeholder(R.drawable.ic_placeholder)
                .override(50, 50)
                .centerCrop()
                .into(awayImage!!)

                home2?.text = CHOSEN_MATCHES[1].datum.homeTeam.name
                away2?.text = CHOSEN_MATCHES[1].datum.awayTeam.name

                Glide.with(this)
                    .load(CHOSEN_MATCHES[1].datum.homeTeam.logo)
                    .placeholder(R.drawable.ic_placeholder)
                    .override(50, 50)
                    .centerCrop()
                    .into(homeImage2!!)

                Glide.with(this)
                    .load(CHOSEN_MATCHES[1].datum.awayTeam.logo)
                    .placeholder(R.drawable.ic_placeholder)
                    .override(50, 50)
                    .centerCrop()
                    .into(awayImage2!!)


                home3?.text = CHOSEN_MATCHES[2].datum.homeTeam.name
                away3?.text = CHOSEN_MATCHES[2].datum.awayTeam.name

                Glide.with(this)
                    .load(CHOSEN_MATCHES[2].datum.homeTeam.logo)
                    .placeholder(R.drawable.ic_placeholder)
                    .override(50, 50)
                    .centerCrop()
                    .into(homeImage3!!)

                Glide.with(this)
                    .load(CHOSEN_MATCHES[2].datum.awayTeam.logo)
                    .placeholder(R.drawable.ic_placeholder)
                    .override(50, 50)
                    .centerCrop()
                    .into(awayImage3!!)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}