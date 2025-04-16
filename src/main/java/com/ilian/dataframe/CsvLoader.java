package com.ilian.dataframe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Classe utilitaire permettant de charger un fichier CSV
 * et de le convertir en un objet {@link DataFrame}.
 */
public class CsvLoader {

    /**
     * Charge un fichier CSV depuis le chemin spécifié et le convertit en {@link DataFrame}.
     * Détecte automatiquement le type de chaque colonne (Integer, Double ou String).
     *
     * @param filePath le chemin vers le fichier CSV
     * @return un objet {@link DataFrame} contenant les données chargées
     * @throws IOException si une erreur d'entrée/sortie se produit lors de la lecture du fichier
     */
    public static DataFrame load(String filePath) throws IOException {
        List<String[]> allRows = new ArrayList<>();
        String[] headers;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine();
            if (headerLine == null) {
                throw new IOException("Le fichier est vide.");
            }

            headers = headerLine.split(",");

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",", -1); // -1 to keep empty strings
                allRows.add(values);
            }
        }

        int columnCount = headers.length;
        List<List<String>> columnsRaw = new ArrayList<>();
        for (int i = 0; i < columnCount; i++) {
            columnsRaw.add(new ArrayList<>());
        }

        for (String[] row : allRows) {
            for (int i = 0; i < columnCount; i++) {
                if (i < row.length) {
                    columnsRaw.get(i).add(row[i]);
                } else {
                    columnsRaw.get(i).add("");
                }
            }
        }

        List<Series<?>> typedSeriesList = new ArrayList<>();

        for (int i = 0; i < columnCount; i++) {
            String label = headers[i];
            List<String> values = columnsRaw.get(i);
            Series<?> typedSeries = tryInferSeries(label, values);
            typedSeriesList.add(typedSeries);
        }

        return new DataFrame(typedSeriesList);
    }

    private static Series<?> tryInferSeries(String label, List<String> values) {
        boolean isInteger = true;
        boolean isDouble = true;

        for (String v : values) {
            if (!v.matches("-?\\d+")) {
                isInteger = false;
            }
            if (!v.matches("-?\\d+(\\.\\d+)?")) {
                isDouble = false;
            }
        }

        if (isInteger) {
            List<Integer> intValues = new ArrayList<>();
            for (String v : values) {
                intValues.add(Integer.parseInt(v));
            }
            return new Series<>(label, intValues);
        } else if (isDouble) {
            List<Double> doubleValues = new ArrayList<>();
            for (String v : values) {
                doubleValues.add(Double.parseDouble(v));
            }
            return new Series<>(label, doubleValues);
        } else {
            return new Series<>(label, values);
        }
    }
}