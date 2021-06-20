package ar.com.unlam.marvel_app.view.ui

import ar.com.unlam.marvel_app.data.model.apiModel.Comics
import Items
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.unlam.marvel_app.R
import ar.com.unlam.marvel_app.view.ui.adapters.ComicsDetailsCharacterAdapter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_hero.*

class DetailCharacterActivity : AppCompatActivity() {
    private var adapter = ComicsDetailsCharacterAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_hero)
        setCharacterDetailsValues()
    }

    private fun setCharacterDetailsValues() {
        val comics = intent.getSerializableExtra("comics") as Comics
        val name = intent.getStringExtra("name")
        val extension = intent.getStringExtra("extension")
        val image = intent.getStringExtra("image")
        val description = intent.getStringExtra("description")
        character_name_detail.text = name
        tv_HeroDescriptionDetails.text = description
        var url = image.toString().replace("http", "https") + "/portrait_uncanny." + extension
        Picasso.get()
            .load(url)
            .error(R.drawable.empty_imagenew)
            .into(imageViewHeroDetail)
        setEvents(comics.items as ArrayList<Items>)
        events_hero_list_rv.layoutManager = LinearLayoutManager(this)
        events_hero_list_rv.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menudetail, menu)
        menu?.findItem(R.id.logOut)?.setVisible(false)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.back -> {
                super.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)

    }

    private fun setEvents(comicsItems: ArrayList<Items>) {
        adapter.comicsList = comicsItems
        adapter.notifyDataSetChanged()
    }

}
