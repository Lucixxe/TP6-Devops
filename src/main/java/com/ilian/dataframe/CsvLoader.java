package com.ilian.dataframe;

import java.io.*;
import java.util.*;

public class CsvLoader {

    public static DataFrame load(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String headerLine = reader.readLine();

        if (headerLine == null) {
            throw new IOException("Fichier CSV vide.");
        }

        String[] headers = headerLine.split(",");
        List<List<String>> columns = new ArrayList<>();

        for (int i = 0; i < headers.length; i++) {
            columns.add(new ArrayList<>());
        }

        String line;
        while ((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            for (int i = 0; i < headers.length; i++) {
                columns.get(i).add(values[i]);
            }
        }

        reader.close();

        List<Series<String>> seriesList = new ArrayList<>();
        for (int i = 0; i < headers.length; i++) {
            seriesList.add(new Series<>(headers[i], columns.get(i)));
        }

        return new DataFrame(new ArrayList<>(seriesList));
    }
}
