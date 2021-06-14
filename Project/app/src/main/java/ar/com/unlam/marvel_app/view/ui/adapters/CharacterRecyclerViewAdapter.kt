package ar.com.unlam.marvel_app.view.ui.adapters

import Results
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ar.com.unlam.marvel_app.R
import com.squareup.picasso.Picasso

class CharacterRecyclerViewAdapter(val clickListener: OnRecyclerItemClick) :
    RecyclerView.Adapter<CharacterRecyclerViewAdapter.MyViewHolder>() {

    private val items = mutableListOf<Results>()

    fun setUpdatedData(itemsIn: List<Results>) {
        items.addAll(itemsIn)
        notifyDataSetChanged()
    }

    fun submitList(it: List<Results>) {
        items.clear()
        items.addAll(it)
    }

    class MyViewHolder(view: View,  var clickListener: OnRecyclerItemClick) :
        RecyclerView.ViewHolder(view) {
        val imageThumb = view.findViewById<ImageView>(R.id.imageThumb)
        val name = view.findViewById<TextView>(R.id.heroName)
        val description = view.findViewById<TextView>(R.id.heroDesc)
        val recyclerViewAdapter = CharacterRecyclerViewAdapter(clickListener)

        fun bind(data: Results) {
            name.text = data.name
            description.text = data.description
            Picasso.get()
                .load("https://media.gq.com.mx/photos/5dec0db85b7e8300097bca15/16:9/w_1920,c_limit/thanos-bebe-marvel.jpg")//(data.thumbnail.path.replace("http","https"))
                .into(imageThumb)
          //  recyclerViewAdapter.items = data

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_list_recycler_view_row, parent, false)
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
        fun onItemClickListener(data: Results)
    }
}