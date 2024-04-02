package com.example.photodemoplus.viewModel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;

import com.example.photodemoplus.DetailActivity;
import com.example.photodemoplus.entity.UnsplashPhoto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.orhanobut.logger.Logger;
import com.example.photodemoplus.mvvm.ObservableViewModel;
import com.example.photodemoplus.BR;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainViewModel extends ObservableViewModel {
    private static final String BASE_URL = "https://api.unsplash.com/photos/random?client_id=D1iV9IoCJ3l76N23H7DpV3hfmBCpu0LPUDw0U734_0Y&count=10";
    @Bindable
    private String query;
    @Bindable
    private List<UnsplashPhoto> photoListLiveData=new ArrayList<>() ;

    OkHttpClient client=new OkHttpClient();

    private final Gson gson;
    public MainViewModel(@NonNull Application application) {
        super(application);
        gson = new Gson();
        loadImage();
    }

    public void setPhotoListLiveData(List<UnsplashPhoto> photoListLiveData) {
        this.photoListLiveData = photoListLiveData;
        notifyPropertyChanged(BR.photoListLiveData);
    }

    public List<UnsplashPhoto> getPhotoListLiveData() {
        return photoListLiveData;
    }

    public void setQuery(String query) {
        this.query = query;
        notifyPropertyChanged(BR.query);
    }

    public String getQuery() {
        return query;
    }

    public void loadImage(){
        String finalUrl = BASE_URL;
        if (query != null && !query.isEmpty()) {
            finalUrl += "&query=" + query;
        }

        Request request=new Request.Builder()
                .url(finalUrl)
                .get()
                .build();
        Call call= client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Logger.e("图片加载失败",e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try( ResponseBody responseBody = response.body() ) {
                    if (response.isSuccessful() && responseBody != null) {
                        String json = responseBody.string();
                        List<UnsplashPhoto> photos = gson.fromJson(json, new TypeToken<List<UnsplashPhoto>>(){}.getType());
                        setPhotoListLiveData(photos);
                        Logger.i("photos",photos);
                    }
                }
            }
        });
    }

    public void onDetailButtonClicked(UnsplashPhoto photo){
        Context context =getApplication().getApplicationContext();
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra("photoId", photo.getId());
        intent.putExtra("photoDescription", photo.getAlt_description());
        intent.putExtra("photoUrl", photo.getUrls().getRaw());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
