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
        columns = new HashMap<>();
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
        Set<String> labels = columns.keySet();
        for (String label : labels) {
            System.out.print(label + "\t");
        }
        System.out.println();

        int rowCount = getRowCount();
        for (int i = 0; i < rowCount; i++) {
            for (String label : labels) {
                Series<?> series = columns.get(label);
                System.out.print(series.get(i) + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Affiche les premières lignes du DataFrame.
     *
     * @param n Le nombre de lignes à afficher depuis le haut.
     */
    public void printHead(int n) {
        Set<String> labels = columns.keySet();
        for (String label : labels) {
            System.out.print(label + "\t");
        }
        System.out.println();

        int rowCount = Math.min(n, getRowCount());
        for (int i = 0; i < rowCount; i++) {
            for (String label : labels) {
                Series<?> series = columns.get(label);
                System.out.print(series.get(i) + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Affiche les dernières lignes du DataFrame.
     *
     * @param n Le nombre de lignes à afficher depuis le bas.
     */
    public void printTail(int n) {
        Set<String> labels = columns.keySet();
        for (String label : labels) {
            System.out.print(label + "\t");
        }
        System.out.println();

        int rowCount = getRowCount();
        int start = Math.max(0, rowCount - n);
        for (int i = start; i < rowCount; i++) {
            for (String label : labels) {
                Series<?> series = columns.get(label);
                System.out.print(series.get(i) + "\t");
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
}