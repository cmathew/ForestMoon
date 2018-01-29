package com.example.cmathew.forestmoon;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.cmathew.forestmoon.bfs.BreadthFirstSearcher;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class BreadthFirstSearcherTest {
    @Test
    public void checkIsConnected() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();

        GraphBuilder graphBuilder = new GraphBuilder();
        InputStream csvStream = appContext.getResources().openRawResource(R.raw.undirected_graph_1);
        boolean[][] connections = graphBuilder.parseCsv(csvStream);
        BreadthFirstSearcher searcher = new BreadthFirstSearcher();
        boolean isConnected = searcher.search(connections);
        csvStream.close();

        assertTrue(isConnected);
    }
}