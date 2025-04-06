package com.ilian.dataframe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe utilitaire permettant de charger un fichier CSV
 * et de le convertir en un objet {@link DataFrame}.
 */
public class CsvLoader {

    /**
     * Charge un fichier CSV depuis le chemin spécifié et le convertit en {@link DataFrame}.
     * <p>
     * La première ligne du fichier est considérée comme contenant les noms de colonnes.
     * Chaque ligne suivante est interprétée comme une ligne de données.
     * </p>
     *
     * @param filePath le chemin vers le fichier CSV
     * @return un objet {@link DataFrame} contenant les données chargées
     * @throws IOException si une erreur d'entrée/sortie se produit lors de la lecture du fichier
     */
    public static DataFrame load(String filePath) throws IOException {
        List<Series<String>> seriesList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String headerLine = br.readLine();
            if (headerLine == null) {
                throw new IOException("Le fichier est vide.");
            }

            String[] headers = headerLine.split(",");
            for (String header : headers) {
                seriesList.add(new Series<>(header, new ArrayList<>()));
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                for (int i = 0; i < values.length; i++) {
                    seriesList.get(i).getValues().add(values[i]);
                }
            }
        }

        return new DataFrame(new ArrayList<>(seriesList));
    }
}
