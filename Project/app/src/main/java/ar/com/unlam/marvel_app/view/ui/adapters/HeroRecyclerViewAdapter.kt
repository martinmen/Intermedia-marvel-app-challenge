package ar.com.unlam.marvel_app.view.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import ar.com.unlam.marvel_app.R
import ar.com.unlam.marvel_app.data.model.Hero
import baseResponse
import com.squareup.picasso.Picasso

class HeroRecyclerViewAdapter(val clickListener: OnRecyclerItemClick) : RecyclerView.Adapter<HeroRecyclerViewAdapter.MyViewHolder>(){

    //var items = ArrayList<Hero>()
    private val items = mutableListOf<baseResponse>()

   // fun setUpdatedData(items : ArrayList<Hero>) {
   fun setUpdatedData(itemsIn : List<baseResponse>) {
       items.addAll(itemsIn)
        notifyDataSetChanged()
    }
    class MyViewHolder(view: View,val clickListener: OnRecyclerItemClick): RecyclerView.ViewHolder(view) {
        val imageThumb2 = view.findViewById<ImageView>(R.id.imageThumb)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvDesc = view.findViewById<TextView>(R.id.tvDesc)
        val recyclerViewAdapter  = HeroRecyclerViewAdapter(clickListener)

        fun bind(data : baseResponse) {
            tvTitle.text = data.data.results[0].name
            tvDesc.text = data.data.results[0].description

            val url  = data.data.results[0].thumbnail.toString()

                Picasso.get()
                    .load(url)
                    .into(imageThumb2)
        }
    }
    fun submitList(it: List<baseResponse>) {
        items.clear()
        items.addAll(it)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hero_list_recycler_view_row, parent, false)

        return MyViewHolder(view,clickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
      holder.bind(items.get(position))
        holder.itemView.setOnClickListener{
            clickListener.onItemClickListener(items.get(position))//(items.get(position))
        }
    }

    interface  OnRecyclerItemClick {
        fun onItemClickListener(data: baseResponse)
    }
}