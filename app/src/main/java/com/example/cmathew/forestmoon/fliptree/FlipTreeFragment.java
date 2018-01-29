package com.example.cmathew.forestmoon.fliptree;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cmathew.forestmoon.R;

public class FlipTreeFragment extends Fragment {

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

        BinaryTree christmas = new BinaryTree();
        int[] values = new int[] { 4, 2, 7, 1, 3, 6, 9 };
        for (int value : values) {
            TreeNode node = new TreeNode(value);
            christmas.addNode(node);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flip_tree, container, false);
    }

}
