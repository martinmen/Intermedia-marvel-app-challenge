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

class ComicsEventsAdapter : RecyclerView.Adapter<ComicsEventsAdapter.ViewHolder>()
{
        var comicsList = ArrayList<Items>()

        class ViewHolder(view: View,) : RecyclerView.ViewHolder(view) {


/*            val titleComic = view.findViewById<TextView>(R.id.tv_event_comic_title)
            val anioComic = view.findViewById<TextView>(R.id.tv_event_anio)
            val recyclerViewComicsEventsAdapter = ComicsEventsAdapter()

            fun bind(data: Items) {
                titleComic.text = data.name
                anioComic.text = data.resourceURI

            }*/
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.event_comic_list_recycler_view_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //holder.bind(comicsList[position])

       comicsList[position].let { article ->
            holder.itemView.tv_event_comic_title.text = comicsList[position].name
            holder.itemView.tv_event_comic_anio.text = comicsList[position].resourceURI
        }
    }

    override fun getItemCount(): Int {
return comicsList.size
    }

    interface OnRecyclerItemClick {
        fun onItemClickListener(data: Items)
    }
}