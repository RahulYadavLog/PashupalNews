package com.mindfire.newstask.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindfire.newstask.model.RecyclerList
import com.mindfire.newstask.network.RetroService
import com.mindfire.newstask.network.RetrofitInstanceClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class MainActivityViewModel:ViewModel() {
    lateinit var recyclerListViewData:MutableLiveData<RecyclerList>
    init {
    recyclerListViewData= MutableLiveData()
    }
    fun getRecyclerListObserver():MutableLiveData<RecyclerList>
    {
        return recyclerListViewData
    }
    fun makeApiCall()
    {
        viewModelScope.launch(Dispatchers.IO) {
         val retroInstance=  RetrofitInstanceClass.getRetrofitInstance().create(RetroService::class.java)
        var response=  retroInstance.getDataFromApi("in","business",null,"7c10f759c3974e7f91cea03560ab0475")
            recyclerListViewData.postValue(response)
        }
    }

}