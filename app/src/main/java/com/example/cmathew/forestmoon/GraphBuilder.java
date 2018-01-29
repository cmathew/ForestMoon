package com.example.cmathew.forestmoon;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

public class GraphBuilder {
    public boolean[][] parseCsv(InputStream csvStream) throws IOException {
        boolean[][] connections = new boolean[5][5];

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
