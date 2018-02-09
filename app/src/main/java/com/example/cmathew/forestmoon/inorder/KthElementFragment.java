package com.example.cmathew.forestmoon.inorder;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cmathew.forestmoon.BinaryTree;
import com.example.cmathew.forestmoon.R;
import com.example.cmathew.forestmoon.TreeNode;

public class KthElementFragment extends Fragment {
    private EditText kthValueEntry;
    private Button kthElementButton;
    private TextView kthTraversalResult;
    private BinaryTree christmas;

    public KthElementFragment() {
        // Required empty public constructor
    }

    public static KthElementFragment newInstance() {
        KthElementFragment fragment = new KthElementFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //       4
        //   2       7
        // 1   3   6   9
        this.christmas = new BinaryTree();
        int[] values = new int[] { 4, 2, 7, 1, 3, 6, 9 };
        for (int value : values) {
            christmas.add(value);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_kth_element, container, false);
        this.kthValueEntry = view.findViewById(R.id.kth_order_entry);
        this.kthElementButton = view.findViewById(R.id.find_kth_element);
        this.kthTraversalResult = view.findViewById(R.id.kth_inorder_result);
        kthElementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int k = Integer.parseInt(kthValueEntry.getText().toString());
                TreeNode kth = christmas.findKth(k);
                String result = String.valueOf(kth.getValue());
                kthTraversalResult.setText(result);
            }
        });

        return view;
    }

}
