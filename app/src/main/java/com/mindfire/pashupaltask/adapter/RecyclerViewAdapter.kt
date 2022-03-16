package com.mindfire.newstask.adapter

import android.content.Context
import android.content.SharedPreferences
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.mindfire.newstask.model.RecyclerData
import com.mindfire.pashupaltask.R
import com.squareup.picasso.Picasso
import java.util.*

class RecyclerViewAdapter :RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {
    var items=ArrayList<RecyclerData?>()
    var newsList=ArrayList<RecyclerData?>()
    lateinit var   context:Context;

    fun setUpdateData(items:ArrayList<RecyclerData?>,context: Context)
    {
        this.items=items
        newsList=items
        this.context=context

        notifyDataSetChanged()
    }
    class MyViewHolder(view:View):RecyclerView.ViewHolder(view)
    {
        val imageThumb=view.findViewById<ImageView>(R.id.imageThumb)
        val tvTitle=view.findViewById<TextView>(R.id.tvTitle)
        val tvDesc=view.findViewById<TextView>(R.id.tvDesc)
        val layout=view.findViewById<LinearLayout>(R.id.layout)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences("share", Context.MODE_PRIVATE)
        var source = sharedPreferences.getString("source", "")
        var array = arrayListOf<String>()

        if (source.equals(items[position]?.source?.name)){
            holder.tvTitle.text = items.get(position)?.title
            holder.tvDesc.text = items.get(position)?.description
            val url = items.get(position)?.urlToImage
            Picasso.get()
                .load(url)
                .into(holder.imageThumb)
        }
        holder.layout.setOnClickListener {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(context, Uri.parse(items.get(position)?.url))
        }


/*
        if (source == items.get(position)?.source?.name) {
           if (!array.contains(items.get(position)?.title)) {
            items.get(position)?.title?.let { array.add(it) }
            holder.tvTitle.text = items.get(position)?.title
            holder.tvDesc.text = items.get(position)?.description
            val url = items.get(position)?.urlToImage
            Picasso.get()
                .load(url)
                .into(holder.imageThumb)
        }
    }
*/
    }


    override fun getItemCount(): Int {
        return items.size
    }


//  fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val charSearch = constraint.toString()
//                if (charSearch.isEmpty()) {
//                    items = newsList as ArrayList<RecyclerData?>
//                } else {
//                    val resultList = ArrayList<RecyclerData>()
//                    for (row in newsList) {
//                        if (row.title.toLowerCase().contains(constraint.toString().toLowerCase())) {
//                            resultList.add(row)
//                        }
//                    }
//                    items= resultList
//                }
//                val filterResults = FilterResults()
//                filterResults.values = items
//                return filterResults
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                items = results?.values as ArrayList<RecyclerData>
//                notifyDataSetChanged()
//            }
//        }
//    }
    fun filterList(filteredList: ArrayList<RecyclerData?>) {
        this.items = filteredList
        notifyDataSetChanged()
    }

}