package com.example.julia.dragdroptworecyclerviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity  {

    RecyclerView rvTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvTop = (RecyclerView) findViewById(R.id.rvTop);


        initTopRecyclerView();

    }

    private void initTopRecyclerView() {
        rvTop.setLayoutManager(new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false));

        List<String> topList = new ArrayList<>();
        topList.add("A");
        topList.add("B");
        topList.add("C");
        topList.add("D");
        topList.add("E");
        ListAdapter topListAdapter = new ListAdapter(topList);
        rvTop.setAdapter(topListAdapter);
        rvTop.setOnDragListener(topListAdapter.getDragInstance());
    }


}
