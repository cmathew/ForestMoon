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
    public Boolean[][] parseCsv(InputStream csvStream) throws IOException {
        CSVParser csvParser = CSVParser.parse(
                csvStream,
                Charset.forName("UTF-8"),
                CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim()
        );

        List<Boolean[]> graphConnectionList = new ArrayList<>();
        Iterable<CSVRecord> csvRecords = csvParser.getRecords();
        for (CSVRecord csvRecord : csvRecords) {
            List<Boolean> nodeConnectionList = new ArrayList<>();
            for (String record : csvRecord) {
                boolean value = Integer.parseInt(record) == 1;
                nodeConnectionList.add(value);
            }

            Boolean[] nodeConnectionArray = new Boolean[nodeConnectionList.size()];
            nodeConnectionArray = nodeConnectionList.toArray(nodeConnectionArray);
            graphConnectionList.add(nodeConnectionArray);
        }

        Boolean[][] graphArray = new Boolean[graphConnectionList.size()][graphConnectionList.size()];
        return graphConnectionList.toArray(graphArray);
    }
}
