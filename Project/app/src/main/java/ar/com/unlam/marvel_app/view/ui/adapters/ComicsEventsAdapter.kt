package ar.com.unlam.marvel_app.view.ui.adapters

import Items
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.unlam.marvel_app.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_list_appear_recycler_view_row.view.*
import kotlinx.android.synthetic.main.event_comic_list_recycler_view_row.view.*

class ComicsEventsAdapter : RecyclerView.Adapter<ComicsEventsAdapter.ViewHolder>() {
    var comicsList = ArrayList<Items>()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.event_comic_list_recycler_view_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        comicsList[position].let { comic ->
            holder.itemView.tv_event_comic_title.text = comicsList[position].name
            var anioRandom = (1970..2020).random() //Parche provisorio, proximamente se traera la fechas.
            holder.itemView.tv_event_comic_anio.text =anioRandom.toString()// comicsList[position].resourceURI
        }

    }

    override fun getItemCount(): Int {
        return comicsList.size
    }

}