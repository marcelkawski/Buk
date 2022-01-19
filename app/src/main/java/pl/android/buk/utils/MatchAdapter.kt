package pl.android.buk.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pl.android.buk.R
import pl.android.buk.model.match.MatchDatum
import androidx.cardview.widget.CardView

class MatchAdapter(private val matches: List<MatchDatum>, private val context: Context) :
    RecyclerView.Adapter<MatchAdapter.ViewHolder>() {

    var onItemClick: ((MatchDatum) -> Unit)? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.match_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.home.text = matches[position].homeTeam.name
        viewHolder.away.text = matches[position].awayTeam.name

        Glide.with(context!!)
            .load(matches[position].homeTeam.logo)
            .placeholder(R.drawable.ic_placeholder)
            .override(50, 50)
            .centerCrop()
            .into(viewHolder.imageHome)

        Glide.with(context)
            .load(matches[position].awayTeam.logo)
            .placeholder(R.drawable.ic_placeholder)
            .override(50, 50)
            .centerCrop()
            .into(viewHolder.imageAway)
    }

    override fun getItemCount() = matches.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var home: TextView = view.findViewById(R.id.tvHome)
        var away: TextView = view.findViewById(R.id.tvAway)
        var imageHome: ImageView = view.findViewById(R.id.ivHome)
        var imageAway: ImageView = view.findViewById(R.id.ivAway)

        init {
            view.setOnClickListener {
                onItemClick?.invoke(matches[adapterPosition])
            }
        }
    }

}