package ar.com.unlam.marvel_app.view.ui

import Comics
import Items
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.unlam.marvel_app.R
import ar.com.unlam.marvel_app.view.ui.adapters.ComicsDetailsCharacterAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_hero.*
import kotlinx.android.synthetic.main.activity_detail_hero.view.*

class DetailCharacterActivity : AppCompatActivity() {
    private var adapter = ComicsDetailsCharacterAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hero)
/*navigationDetailMenu.setOnNavigationItemReselectedListener {
    when(it.itemId){
R.id.close_detail -> finish()
    }
}*/
        val comics = intent.getSerializableExtra("comics") as Comics
        val name = intent.getStringExtra("name")
 //       val description = intent.getStringExtra("description")
/*        if (name != null) {
            if (name.isNotEmpty())
                navigationDetailMenu.get(R.id.navigationDetailMenu).textViewAppearTittle.text  = name.toString()
        }*/

        character_name_detail.text = name
val image = "https://media.gq.com.mx/photos/5dec0db85b7e8300097bca15/16:9/w_1920,c_limit/thanos-bebe-marvel.jpg"
        setEvents(comics.items as ArrayList<Items>)
        events_hero_list_rv.layoutManager = LinearLayoutManager(this)
        events_hero_list_rv.adapter=adapter
        Picasso.get()
            .load(image)
            .into(imageViewHeroDetail)
    }



    private fun setEvents(comicsItems: ArrayList<Items>) {

            adapter.comicsList = comicsItems
        adapter.notifyDataSetChanged()
        }

    }
