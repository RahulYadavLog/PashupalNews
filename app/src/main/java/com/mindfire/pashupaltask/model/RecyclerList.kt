package com.mindfire.newstask.model

data class RecyclerList(val articles:ArrayList<RecyclerData?>)
data class RecyclerData(val title:String,val description:String,val url:String,val urlToImage:String,val source: Source)
data class Source(val name:String)