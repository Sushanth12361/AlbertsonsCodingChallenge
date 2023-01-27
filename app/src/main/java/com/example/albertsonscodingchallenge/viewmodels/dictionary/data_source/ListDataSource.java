package com.example.albertsonscodingchallenge.viewmodels.dictionary.data_source;

import androidx.lifecycle.MutableLiveData;

import com.example.albertsonscodingchallenge.model.DictionaryMeaning;
import com.example.albertsonscodingchallenge.utils.IFailureCallback;

import java.util.List;

public interface ListDataSource {

    default void getListDataFromServer(String sf, MutableLiveData<List<DictionaryMeaning>> liveDataList, IFailureCallback failureCallback) {
    }

    //repository methods

}
