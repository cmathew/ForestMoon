package com.example.cmathew.forestmoon;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.*;

public class MainActivity extends AppCompatActivity {
    private boolean[][] connections;
    private boolean[] seenJamrock;

    private static final String LOG_TAG = "Sailor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            this.connections = parseCsv();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        this.seenJamrock = new boolean[5];
        for (int i = 0; i < seenJamrock.length; i++) {
            seenJamrock[i] = false;
        }

        BreadthFirstSearcher searcher = new BreadthFirstSearcher();
        searcher.bfs(connections, 0, seenJamrock);

        boolean allVisited = true;
        for (int i = 0; i < seenJamrock.length; i++) {
            allVisited = allVisited && seenJamrock[i];
        }

        TextView outcome = findViewById(R.id.sailor_scan_outcome);
        if (!allVisited) {
            outcome.setText("Islands detected.");
        } else {
            outcome.setText("Connected. You may travel freely!");
        }
    }

    private boolean[][] parseCsv() throws IOException {
        boolean[][] connections = new boolean[5][5];

        InputStream csvStream = getResources().openRawResource(R.raw.undirected_graph);
        CSVParser csvParser = CSVParser.parse(
                csvStream,
                Charset.forName("UTF-8"),
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
        );

        Iterable<CSVRecord> csvRecords = csvParser.getRecords();
        for (CSVRecord csvRecord : csvRecords) {
            // Accessing values by Header names
            int nodeId = Integer.parseInt(csvRecord.get("node_name")) - 1;
            boolean connected1 = Integer.parseInt(csvRecord.get("connected_1")) == 1;
            boolean connected2 = Integer.parseInt(csvRecord.get("connected_2")) == 1;
            boolean connected3 = Integer.parseInt(csvRecord.get("connected_3")) == 1;
            boolean connected4 = Integer.parseInt(csvRecord.get("connected_4")) == 1;
            boolean connected5 = Integer.parseInt(csvRecord.get("connected_5")) == 1;

            connections[nodeId][0] = connected1;
            connections[nodeId][1] = connected2;
            connections[nodeId][2] = connected3;
            connections[nodeId][3] = connected4;
            connections[nodeId][4] = connected5;
        }

        return connections;
    }
}
