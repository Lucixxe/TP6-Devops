package com.ilian.dataframe;

import java.util.List;

/**
 * Représente une série de données étiquetée, contenant une liste de valeurs d'un même type.
 *
 * @param <T> Le type des valeurs contenues dans la série (ex. Integer, String, etc.)
 */
public class Series<T> {
    private String label;
    private List<T> values;

    /**
     * Construit une nouvelle série avec un label et une liste de valeurs.
     *
     * @param label Le nom (ou étiquette) de la série.
     * @param values La liste des valeurs associées à cette série.
     */
    public Series(String label, List<T> values) {
        this.label = label;
        this.values = values;
    }

    /**
     * Retourne l'étiquette de la série.
     *
     * @return L'étiquette de la série.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Retourne la liste des valeurs de la série.
     *
     * @return La liste des valeurs.
     */
    public List<T> getValues() {
        return values;
    }

    /**
     * Retourne la taille de la série, c’est-à-dire le nombre de valeurs.
     *
     * @return Le nombre de valeurs dans la série.
     */
    public int size() {
        return values.size();
    }

    /**
     * Retourne la valeur à un index donné.
     *
     * @param index L’index de la valeur à récupérer.
     * @return La valeur à l’index spécifié.
     */
    public T get(int index) {
        return values.get(index);
    }

    /**
     * Affiche la série dans la console : son étiquette puis toutes ses valeurs.
     */
    public void print() {
        System.out.println("Series: " + label);
        for (T value : values) {
            System.out.println(value);
        }
    }
}
