package com.example.cmathew.forestmoon.bfs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cmathew.forestmoon.GraphBuilder;
import com.example.cmathew.forestmoon.R;

import java.io.IOException;
import java.io.InputStream;

public class BfsFragment extends Fragment {
    private static final String LOG_TAG = "Sailor";

    private Boolean[][] connections;

    private TextView sailorOutcome;

    public BfsFragment() {
        // Required empty public constructor
    }

    public static BfsFragment newInstance() {
        BfsFragment fragment = new BfsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        InputStream csvStream = null;
        try {
            try {
                GraphBuilder graphBuilder = new GraphBuilder();
                csvStream = getResources().openRawResource(R.raw.undirected_graph_1);
                this.connections = graphBuilder.parseCsv(csvStream);
            } finally {
                if (csvStream != null) {
                    csvStream.close();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        BreadthFirstSearcher searcher = new BreadthFirstSearcher();
        if (!searcher.search(connections)) {
            sailorOutcome.setText("Islands detected.");
        } else {
            sailorOutcome.setText("Connected. You may travel freely!");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bfs, container, false);
        this.sailorOutcome = view.findViewById(R.id.sailor_scan_outcome);
        return view;
    }
}
