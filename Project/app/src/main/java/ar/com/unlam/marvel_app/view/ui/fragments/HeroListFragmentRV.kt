package ar.com.unlam.marvel_app.view.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.unlam.marvel_app.R
import ar.com.unlam.marvel_app.view.ui.adapters.HeroRecyclerViewAdapter
import ar.com.unlam.marvel_app.data.model.Hero
import ar.com.unlam.marvel_app.view.ui.DetailHeroActivity
import ar.com.unlam.marvel_app.view.ui.viewmodel.HerosViewModel
import baseResponse


class HeroListFragmentRV : Fragment(),HeroRecyclerViewAdapter.OnRecyclerItemClick {
    val heroList :ArrayList<baseResponse> = ArrayList()

    private lateinit var recyclerAdapter : HeroRecyclerViewAdapter
   // private val viewModel: HerosViewModel by ViewModelProvid

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view =  inflater.inflate(R.layout.hero_list_recycler_view, container, false)

        initViewModel(view)
        initViewModel()
        return view
    }

    private fun initViewModel(view: View) {
        //
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decortion  = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decortion)
        recyclerAdapter = HeroRecyclerViewAdapter(this)
       //recyclerAdapter.setUpdatedData(viewModel._charactersListLiveData)
       // recyclerAdapter.submitList(viewModel._charactersListLiveData)
        recyclerView.adapter = recyclerAdapter
    }

    private fun initViewModel() {
        val viewModel  = ViewModelProvider(this).get(HerosViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<List<baseResponse>> {
            if(it != null) {
                recyclerAdapter.setUpdatedData(it)
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getHerores()
    }
    companion object {

        @JvmStatic
        fun newInstance() =
                HeroListFragmentRV()
    }

    override fun onItemClickListener(data: baseResponse) {
        val intent = Intent(this@HeroListFragmentRV.context, DetailHeroActivity::class.java)
        intent.putExtra("loc_data", data.data.results[0].id)

        startActivity(intent)    }
}