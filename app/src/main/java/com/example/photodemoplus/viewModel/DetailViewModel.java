package com.example.photodemoplus.viewModel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.photodemoplus.MainActivity;
import com.example.photodemoplus.mvvm.ObservableViewModel;

public class DetailViewModel extends ObservableViewModel {
    private String photoId;
    private String photoDescription;
    private String photoUrl;
    private MutableLiveData<Void> backPressedEvent = new MutableLiveData<>();

    public LiveData<Void> getBackPressedEvent() {
        return backPressedEvent;
    }

    public void setPhotoDescription(String photoDescription) {
        this.photoDescription = photoDescription;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoDescription() {
        return photoDescription;
    }

    public String getPhotoId() {
        return photoId;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    public void returnList() {
        backPressedEvent.setValue(null);
    }
}
