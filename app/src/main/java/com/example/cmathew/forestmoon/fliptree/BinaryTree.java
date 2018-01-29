package com.example.cmathew.forestmoon.fliptree;

public class BinaryTree {
    private TreeNode rootNode;

    public BinaryTree() {

    }

    public void addNode(TreeNode node) {
        TreeNode currentNode = rootNode;
        while (currentNode != null) {
            if (node.getValue() <= currentNode.getValue()) {
                TreeNode leftChild = currentNode.getLeftChild();
                if (leftChild == null) {
                    currentNode.setLeftChild(node);
                    return;
                } else {
                    currentNode = leftChild;
                }
            } else if (node.getValue() > currentNode.getValue()) {
                TreeNode rightChild = currentNode.getRightChild();
                if (rightChild == null) {
                    currentNode.setRightChild(node);
                    return;
                } else {
                    currentNode = rightChild;
                }
            }
        }
    }
}
