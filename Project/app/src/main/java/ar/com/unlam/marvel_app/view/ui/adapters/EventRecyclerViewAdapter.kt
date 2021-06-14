package ar.com.unlam.marvel_app.view.ui.adapters

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

    private var items = mutableListOf<ResultsEvent>()

    fun setUpdatedData(itemsIn: List<ResultsEvent>) {
        items.addAll(itemsIn)
        notifyDataSetChanged()
    }

    fun submitList(it: List<ResultsEvent>) {
        items.clear()
        items.addAll(it)
    }

    class MyViewHolder(view: View, var clickListener: OnRecyclerItemClick) :
        RecyclerView.ViewHolder(view) {
        val imageEventThumb = view.findViewById<ImageView>(R.id.imageEventThumb)
        val title = view.findViewById<TextView>(R.id.tvEventTitle)
        val eventDesc = view.findViewById<TextView>(R.id.tv_Event_Desc)

        val comicEventRecyclerView = view.recyclerViewComicEvent
      //  val anioComic = view.findViewById<TextView>(R.id.tv_event_comic_anio)

        val recyclerViewEventsAdapter = EventRecyclerViewAdapter(clickListener)
        val titleComic = view.findViewById<TextView>(R.id.tv_event_comic_title)


        fun bind(data: ResultsEvent) {
            title.text = data.title
            eventDesc.text = data.modified


            var url = data.thumbnail.path.replace("http", "https") + "/standard_fantastic.jpg"
            Picasso.get()
                .load(url)
                .error(R.drawable.empty_imagenew)
                .into(imageEventThumb)

/*            comicEventRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                val decoration = DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL)
                addItemDecoration(decoration)
                var recyclerViewAdapter = EventRecyclerViewAdapter(clickListener)

                titleComic.text = data.comics.items[0].name
                adapter = recyclerViewAdapter
            }*/

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_list_recycler_view_row, parent, false)
        return MyViewHolder(view, clickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.setOnClickListener {
            clickListener.onItemClickListener(items[position])//(items.get(position))
        }
    }

    interface OnRecyclerItemClick {
        fun onItemClickListener(data: ResultsEvent)
    }
}