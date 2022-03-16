package com.mindfire.pashupaltask.fragment

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
import com.mindfire.newstask.adapter.RecyclerViewAdapter
import com.mindfire.newstask.model.RecyclerData
import com.mindfire.newstask.model.RecyclerList
import com.mindfire.newstask.viewmodel.MainActivityViewModel
import com.mindfire.pashupaltask.R
import com.mindfire.pashupaltask.adapter.NewsSourcesAdapter

class NewsSourcesFragment : Fragment() {

    private lateinit var recyclerAdapter: NewsSourcesAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_news_sources, container, false)
        initViewModel(view)
        initViewModel()
        return view
    }

    fun initViewModel(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerAdapter = NewsSourcesAdapter()
        recyclerView.adapter = recyclerAdapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<RecyclerList> {
            var array= arrayListOf<String>()
            if (it != null) {
                var appCardDataBanner = it.articles as ArrayList<RecyclerData?>
                var list=ArrayList<RecyclerData?>()
                for (i in 0 until appCardDataBanner.size){
                    var socurceApi = appCardDataBanner[i]?.source?.name

                    if (!array.contains(socurceApi)){
                        if (socurceApi != null) {
                            array.add(socurceApi)
                        }
                        list.add(it.articles[i])
                    }

                }
                recyclerAdapter.setUpdateData(list)

            } else {
                Toast.makeText(activity, "Error in getting Data", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.makeApiCall()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            NewsSourcesFragment()
    }
}

