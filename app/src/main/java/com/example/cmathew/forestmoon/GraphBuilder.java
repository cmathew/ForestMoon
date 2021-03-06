package com.example.cmathew.forestmoon;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class GraphBuilder {
    public boolean[][] parseCsv(InputStream csvStream) throws IOException {
        CSVParser csvParser = CSVParser.parse(
                csvStream,
                Charset.forName("UTF-8"),
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
        );

        List<List<Boolean>> graphConnectionList = new ArrayList<>();
        Iterable<CSVRecord> csvRecords = csvParser.getRecords();
        for (CSVRecord csvRecord : csvRecords) {
            List<Boolean> nodeConnectionList = new ArrayList<>();
            for (String record : csvRecord) {
                boolean value = Integer.parseInt(record) == 1;
                nodeConnectionList.add(value);
            }

            graphConnectionList.add(nodeConnectionList);
        }

        boolean[][] graphArray = new boolean[graphConnectionList.size()][graphConnectionList.size()];
        for (int i = 0; i < graphConnectionList.size(); i++) {
            List<Boolean> nodeConnectionList = graphConnectionList.get(i);
            for (int j = 0; j < nodeConnectionList.size(); j++) {
                graphArray[i][j] = nodeConnectionList.get(j);
            }
        }

        return graphArray;
    }
}
