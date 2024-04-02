package com.example.photodemoplus;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.photodemoplus.databinding.ActivityDetailBinding;
import com.example.photodemoplus.viewModel.DetailViewModel;
import com.example.photodemoplus.viewModel.MainViewModel;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDetailBinding detailBinding = DataBindingUtil.setContentView(this,R.layout.activity_detail);
        DetailViewModel detailViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(DetailViewModel.class);
        detailBinding.setViewmodel(detailViewModel);
        detailBinding.setLifecycleOwner(this);

        Intent intent = getIntent();
        if (intent!=null){
            detailViewModel.setPhotoDescription(intent.getStringExtra("photoDescription"));
            detailViewModel.setPhotoId(intent.getStringExtra("photoId"));
            detailViewModel.setPhotoUrl(intent.getStringExtra("photoUrl"));
        }

        detailViewModel.getBackPressedEvent().observe(this, aVoid -> onBackPressed());
    }
}
