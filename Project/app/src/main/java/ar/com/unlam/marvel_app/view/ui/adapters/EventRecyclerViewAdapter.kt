package ar.com.unlam.marvel_app.view.ui.adapters

import Items
import ResultsEvent
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.graphics.drawable.toDrawable
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


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position])
        holder.itemView.expandBtn.setOnClickListener {
            if (holder.comicEventRecyclerView.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(holder.cardComicsEvent, AutoTransition())
                holder.rvComicsEvent.visibility = View.VISIBLE
                holder.txComicsDisc.visibility = View.VISIBLE
                it.background =
                    R.drawable.ic_black_arrow_up_24.toDrawable().mutate() //. toDrawable()
                /*Picasso.get()
                    .load(R.drawable.ic_black_arrow_down_24).into(holder.itemView.expandBtn)*/
            } else {
                TransitionManager.beginDelayedTransition(holder.cardComicsEvent, AutoTransition())
                holder.rvComicsEvent.visibility = View.GONE
                holder.txComicsDisc.visibility = View.GONE
                /* Picasso.get()
                     .load(R.drawable.ic_black_arrow_up_24).into(holder.itemView.expandBtn)*/
                it.background = R.drawable.ic_black_arrow_down_24.toDrawable().mutate()
            }
        }
    }

    class MyViewHolder(
        view: View,
        view2: View,
        var clickListener: OnRecyclerItemClick
    ) : RecyclerView.ViewHolder(view) {
        val imageEventThumb = view.findViewById<ImageView>(R.id.imageEventThumb)
        val title = view.findViewById<TextView>(R.id.tvEventTitle)
        val eventDesc = view.findViewById<TextView>(R.id.tv_Event_Desc)
        //        val btnArrow = view.findViewById<Button>(R.id.expandBtn)
        val txComicsDisc = view.findViewById<TextView>(R.id.textViewCommicDiscuss)
        val rvComicsEvent = view.findViewById<RecyclerView>(R.id.recyclerViewComicEvent)
        val cardComicsEvent = view.findViewById<CardView>(R.id.cardEventComicRow)
        val recyclerViewAdapter = EventRecyclerViewAdapter(clickListener)

        val comicEventRecyclerView = view.recyclerViewComicEvent
        val recyclerViewEventsAdapter = ComicsEventsAdapter()
        val titleComic = view2.tv_event_comic_title
        fun bind(data: ResultsEvent) {
            title.text = data.title
            eventDesc.text = data.modified
            val url = data.thumbnail.path.replace("http", "https") + "/standard_fantastic.jpg"
            Picasso.get()
                .load(url)
                .error(R.drawable.empty_imagenew)
                .into(imageEventThumb)
              if(data.comics.items.isNotEmpty()){
            comicEventRecyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                val recyclerViewComicsAdapter = ComicsEventsAdapter()
                recyclerViewComicsAdapter.comicsList = data.comics.items as ArrayList<Items>
                adapter = recyclerViewComicsAdapter
            }
            }else{
                  txComicsDisc.visibility = GONE
                  comicEventRecyclerView.visibility = GONE
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_list_recycler_view_row, parent, false)
        val view2 = LayoutInflater.from(parent.context)
            .inflate(R.layout.event_comic_list_recycler_view_row, parent, false)
        return MyViewHolder(view,view2, clickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    interface OnRecyclerItemClick {
        fun onItemClickListener(data: ResultsEvent)
    }
}