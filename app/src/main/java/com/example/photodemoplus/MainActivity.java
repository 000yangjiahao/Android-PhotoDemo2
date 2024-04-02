package com.example.photodemoplus;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.photodemoplus.adapters.RecyclerAdapter;
import com.example.photodemoplus.databinding.ActivityMainBinding;
import com.example.photodemoplus.viewModel.MainViewModel;
import com.orhanobut.logger.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        MainViewModel mainViewModel = new ViewModelProvider(this,
                ViewModelProvider.AndroidViewModelFactory.getInstance(this.getApplication()))
                .get(MainViewModel.class);
        mainBinding.setViewModel(mainViewModel);
        mainBinding.setLifecycleOwner(this);

        RecyclerView recycler = mainBinding.recyclerView;
        recycler.setLayoutManager(new LinearLayoutManager(this));
        RecyclerAdapter adapter = new RecyclerAdapter( mainViewModel);
        recycler.setAdapter(adapter);

    }
}