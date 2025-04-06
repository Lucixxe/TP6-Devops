package com.ilian.dataframe;

import java.io.IOException;

/**
 * Classe principale de l'application.
 * <p>
 * Cette classe contient le point d'entrée de l'application permettant de charger un fichier CSV
 * et d'afficher les 5 premières lignes du DataFrame résultant.
 * </p>
 */
public class App {

    /**
     * Point d'entrée principal de l'application.
     * <p>
     * Charge un fichier CSV à partir d'un chemin spécifié, puis affiche les 5 premières lignes.
     * Le fichier utilisé est <code>data.csv</code> situé à la racine du projet.
     * </p>
     *
     * @param args arguments de la ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        try {
            DataFrame df = CsvLoader.load("data.csv");
            df.printHead(5);
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du fichier CSV : " + e.getMessage());
        }
    }
}
