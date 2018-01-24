package com.example.cmathew.forestmoon;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class BreadthFirstSearcher {
    private static final String LOG_TAG = "BreadCrumb";

    // Node ID x Node ID matrix
    // Index of current node
    // Indexed by Node IDs
    public void bfs(boolean[][] nodeConnections, int currentNodeIndex, boolean[] visited) {
        // Cycle detected
        if (visited[currentNodeIndex]) {
            return;
        }

        // Return if all visited
        boolean allVisited = true;
        for (int i = 0; i < visited.length; i++) {
            allVisited = allVisited && visited[i];
        }
        if (allVisited) {
            return;
        }

        Log.i(LOG_TAG, String.format("Visited node %d", currentNodeIndex + 1));
        visited[currentNodeIndex] = true;

        //List<Integer> neighborIds = new ArrayList<>();
        boolean[] currentNodeConnections = nodeConnections[currentNodeIndex];
        for (int i = 0; i < currentNodeConnections.length; i++) {
            if (currentNodeConnections[i]) {
                bfs(nodeConnections, i, visited);
            }
        }
    }
}
