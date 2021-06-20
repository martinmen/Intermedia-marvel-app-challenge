package ar.com.unlam.marvel_app.view.ui.adapters

import Items
import ar.com.unlam.marvel_app.data.model.apiModel.ResultsEvent
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.unlam.marvel_app.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.event_list_recycler_view_row.view.*

class EventRecyclerViewAdapter(val clickListener: OnRecyclerItemClick) :
    RecyclerView.Adapter<EventRecyclerViewAdapter.MyViewHolder>() {

    var items = mutableListOf<ResultsEvent>()
    var items2 = mutableListOf<Items>()

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.expandBtn.setOnClickListener {
            if (holder.comicEventRecyclerView.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(holder.cardComicsEvent, AutoTransition())
                holder.comicEventRecyclerView.visibility = View.VISIBLE
                holder.txComicsDisc.visibility = View.VISIBLE
                it.rotation = 180.0F
            } else {
                TransitionManager.beginDelayedTransition(holder.cardComicsEvent, AutoTransition())
                holder.comicEventRecyclerView.visibility = View.GONE
                holder.txComicsDisc.visibility = View.GONE
                it.rotation = 0.0F
            }
        }
    }

    class MyViewHolder(
        view: View,
        var clickListener: OnRecyclerItemClick
    ) : RecyclerView.ViewHolder(view) {
        val imageEventThumb = view.findViewById<ImageView>(R.id.imageEventThumb)
        val title = view.findViewById<TextView>(R.id.tvEventTitle)
        val eventDesc = view.findViewById<TextView>(R.id.tv_Event_Desc)
        val txComicsDisc = view.findViewById<TextView>(R.id.textViewCommicDiscuss)
        val txNoComicsDisc = view.findViewById<TextView>(R.id.textViewNoCommicDiscuss)
        val cardComicsEvent = view.findViewById<CardView>(R.id.cardEventComicRow)
        val recyclerViewAdapter = EventRecyclerViewAdapter(clickListener)
        val comicEventRecyclerView = view.recyclerViewComicEvent
        val recyclerViewEventsAdapter = ComicsEventsAdapter()
        fun bind(data: ResultsEvent) {
            title.text = data.title
            eventDesc.text = data.modified.subSequence(0,4  )
            val url = data.thumbnail.path.replace("http", "https") + "/standard_fantastic.jpg"
            Picasso.get()
                .load(url)
                .error(R.drawable.empty_imagenew)
                .into(imageEventThumb)
            if (data.comics.items.isNotEmpty()) {
                comicEventRecyclerView.apply {
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    val recyclerViewComicsAdapter = ComicsEventsAdapter()
                    recyclerViewComicsAdapter.comicsList = data.comics.items as ArrayList<Items>
                    adapter = recyclerViewComicsAdapter
                }
            } else {
                txComicsDisc.visibility = GONE
                comicEventRecyclerView.visibility = GONE
            }

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

    interface OnRecyclerItemClick {
        fun onItemClickListener(data: ResultsEvent)
    }
}