package com.ilian.dataframe;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Series<Integer> ages = new Series<>("Age", Arrays.asList(25, 30, 35));
        Series<String> names = new Series<>("Nom", Arrays.asList("Alice", "Bob", "Charlie"));

        DataFrame df = new DataFrame(List.of(ages, names));
        df.printFull();
        df.printHead(2);
        df.printTail(2);
        System.out.println("Test CD pipeline");


    }
}