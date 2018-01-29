package com.example.cmathew.forestmoon.bfs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cmathew.forestmoon.GraphBuilder;
import com.example.cmathew.forestmoon.GraphConnectionAdapter;
import com.example.cmathew.forestmoon.R;

import java.io.IOException;
import java.io.InputStream;

public class BfsFragment extends Fragment {
    private static final String LOG_TAG = "Sailor";

    private boolean[][] connections;

    private TextView sailorOutcome;
    private RecyclerView graphMatrixView;

    private GraphConnectionAdapter connectionAdapter;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        this.connectionAdapter = new GraphConnectionAdapter(connections);
    }

    @Override
    public void onStart() {
        super.onStart();

        BreadthFirstSearcher searcher = new BreadthFirstSearcher();
        if (!searcher.search(connections)) {
            sailorOutcome.setText(R.string.bfs_result_graph_islands);
        } else {
            sailorOutcome.setText(R.string.bfs_result_connected_graph);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bfs, container, false);
        this.sailorOutcome = view.findViewById(R.id.sailor_scan_outcome);
        this.graphMatrixView = view.findViewById(R.id.graph_matrix_view);

        GridLayoutManager gridMgr = new GridLayoutManager(getContext(), connections.length);
        gridMgr.setOrientation(LinearLayoutManager.VERTICAL);
        graphMatrixView.setLayoutManager(gridMgr);
        graphMatrixView.setAdapter(connectionAdapter);

        return view;
    }
}
