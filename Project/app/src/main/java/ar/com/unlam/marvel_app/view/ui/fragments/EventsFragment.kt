package ar.com.unlam.marvel_app.view.ui.fragments

import ResultsEvent
import android.content.Intent
import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
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
import ar.com.unlam.marvel_app.view.ui.DetailCharacterActivity
import ar.com.unlam.marvel_app.view.ui.adapters.CharacterRecyclerViewAdapter
import ar.com.unlam.marvel_app.view.ui.adapters.EventRecyclerViewAdapter
import ar.com.unlam.marvel_app.view.ui.viewmodel.CharacterViewModel
import ar.com.unlam.marvel_app.view.ui.viewmodel.EventViewModels
import kotlinx.android.synthetic.main.event_list_recycler_view_row.*


class EventsFragment : Fragment()  , EventRecyclerViewAdapter.OnRecyclerItemClick {
    private lateinit var recyclerAdapter: EventRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.event_list_recycler_view, container, false)
/*        expandBtn.setOnClickListener {
            if (recyclerViewComicEvent.visibility == View.GONE) {
                TransitionManager.beginDelayedTransition(cardEventComicRow, AutoTransition())
                recyclerViewComicEvent.visibility = View.VISIBLE
                expandBtn.text = "COLLAPSE"
            } else {
                TransitionManager.beginDelayedTransition(cardEventComicRow, AutoTransition())
                recyclerViewComicEvent.visibility = View.GONE
                expandBtn.text = "EXPAND"
            }
        }*/
        initViewModel(view)
        initViewModel()
        return view
    }

    private fun initViewModel(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewEvents)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        // val decortion = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        //recyclerView.addItemDecoration(decortion)
        // recyclerAdapter = HeroRecyclerViewAdapter(this)
        // recyclerView.adapter = recyclerAdapter

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@EventsFragment.context)
            val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerAdapter = EventRecyclerViewAdapter(this@EventsFragment)
            adapter = recyclerAdapter
        }
    }

    fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(EventViewModels::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<List<ResultsEvent>> {
            if (it != null) {
                recyclerAdapter.setUpdatedData(it)
                recyclerAdapter.submitList(it)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "Error in getting data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getEvents()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            EventsFragment()
    }

    override fun onItemClickListener(data: ResultsEvent) {
        val intent = Intent(this.context, DetailCharacterActivity::class.java)
        intent.putExtra("name", data.title)
        intent.putExtra("description", data.description)
        intent.putExtra("image", data.thumbnail.path)
        intent.putExtra("extension", data.thumbnail.extension)
        intent.putExtra("comics", data.comics)
       // startActivity(intent)
    }


}
