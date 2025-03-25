package com.ilian.dataframe;

import java.util.List;

public class Series<T> {
    private String label;
    private List<T> values;

    public Series(String label, List<T> values) {
        this.label = label;
        this.values = values;
    }

    public String getLabel() {
        return label;
    }

    public List<T> getValues() {
        return values;
    }

    public int size() {
        return values.size();
    }

    public T get(int index) {
        return values.get(index);
    }

    public void print() {
        System.out.println("Series: " + label);
        for (T value : values) {
            System.out.println(value);
        }
    }
}
