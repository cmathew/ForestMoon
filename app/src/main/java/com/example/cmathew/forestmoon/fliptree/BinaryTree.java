package com.example.cmathew.forestmoon.fliptree;

import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    private static final String LOG_TAG = "Christmas";

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

    public int getMaxDepth() {
        return getDepth(rootNode);
    }

    // 'Depth' meaning distance from the leaves (i.e. not from the root)
    public int getDepth(TreeNode currentNode) {
        if (currentNode == null) {
            return 0;
        }

        int leftDepth = getDepth(currentNode.getLeftChild());
        int rightDepth = getDepth(currentNode.getRightChild());

        return Math.max(leftDepth, rightDepth) + 1;
    }

    private int getPrintWidth() {
        return (int) Math.pow(getMaxDepth(), 2) * 2;
    }

    /*
    public void print() {
        printLevel(rootNode, 1);
    }

    private void printLevel(TreeNode currentNode, int level) {
        int printSize = getPrintWidth();
        int numbersOnLevel = (int) Math.pow(level, 2);
        int spacesPerNumber = printSize / numbersOnLevel;
    }
    */

    public void printLevels() {
        List<TreeNode> thisLevel = new ArrayList<>();
        if (rootNode != null) {
            thisLevel.add(rootNode);
        }

        while (!thisLevel.isEmpty()) {
            List<TreeNode> nextLevel = new ArrayList<>();

            String levelOut = "";
            for (TreeNode node : thisLevel) {
                levelOut += String.format("%d ", node.getValue());

                TreeNode leftChild = node.getLeftChild();
                if (leftChild != null) {
                    nextLevel.add(leftChild);
                }

                TreeNode rightChild = node.getRightChild();
                if (rightChild != null) {
                    nextLevel.add(rightChild);
                }
            }
            Log.v(LOG_TAG, levelOut);

            thisLevel = nextLevel;
        }
    }

    public void printPreorderDfs(TreeNode node) {
        if (node == null) {
            return;
        }

        Log.v(LOG_TAG, String.valueOf(node.getValue()));
        printPreorderDfs(node.getLeftChild());
        printPreorderDfs(node.getRightChild());
    }

    public void printPostorderDfs(TreeNode node) {
        if (node == null) {
            return;
        }

        printPostorderDfs(node.getLeftChild());
        printPostorderDfs(node.getRightChild());
        Log.v(LOG_TAG, String.valueOf(node.getValue()));
    }

    public void printInorderDfs(TreeNode node) {
        if (node == null) {
            return;
        }

        printInorderDfs(node.getLeftChild());
        Log.v(LOG_TAG, String.valueOf(node.getValue()));
        printInorderDfs(node.getRightChild());
    }
}
