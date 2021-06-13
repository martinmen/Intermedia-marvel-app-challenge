package ar.com.unlam.marvel_app.view.ui.adapters

import Items
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.com.unlam.marvel_app.R
import kotlinx.android.synthetic.main.hero_list_appear_recycler_view_row.view.*

class ComicsDetailsCharacterAdapter : RecyclerView.Adapter<ComicsDetailsCharacterAdapter.ViewHolder>()
{
        var comicsList = ArrayList<Items>()

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.hero_list_appear_recycler_view_row, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        comicsList[position].let { article ->
            holder.itemView.tv_event_name.text = comicsList[position].name
            holder.itemView.tv_event_desc.text =comicsList[position].resourceURI

        }
    }

    override fun getItemCount(): Int {
return comicsList.size
    }
}