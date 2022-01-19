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
import pl.android.buk.model.MappedBookmakersTypes

class BookmakerTypesAdapter(
    private val bookmakers: List<MappedBookmakersTypes>,
    private val context: Context
) : RecyclerView.Adapter<BookmakerTypesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.tvName)
        var logo: ImageView = view.findViewById(R.id.ivBukLogo)
        var totalAmount: TextView = view.findViewById(R.id.tvTotalAmount)
    }

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.bookmaker_type_row_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.name.text = bookmakers[position].bookmakerName
        viewHolder.totalAmount.text = bookmakers[position].amount.toString() + " zł"

        Glide.with(context)
            .load(bookmakers[position].logo)
            .placeholder(R.drawable.ic_placeholder)
            .fitCenter()
            .into(viewHolder.logo)
    }

    override fun getItemCount() = bookmakers.size
}