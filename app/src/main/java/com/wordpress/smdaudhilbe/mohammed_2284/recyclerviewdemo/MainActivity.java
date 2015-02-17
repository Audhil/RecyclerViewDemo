package com.wordpress.smdaudhilbe.mohammed_2284.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.wordpress.smdaudhilbe.adapters.RecyclerAdapter;
import com.wordpress.smdaudhilbe.model.OnRecyclerViewItemClickListener;
import com.wordpress.smdaudhilbe.model.ViewModel;

import java.util.ArrayList;
import java.util.List;

//  code available @ https://github.com/antoniolg/RecyclerViewExtensions
public class MainActivity extends ActionBarActivity {

    private static final String MOCK_URL = "http://lorempixel.com/800/400/nightlife/";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    public void initViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        final RecyclerAdapter adapter;
        recyclerView.setAdapter(adapter = new RecyclerAdapter(createMockList(), R.layout.item));
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter.setOnItemClickListener(new OnRecyclerViewItemClickListener<ViewModel>() {
            @Override
            public void onItemClick(View view, ViewModel viewModel) {
                adapter.remove(viewModel);
            }
        });
    }

    private List<ViewModel> createMockList() {
        List<ViewModel> items = new ArrayList<ViewModel>();
        for (int i = 0; i < 20; i++) {
            items.add(new ViewModel(i, "Item " + (i + 1), MOCK_URL + (i % 10 + 1)));
        }
        return items;
    }
}