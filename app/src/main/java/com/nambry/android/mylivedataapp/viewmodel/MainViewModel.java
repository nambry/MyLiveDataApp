package com.nambry.android.mylivedataapp.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.nambry.android.mylivedataapp.repository.MainRepository;

public class MainViewModel extends ViewModel {

    /** 外部から変更可能なLiveData */
    MutableLiveData<String> liveData = new MutableLiveData<>();

    public MutableLiveData<String> getLiveData() {
        return this.liveData;
    }

    public void fetchText() {
        MainRepository repository = new MainRepository();
        liveData.setValue(repository.fetchText());
    }
}
