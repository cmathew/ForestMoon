package com.example.cmathew.forestmoon.fliptree;

import android.support.annotation.Nullable;

public class TreeNode {
    private int value;

    @Nullable
    private TreeNode leftChild;
    @Nullable
    private TreeNode rightChild;

    public TreeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Nullable
    public TreeNode getLeftChild() {
        return leftChild;
    }

    @Nullable
    public TreeNode getRightChild() {
        return rightChild;
    }

    public void setLeftChild(@Nullable TreeNode leftChild) {
        this.leftChild = leftChild;
    }

    public void setRightChild(@Nullable TreeNode rightChild) {
        this.rightChild = rightChild;
    }
}
