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
    
    /**
     * Affiche les premières lignes du DataFrame dans la console.
     *
     * Cette méthode affiche les étiquettes de colonnes, suivies des {@code n} premières lignes
     * du tableau. Elle est utile pour visualiser un aperçu rapide des données en tête de DataFrame.
     *
     * @param n Le nombre de lignes à afficher à partir du début.
     *          Si {@code n} dépasse le nombre total de lignes, toutes les lignes sont affichées.
    */
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
    

    /**
     * Affiche les dernières lignes du DataFrame dans la console.
     *
     * Cette méthode affiche les étiquettes de colonnes, suivies des {@code n} dernières lignes
     * du tableau. Elle est utile pour visualiser la fin du DataFrame, souvent après un tri ou un traitement.
     *
     * @param n Le nombre de lignes à afficher à partir de la fin.
     *          Si {@code n} dépasse le nombre total de lignes, toutes les lignes sont affichées.
    */
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


    /**
     * Sélectionne un sous-ensemble de lignes du DataFrame.
     *
     * Cette méthode extrait les lignes comprises entre {@code fromIndex} (inclus) et {@code toIndex} (exclus),
     * pour toutes les colonnes. Elle est utile pour découper une partie du DataFrame sans modifier l'original.
     *
     * @param fromIndex L’index de début (inclus).
     * @param toIndex   L’index de fin (exclu).
     * @return Un nouveau DataFrame contenant uniquement les lignes sélectionnées.
     * @throws IllegalArgumentException si les index sont invalides (négatifs, hors bornes ou mal ordonnés).
    */
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

    /**
     * Sélectionne un sous-ensemble de colonnes du DataFrame selon leurs étiquettes.
     *
     * @param labels Les étiquettes des colonnes à sélectionner.
     * @return Un nouveau DataFrame ne contenant que les colonnes spécifiées.
     * @throws NullPointerException si une étiquette ne correspond à aucune colonne existante.
    */
    public DataFrame selectColumns(String... labels) {
        List<Series<?>> selected = new ArrayList<>();
        for (String label : labels) {
            selected.add(getColumn(label));
        }
        return new DataFrame(selected);
    }
    
    /**
     * Calcule des statistiques de base (count, mean, min, max) sur une colonne numérique.
     *
     * @param label Le nom de la colonne à analyser.
     * @return Une map contenant les statistiques, ou null si la colonne n'est pas numérique ou introuvable.
     */
    public Map<String, Double> getStatistics(String label) {
        Series<?> series = columns.get(label);
        if (series == null) return null;

        List<?> values = series.getValues();
        if (values.isEmpty() || !(values.get(0) instanceof Number)) {
            return null; // non numérique
        }

        double sum = 0;
        double min = Double.MAX_VALUE;
        double max = -Double.MAX_VALUE;
        int count = 0;

        for (Object obj : values) {
            if (!(obj instanceof Number)) continue;
            double val = ((Number) obj).doubleValue();
            sum += val;
            min = Math.min(min, val);
            max = Math.max(max, val);
            count++;
        }

        if (count == 0) return null;

        Map<String, Double> stats = new HashMap<>();
        stats.put("count", (double) count);
        stats.put("mean", sum / count);
        stats.put("min", min);
        stats.put("max", max);
        return stats;
    }
}