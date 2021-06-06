package ar.com.unlam.marvel_app.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.unlam.marvel_app.R
import ar.com.unlam.marvel_app.model.Hero
import com.squareup.picasso.Picasso

class HeroRecyclerViewAdapter(val clickListener: OnRecyclerItemClick) : RecyclerView.Adapter<HeroRecyclerViewAdapter.MyViewHolder>(){

    var items = ArrayList<Hero>()

    fun setUpdatedData(items : ArrayList<Hero>) {
        this.items = items
        notifyDataSetChanged()
    }
    class MyViewHolder(view: View,val clickListener: OnRecyclerItemClick): RecyclerView.ViewHolder(view) {
        val imageThumb2 = view.findViewById<ImageView>(R.id.imageThumb)
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvDesc = view.findViewById<TextView>(R.id.tvDesc)
        val recyclerViewAdapter  = HeroRecyclerViewAdapter(clickListener)

        fun bind(data : Hero) {
            tvTitle.text = data.nombre
            tvDesc.text = data.desc

            val url  = data.img

                Picasso.get()
                    .load(R.drawable.empty_image)
                    .into(imageThumb2)
        }
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
            clickListener.onItemClickListener(items.get(position))
        }
    }

    interface  OnRecyclerItemClick {
        fun onItemClickListener(data: Hero)
    }
}