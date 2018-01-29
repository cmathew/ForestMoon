package com.example.cmathew.forestmoon.fliptree;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.cmathew.forestmoon.R;

public class FlipTreeFragment extends Fragment {
    private Button flipButton;
    private BinaryTree christmas;

    public FlipTreeFragment() {
        // Required empty public constructor
    }

    public static FlipTreeFragment newInstance() {
        FlipTreeFragment fragment = new FlipTreeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.christmas = new BinaryTree();
        int[] values = new int[] { 4, 2, 7, 1, 3, 6, 9 };
        for (int value : values) {
            christmas.add(value);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flip_tree, container, false);
        this.flipButton = view.findViewById(R.id.flip_tree_button);
        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                christmas.flip();
            }
        });
        return view;
    }

}
