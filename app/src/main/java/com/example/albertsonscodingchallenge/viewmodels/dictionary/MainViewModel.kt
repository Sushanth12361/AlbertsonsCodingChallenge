package com.example.albertsonscodingchallenge.viewmodels.dictionary

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.albertsonscodingchallenge.model.DictionaryMeaning
import com.example.albertsonscodingchallenge.viewmodels.CustomAndroidViewModel
import com.example.albertsonscodingchallenge.viewmodels.dictionary.data_source.ListDataRepository
import com.example.albertsonscodingchallenge.viewmodels.dictionary.data_source.adapter.DictionaryMeaningListAdapter
import com.example.albertsonscodingchallenge.viewmodels.dictionary.data_source.ListDataSource

class MainViewModel(application: Application) : CustomAndroidViewModel(application) {

    private val meaningMutableLiveData = MutableLiveData<List<DictionaryMeaning>>()
    private val errorMsg = ObservableField<String>()
    private val listAdapter: DictionaryMeaningListAdapter = DictionaryMeaningListAdapter()
    var listDataRepository: ListDataSource? = null

    init {
        listDataRepository = ListDataRepository.getInstance(application.applicationContext)
    }

    override fun onStart(owner: LifecycleOwner) {
        meaningMutableLiveData.observe(owner, Observer { item ->
            if (item.isEmpty())
                listAdapter.updateData(ArrayList())
            else
                listAdapter.updateData(item[0].lfs)
            dataLoading.set(false)
        })


    }

    override fun onStop(owner: LifecycleOwner) {
        meaningMutableLiveData.removeObservers(owner)
    }

    fun findAbbreviation(abbr: String) {
        errorMsg.set("")
        dataLoading.set(true)
        listDataRepository!!.getListDataFromServer(abbr, meaningMutableLiveData) { error ->
            dataLoading.set(false)
            errorMsg.set(error)
        }
    }

    fun getListAdapter(): DictionaryMeaningListAdapter = listAdapter
}
