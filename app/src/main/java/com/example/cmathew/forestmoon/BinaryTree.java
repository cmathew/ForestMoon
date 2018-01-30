package com.example.cmathew.forestmoon;

import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

    private TreeNode addNode(TreeNode currentRoot, int value) {
        if (currentRoot == null) {
            return new TreeNode(value);
        }

        if (value > currentRoot.getValue()) {
            TreeNode rightChild = currentRoot.getRightChild();
            TreeNode alteredRightRoot = addNode(rightChild, value);
            currentRoot.setRightChild(alteredRightRoot);
        } else {
            TreeNode leftChild = currentRoot.getLeftChild();
            TreeNode alteredLeftRoot = addNode(leftChild, value);
            currentRoot.setLeftChild(alteredLeftRoot);
        }

        return currentRoot;
    }

    public void flip() {
        this.rootNode = flipTree(rootNode);
    }

    private TreeNode flipTree(TreeNode currentRoot) {
        if (currentRoot == null) {
            return null;
        }

        TreeNode leftChild = currentRoot.getLeftChild();
        TreeNode rightChild = currentRoot.getRightChild();

        TreeNode alteredLeftRoot = flipTree(leftChild);
        TreeNode alteredRightRoot = flipTree(rightChild);
        currentRoot.setLeftChild(alteredRightRoot);
        currentRoot.setRightChild(alteredLeftRoot);

        return currentRoot;
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

    public void printInorderDfsRecursive() {
        printInorderDfs(rootNode);
    }

    public void printInorderDfs(TreeNode node) {
        if (node == null) {
            return;
        }

        printInorderDfs(node.getLeftChild());
        Log.v(LOG_TAG, String.valueOf(node.getValue()));
        printInorderDfs(node.getRightChild());
    }

    // for a given root we want to enqueue ourselves
    // and enqueue all left children atop us
    private void createInorderStackForNode(TreeNode node, Stack<TreeNode> stack) {
        TreeNode currentLeftRoot = node;
        while (currentLeftRoot != null) {
            stack.push(currentLeftRoot);
            currentLeftRoot = currentLeftRoot.getLeftChild();
        }
    }

    public void printInorderDfsIterative() {
        Stack<TreeNode> stack = new Stack<>();
        createInorderStackForNode(rootNode, stack);

        while (!stack.isEmpty()) {
            // Pop a node, create a call stack from it
            TreeNode current = stack.pop();
            Log.d(LOG_TAG, String.valueOf(current.getValue()));

            TreeNode currentRightRoot = current.getRightChild();
            createInorderStackForNode(currentRightRoot, stack);
        }
    }
}
