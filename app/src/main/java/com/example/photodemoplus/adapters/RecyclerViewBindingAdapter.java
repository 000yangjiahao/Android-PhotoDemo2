package com.example.photodemoplus.adapters;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photodemoplus.entity.UnsplashPhoto;

import java.util.List;

public class RecyclerViewBindingAdapter {
    @BindingAdapter("items")
    public static void setItems(RecyclerView recyclerView, List<UnsplashPhoto> items) {
        if (recyclerView.getAdapter() instanceof RecyclerAdapter) {
            RecyclerAdapter adapter = (RecyclerAdapter) recyclerView.getAdapter();
            adapter.updateData(items);
        }
    }
}
