package com.ilian.dataframe;

import java.util.*;

/**
 * Représente un tableau de données structuré, composé de colonnes (Series) identifiées par des étiquettes.
 */
public class DataFrame {
    private Map<String, Series<?>> columns;

    /**
     * Crée un DataFrame à partir d'une liste de séries.
     *
     * @param seriesList Liste des séries à intégrer dans le DataFrame.
     * @throws IllegalArgumentException si plusieurs séries ont des étiquettes identiques.
     */
    public DataFrame(List<Series<?>> seriesList) {
        columns = new LinkedHashMap<>();
        for (Series<?> series : seriesList) {
            String label = series.getLabel();
            if (columns.containsKey(label)) {
                throw new IllegalArgumentException("Duplicate column label: " + label);
            }
            columns.put(label, series);
        }
    }

    /**
     * Affiche l'intégralité du contenu du DataFrame dans la console.
     */
    public void printFull() {
        List<String> labels = new ArrayList<>(columns.keySet());
    
        // Print column headers
        for (int j = 0; j < labels.size(); j++) {
            System.out.print(labels.get(j));
            if (j < labels.size() - 1) System.out.print("\t");
        }
        System.out.println();
    
        // Print rows
        int rowCount = getRowCount();
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < labels.size(); j++) {
                Series<?> series = columns.get(labels.get(j));
                System.out.print(series.get(i));
                if (j < labels.size() - 1) System.out.print("\t");
            }
            System.out.println();
        }
    }
    
    public void printHead(int n) {
        List<String> labels = new ArrayList<>(columns.keySet());
    
        // Print column headers
        for (int j = 0; j < labels.size(); j++) {
            System.out.print(labels.get(j));
            if (j < labels.size() - 1) System.out.print("\t");
        }
        System.out.println();
    
        // Print rows
        int rowCount = Math.min(n, getRowCount());
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < labels.size(); j++) {
                Series<?> series = columns.get(labels.get(j));
                System.out.print(series.get(i));
                if (j < labels.size() - 1) System.out.print("\t");
            }
            System.out.println();
        }
    }
    
    public void printTail(int n) {
        List<String> labels = new ArrayList<>(columns.keySet());
    
        // Print column headers
        for (int j = 0; j < labels.size(); j++) {
            System.out.print(labels.get(j));
            if (j < labels.size() - 1) System.out.print("\t");
        }
        System.out.println();
    
        // Print rows
        int rowCount = getRowCount();
        int start = Math.max(0, rowCount - n);
        for (int i = start; i < rowCount; i++) {
            for (int j = 0; j < labels.size(); j++) {
                Series<?> series = columns.get(labels.get(j));
                System.out.print(series.get(i));
                if (j < labels.size() - 1) System.out.print("\t");
            }
            System.out.println();
        }
    }

    /**
     * Retourne le nombre de lignes dans le DataFrame.
     *
     * @return Le nombre de lignes.
     */
    public int getRowCount() {
        if (columns.isEmpty()) {
            return 0;
        }
        return columns.values().iterator().next().size();
    }

    /**
     * Retourne le nombre de colonnes dans le DataFrame.
     *
     * @return Le nombre de colonnes.
     */
    public int getColumnCount() {
        return columns.size();
    }

    /**
     * Retourne les étiquettes (labels) de toutes les colonnes du DataFrame.
     *
     * @return Un ensemble d’étiquettes de colonnes.
     */
    public Set<String> getColumnLabels() {
        return columns.keySet();
    }

    /**
     * Retourne la série correspondant à une étiquette donnée.
     *
     * @param label Le nom de la colonne souhaitée.
     * @return La série correspondante, ou null si l’étiquette n’existe pas.
     */
    public Series<?> getColumn(String label) {
        return columns.get(label);
    }

    /**
     * Construit un DataFrame à partir d’un fichier CSV.
     *
     * @param filePath Le chemin vers le fichier CSV.
     * @return Une instance de DataFrame initialisée avec les données du fichier.
     * @throws java.io.IOException si le fichier est introuvable ou invalide.
     */
    public static DataFrame fromCSV(String filePath) throws java.io.IOException {
        return CsvLoader.load(filePath);
    }

    public DataFrame selectRows(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > getRowCount() || fromIndex >= toIndex) {
            throw new IllegalArgumentException("Invalid row range");
        }
    
        List<Series<?>> newSeries = new ArrayList<>();
    
        for (Series<?> s : columns.values()) {
            List<?> subList = s.getValues().subList(fromIndex, toIndex);
            newSeries.add(new Series<>(s.getLabel(), new ArrayList<>(subList)));
        }
    
        return new DataFrame(newSeries);
    }    

    
    public DataFrame selectColumns(String... labels) {
        List<Series<?>> selected = new ArrayList<>();
        for (String label : labels) {
            selected.add(getColumn(label));
        }
        return new DataFrame(selected);
    }
    
    /**
    * Calcule les statistiques (min, max, moyenne) pour chaque colonne numérique du DataFrame.
    *
    * @return Une chaîne contenant les statistiques formatées pour chaque colonne numérique.
    */
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Series<?>> entry : columns.entrySet()) {
            String label = entry.getKey();
            Series<?> series = entry.getValue();

            // Vérifie si la série contient des valeurs numériques
            if (!series.getValues().isEmpty() && series.getValues().get(0) instanceof Number) {
                List<Number> values = (List<Number>) series.getValues();
                double sum = 0;
                double min = Double.MAX_VALUE;
                double max = -Double.MAX_VALUE;

                for (Number value : values) {
                    double v = value.doubleValue();
                    sum += v;
                    if (v < min) min = v;
                    if (v > max) max = v;
                }

                double mean = sum / values.size();
                sb.append(String.format("Colonne '%s' -> min: %.2f | max: %.2f | mean: %.2f\n", label, min, max, mean));
            }
        }
        return sb.toString();
    }
}