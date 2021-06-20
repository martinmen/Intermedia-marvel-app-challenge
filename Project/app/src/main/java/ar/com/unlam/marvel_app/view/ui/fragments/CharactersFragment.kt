package ar.com.unlam.marvel_app.view.ui.fragments

import ResultsCharacters
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
import ar.com.unlam.marvel_app.view.ui.adapters.CharacterRecyclerViewAdapter
import ar.com.unlam.marvel_app.view.ui.DetailCharacterActivity
import ar.com.unlam.marvel_app.view.ui.viewmodel.CharacterViewModel
import kotlinx.android.synthetic.main.character_list_recycler_view.*

class CharactersFragment : Fragment(), CharacterRecyclerViewAdapter.OnRecyclerItemClick {
    private lateinit var recyclerAdapter: CharacterRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.character_list_recycler_view, container, false)

        initViewModel(view)
        initViewModel()
        return view
    }

    private fun initViewModel(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewCharacters)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@CharactersFragment.context)
            val decoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            addItemDecoration(decoration)
            recyclerAdapter = CharacterRecyclerViewAdapter(this@CharactersFragment)
            adapter = recyclerAdapter
        }
    }

    fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<List<ResultsCharacters>> {
            if (it != null) {
                recyclerAdapter.setUpdatedData(it)
                recyclerAdapter.submitList(it)
                recyclerAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, getString(R.string.err_gettin_data), Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getCharacters()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            CharactersFragment()
    }

    override fun onItemClickListener(data: ResultsCharacters) {
        val intent = Intent(this@CharactersFragment.context, DetailCharacterActivity::class.java)
        intent.putExtra("name", data.name)
        intent.putExtra("description", data.description)
        intent.putExtra("image", data.thumbnail.path)
        intent.putExtra("extension", data.thumbnail.extension)
        intent.putExtra("comics", data.comics)
        startActivity(intent)
    }


}
