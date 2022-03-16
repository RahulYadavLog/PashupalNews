package com.mindfire.pashupaltask.adapter


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.mindfire.newstask.model.RecyclerData
import com.mindfire.pashupaltask.R
import com.mindfire.pashupaltask.activity.NewsActivity
import com.squareup.picasso.Picasso
import kotlin.coroutines.coroutineContext

class NewsSourcesAdapter :RecyclerView.Adapter<NewsSourcesAdapter.MyViewHolder>() {
    var items=ArrayList<RecyclerData?>()
    fun setUpdateData(items:ArrayList<RecyclerData?>)
    {
        this.items=items
        notifyDataSetChanged()
    }
    class MyViewHolder(view:View):RecyclerView.ViewHolder(view)
    {

        val tvTitle=view.findViewById<TextView>(R.id.tvTitle)
        fun bind(data: RecyclerData) {

                tvTitle.text = data.source.name
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.source_list,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        items.get(position)?.let { holder.bind(it) }


        holder.tvTitle.setOnClickListener {
                v ->
            val sharedPreferences: SharedPreferences = v.context.getSharedPreferences("share", Context.MODE_PRIVATE)
            val editor:SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putString("source", items.get(position)?.source?.name)
            editor.apply()
            editor.commit()
            val intent = Intent(v.context, NewsActivity::class.java)
            v.context.startActivity(intent)

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}