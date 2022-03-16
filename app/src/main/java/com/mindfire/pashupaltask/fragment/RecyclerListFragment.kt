package com.mindfire.pashupaltask.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
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

class RecyclerListFragment : Fragment() {

    private lateinit var recyclerAdapter: RecyclerViewAdapter
    private var newsList = mutableListOf<RecyclerData?>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_recycler_list, container, false)

        initViewModel(view)
        initViewModel()
       var editSearch=view.findViewById<EditText>(R.id.editSearch)
        editSearch.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter1(s.toString())


            }

        })
        return view
    }

    fun initViewModel(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decoration)
        recyclerAdapter = RecyclerViewAdapter()
        recyclerView.adapter = recyclerAdapter
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<RecyclerList> {
            var array= arrayListOf<String>()

            if (it != null) {
                val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences("share", Context.MODE_PRIVATE)
                var source = sharedPreferences.getString("source", "")
                var appCardDataBanner = it.articles as ArrayList<RecyclerData?>
                var list=ArrayList<RecyclerData?>()
                for (i in 0 until appCardDataBanner.size){
                    var socurceApi = appCardDataBanner[i]?.source?.name
                    if (source.equals(socurceApi)&&!array.contains(appCardDataBanner[i]?.title)){
                        if (socurceApi != null) {
                            appCardDataBanner[i]?.title?.let { it1 -> array.add(it1) }
                        }
                        list.add(it.articles[i])
                    }

                }
                recyclerAdapter.setUpdateData(list,requireContext())

                //          recyclerAdapter.setUpdateData(it.articles,requireContext())
                newsList.addAll(it.articles)
            } else {
                Toast.makeText(activity, "Error in getting Data", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.makeApiCall()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            RecyclerListFragment()
    }

    private fun filter1(text: String) {
        val filteredList: ArrayList<RecyclerData?> = ArrayList()
        for (item in newsList) {
            if (item?.title?.lowercase()?.contains(text.lowercase())!!) {
                filteredList.add(item)
            }
        }
        recyclerAdapter.filterList(filteredList)
    }

}

