package ar.com.unlam.marvel_app.view.ui.fragments

import ar.com.unlam.marvel_app.data.model.apiModel.ResultsEvent
import android.os.Bundle
import android.util.Log
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
import ar.com.unlam.marvel_app.view.ui.adapters.EventRecyclerViewAdapter
import ar.com.unlam.marvel_app.view.ui.viewmodel.EventViewModels
import kotlinx.android.synthetic.main.event_list_recycler_view_row.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class EventsFragment : Fragment()  , EventRecyclerViewAdapter.OnRecyclerItemClick {
    private lateinit var recyclerAdapter: EventRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.event_list_recycler_view, container, false)
        initViewModel(view)
        initViewModel()
        return view
    }

    private fun initViewModel(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewEvents)
        recyclerView.layoutManager = LinearLayoutManager(activity)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@EventsFragment.context)
            val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerAdapter = EventRecyclerViewAdapter(this@EventsFragment)
            adapter = recyclerAdapter
        }
    }
    private fun Date.dateToString(format: String): String {
        //simple date formatter
        val dateFormatter = SimpleDateFormat(format, Locale.getDefault())

        //return the formatted date string
        return dateFormatter.format(this)
    }



    fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(EventViewModels::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<List<ResultsEvent>> {
            if (it != null) {
                recyclerAdapter.items = it.toMutableList()
                recyclerAdapter.items2= it[0].comics.items.toMutableList()
                recyclerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, getString(R.string.err_gettin_data), Toast.LENGTH_SHORT).show()
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
        Toast.makeText(this.context,"item: "+ data.title,Toast.LENGTH_SHORT).show()

    }


}
