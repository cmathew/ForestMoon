package com.example.cmathew.forestmoon.bfs;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BreadthFirstSearcher {
    private static final String LOG_TAG = "BreadCrumb";
    private boolean[] isNodeVisited;

    public BreadthFirstSearcher() {

    }

    /**
     * @param  nodeConnections  Node ID x Node ID matrix
     * @return Mapping of node visitations, indexed by node ID
     */
    public boolean search(boolean[][] nodeConnections) {
        this.isNodeVisited = new boolean[nodeConnections.length];
        for (int i = 0; i < isNodeVisited.length; i++) {
            isNodeVisited[i] = false;
        }

        return bfs(nodeConnections, 0);
    }

    /**
     * @param  nodeConnections  Node ID x Node ID matrix
     * @param  currentNodeIndex Current node ID in the traversal
     * @return Mapping of node visitations, indexed by node ID
     */
    private boolean bfs(boolean[][] nodeConnections, int currentNodeIndex) {
        // Cycle detected
        if (isNodeVisited[currentNodeIndex]) {
            return false;
        }

        Log.i(LOG_TAG, String.format("Visited Node ID #%d", currentNodeIndex + 1));
        isNodeVisited[currentNodeIndex] = true;

        // Return if all visited
        boolean allVisited = true;
        for (boolean visited : isNodeVisited) {
            allVisited = allVisited && visited;
        }
        if (allVisited) {
            return true;
        }

        boolean[] currentNodeConnections = nodeConnections[currentNodeIndex];
        for (int i = 0; i < currentNodeConnections.length; i++) {
            if (currentNodeConnections[i]) {
                if (bfs(nodeConnections, i)) {
                    return true;
                }
            }
        }

        return false;
    }
}
