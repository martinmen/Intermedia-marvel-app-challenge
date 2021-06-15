package ar.com.unlam.marvel_app.view.ui.adapters

import Comics
import Events
import Items
import ResultsEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.unlam.marvel_app.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.event_comic_list_recycler_view_row.view.*
import kotlinx.android.synthetic.main.event_list_recycler_view_row.view.*

class EventRecyclerViewAdapter(val clickListener: OnRecyclerItemClick) :
    RecyclerView.Adapter<EventRecyclerViewAdapter.MyViewHolder>() {

    var items = mutableListOf<ResultsEvent>()
    var items2 = mutableListOf<Items>()

    class MyViewHolder(
        view: View,
        view2: View,
        view3: View,
        var clickListener: OnRecyclerItemClick
    ) :
        RecyclerView.ViewHolder(view) {
        val imageEventThumb = view.findViewById<ImageView>(R.id.imageEventThumb)
        val title = view.findViewById<TextView>(R.id.tvEventTitle)
        val eventDesc = view.findViewById<TextView>(R.id.tv_Event_Desc)
        val comicEventRecyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewComicEvent)

        val recyclerViewEventsAdapter = EventRecyclerViewAdapter(clickListener)
        val titleComic = view3.tv_event_comic_title
        fun bind(data: ResultsEvent, data2: Items) {
            title.text = data.title
            eventDesc.text = data.modified
            titleComic.text = data2.name
            var url = data.thumbnail.path.replace("http", "https") + "/standard_fantastic.jpg"
            Picasso.get()
                .load(url)
                .error(R.drawable.empty_imagenew)
                .into(imageEventThumb)
            if (data2.name != null) {
                comicEventRecyclerView.apply {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                    addItemDecoration(decoration)
                    val recyclerViewAdapter = EventRecyclerViewAdapter(clickListener)
                    recyclerViewAdapter.items2 = data.comics.items.toMutableList()
                    adapter = recyclerViewAdapter

                }

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_list_recycler_view_row, parent, false)
        val view2 = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_comic_list_recycler_view, parent, false)
        val view3 = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_comic_list_recycler_view_row, parent, false)
        return MyViewHolder(view, view2, view3, clickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position], items2[position])
        holder.itemView.expandBtn.setOnClickListener {
            clickListener.onItemClickListener(items[position])//(items.get(position))
        }
    }

    interface OnRecyclerItemClick {
        fun onItemClickListener(data: ResultsEvent)
    }
}