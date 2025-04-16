package com.ilian.dataframe;

import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * Classe principale de l'application.
 * Permet de charger un fichier CSV et d'interagir avec son contenu via un menu CLI.
 */
public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DataFrame df = null;

        try {
            df = CsvLoader.load("data/etudiants.csv");
            System.out.println("✅ Fichier chargé avec succès !");
        } catch (IOException e) {
            System.err.println("❌ Erreur lors du chargement du fichier CSV : " + e.getMessage());
            System.exit(1);
        }

        while (true) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Afficher les 5 premières lignes (head)");
            System.out.println("2. Afficher les 5 dernières lignes (tail)");
            System.out.println("3. Afficher les statistiques d'une colonne");
            System.out.println("4. Quitter");
            System.out.print("👉 Votre choix : ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    df.printHead(5);
                    break;
                case "2":
                    df.printTail(5);
                    break;
                case "3":
                    System.out.print("Nom de la colonne pour les stats : ");
                    String colName = scanner.nextLine().trim();
                    Map<String, Double> stats = df.getStatistics(colName);
                    if (stats != null) {
                        System.out.println("📊 Statistiques pour \"" + colName + "\" :");
                        stats.forEach((k, v) -> System.out.println(k + " = " + v));
                    } else {
                        System.out.println("⚠️ Colonne invalide ou non numérique.");
                    }
                    break;
                case "4":
                    System.out.println("👋 Au revoir !");
                    scanner.close();
                    return;
                default:
                    System.out.println("⛔ Choix invalide. Veuillez réessayer.");
            }
        }
    }
}
