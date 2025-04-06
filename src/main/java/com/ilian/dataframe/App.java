package com.ilian.dataframe;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Series<Integer> ages = new Series<>("Age", Arrays.asList(25, 30, 35));
        Series<String> names = new Series<>("Nom", Arrays.asList("Alice", "Bob", "Charlie"));

    try {
    DataFrame df = DataFrame.fromCSV("data/etudiants.csv");
    df.printFull();
    } catch (IOException e) {
    System.err.println("Erreur lors du chargement du fichier CSV : " + e.getMessage());
    }

        System.out.println("Test CD pipeline");
    }
}