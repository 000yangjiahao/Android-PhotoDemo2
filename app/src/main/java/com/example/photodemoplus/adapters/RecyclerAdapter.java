package com.example.photodemoplus.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.photodemoplus.DetailActivity;
import com.example.photodemoplus.R;
import com.example.photodemoplus.entity.UnsplashPhoto;
import com.example.photodemoplus.viewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.PhotoViewHolder> {

    private final List<UnsplashPhoto> photoList;
    private final MainViewModel mainViewModel;
    public RecyclerAdapter(MainViewModel mainViewModel) {
        this.photoList = new ArrayList<>();
        this.mainViewModel = mainViewModel;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void updateData(List<UnsplashPhoto> newData) {
        photoList.clear();
        photoList.addAll(newData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.recycler_item, parent, false);
        return new PhotoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        UnsplashPhoto photo = photoList.get(position);
        ViewDataBinding binding = DataBindingUtil.getBinding(holder.itemView);
        assert binding != null;
        binding.setVariable(BR.photo, photo);
        binding.setVariable(BR.viewModel,mainViewModel);
        binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return photoList.size();
    }

    public class PhotoViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        public PhotoViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

    }
}
