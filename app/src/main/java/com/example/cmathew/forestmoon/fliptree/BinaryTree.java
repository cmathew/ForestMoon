package com.example.cmathew.forestmoon.fliptree;

import android.support.annotation.Nullable;

public class BinaryTree {
    @Nullable
    private TreeNode rootNode;

    public BinaryTree() {

    }

    public TreeNode getRootNode() {
        return rootNode;
    }

    public void add(int value) {
        this.rootNode = addNode(rootNode, value);
    }

    // Return the modified tree
    private TreeNode addNode(TreeNode currentNode, int value) {
        if (currentNode == null) {
            currentNode = new TreeNode(value);
            return currentNode;
        }

        if (value <= currentNode.getValue()) {
            TreeNode leftChild = currentNode.getLeftChild();
            currentNode.setLeftChild(addNode(leftChild, value));
        } else if (value > currentNode.getValue()) {
            TreeNode rightChild = currentNode.getRightChild();
            currentNode.setRightChild(addNode(rightChild, value));
        }

        return currentNode;
    }

    public void flip() {
        flipTree(rootNode);
    }

    private void flipTree(TreeNode currentNode) {
        if (currentNode == null) {
            return;
        }

        TreeNode leftChild = currentNode.getLeftChild();
        TreeNode rightChild = currentNode.getRightChild();

        currentNode.setLeftChild(rightChild);
        currentNode.setRightChild(leftChild);

        flipTree(leftChild);
        flipTree(rightChild);
    }
}
