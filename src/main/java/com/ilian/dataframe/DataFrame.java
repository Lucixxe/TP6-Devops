package com.ilian.dataframe;

import java.util.*;

public class DataFrame {
    private Map<String, Series<?>> columns;
    private int rowCount;

    public DataFrame(List<Series<?>> seriesList) {
        columns = new LinkedHashMap<>();
        if (seriesList.isEmpty()) {
            throw new IllegalArgumentException("La liste des séries ne peut pas être vide.");
        }

        rowCount = seriesList.get(0).size();

        for (Series<?> series : seriesList) {
            if (series.size() != rowCount) {
                throw new IllegalArgumentException("Toutes les séries doivent avoir la même taille.");
            }
            columns.put(series.getLabel(), series);
        }
    }

    public void printFull() {
        // Affiche les labels de colonnes
        System.out.println(String.join(" | ", columns.keySet()));

        for (int i = 0; i < rowCount; i++) {
            for (String label : columns.keySet()) {
                Object value = columns.get(label).get(i);
                System.out.print(value + " | ");
            }
            System.out.println();
        }
    }

    public void printHead(int n) {
        System.out.println("[HEAD] " + String.join(" | ", columns.keySet()));
        for (int i = 0; i < Math.min(n, rowCount); i++) {
            for (String label : columns.keySet()) {
                Object value = columns.get(label).get(i);
                System.out.print(value + " | ");
            }
            System.out.println();
        }
    }
    
    public void printTail(int n) {
        System.out.println("[TAIL] " + String.join(" | ", columns.keySet()));
        for (int i = Math.max(0, rowCount - n); i < rowCount; i++) {
            for (String label : columns.keySet()) {
                Object value = columns.get(label).get(i);
                System.out.print(value + " | ");
            }
            System.out.println();
        }
    }    

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columns.size();
    }

    public Series<?> getColumn(String label) {
        return columns.get(label);
    }

    public Set<String> getColumnLabels() {
        return columns.keySet();
    }
}
