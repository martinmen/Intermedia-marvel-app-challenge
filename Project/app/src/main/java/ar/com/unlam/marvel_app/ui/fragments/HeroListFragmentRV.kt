package ar.com.unlam.marvel_app.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.unlam.marvel_app.R
import ar.com.unlam.marvel_app.ui.adapters.HeroRecyclerViewAdapter
import ar.com.unlam.marvel_app.model.Hero
import ar.com.unlam.marvel_app.ui.DetailHeroActivity


class HeroListFragmentRV : Fragment(),HeroRecyclerViewAdapter.OnRecyclerItemClick {
    val heroList :ArrayList<Hero> = ArrayList()

    private lateinit var recyclerAdapter : HeroRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.hero_list_recycler_view, container, false)
        initViewModel()
        initViewModel(view)

        return view
    }


    private fun initViewModel(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decortion  = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decortion)
        recyclerAdapter = HeroRecyclerViewAdapter(this)
        recyclerAdapter.setUpdatedData(heroList)
        recyclerView.adapter = recyclerAdapter
    }

    private fun initViewModel() {

        heroList.add(Hero("Hola1", "comentario1", "Imagen"))
        heroList.add(Hero("Hola1", "comentario1", "Imagen"))
        heroList.add(Hero("Hola1", "comentario1", "Imagen"))
        heroList.add(Hero("Hola1", "comentario1", "Imagen"))
        heroList.add(Hero("Hola1", "comentario1", "Imagen"))
        heroList.add(Hero("Hola1", "comentario1", "Imagen"))
        heroList.add(Hero("Hola1", "comentario1", "Imagen"))
        heroList.add(Hero("Hola1", "comentario1", "Imagen"))
        heroList.add(Hero("Hola1", "comentario1", "Imagen"))


        /*    val viewModel  = ViewModelProvider(this).get(MainActivityViewModel::class.java)
             viewModel.getRecyclerListObserver().observe(this, Observer<RecyclerList> {
                 if(it != null) {
                     recyclerAdapter.setUpdatedData(it.items)
                 } else {
                     Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
                 }
             })
             viewModel.makeApiCall()*/
    }
    companion object {

        @JvmStatic
        fun newInstance() =
                HeroListFragmentRV()
    }

    override fun onItemClickListener(data: Hero) {
        val intent = Intent(this@HeroListFragmentRV.context, DetailHeroActivity::class.java)
        intent.putExtra("loc_data", data.nombre)

        startActivity(intent)    }
}