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
        val extension = intent.getStringExtra("extension")
        val image = intent.getStringExtra("image")
       val description = intent.getStringExtra("description")
/*        if (name != null) {
            if (name.isNotEmpty())
                navigationDetailMenu.get(R.id.navigationDetailMenu).textViewAppearTittle.text  = name.toString()
        }*/

        character_name_detail.text = name
        tv_HeroDescriptionDetails.text=description
        var url = image.toString().replace("http","https")+"/portrait_uncanny."+extension

        Picasso.get()
            .load(url)
            .error(R.drawable.empty_imagenew)
            .into(imageViewHeroDetail)
        setEvents(comics.items as ArrayList<Items>)
        events_hero_list_rv.layoutManager = LinearLayoutManager(this)
        events_hero_list_rv.adapter=adapter
    }



    private fun setEvents(comicsItems: ArrayList<Items>) {

            adapter.comicsList = comicsItems
        adapter.notifyDataSetChanged()
        }

    }
