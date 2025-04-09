package com.ilian.dataframe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DataFrameTest {

    private Series<Integer> series1;
    private Series<String> series2;
    private DataFrame dataFrame;

    @BeforeEach
    void setUp() {
        series1 = new Series<>("Column1", Arrays.asList(1, 2, 3));
        series2 = new Series<>("Column2", Arrays.asList("A", "B", "C"));
        dataFrame = new DataFrame(Arrays.asList(series1, series2));
    }

    @Test
    void testConstructorWithValidInput() {
        assertEquals(2, dataFrame.getColumnCount(), "Le DataFrame doit correctement initialiser le nombre de colonnes.");
        assertEquals(3, dataFrame.getRowCount(), "Le DataFrame doit correctement initialiser le nombre de lignes.");
    }

    @Test
    void testConstructorWithDuplicateLabels() {
        Series<Integer> duplicateSeries = new Series<>("Column1", Arrays.asList(4, 5, 6));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new DataFrame(Arrays.asList(series1, duplicateSeries));
        }, "Le DataFrame doit empêcher la création avec des étiquettes de colonnes en double.");
        assertEquals("Duplicate column label: Column1", exception.getMessage(), "Le message d'erreur doit indiquer qu'une étiquette en double a été détectée.");
    }

    @Test
    void testGetColumnLabels() {
        Set<String> labels = dataFrame.getColumnLabels();
        assertTrue(labels.contains("Column1"), "Le DataFrame doit inclure toutes les étiquettes de colonnes définies.");
        assertTrue(labels.contains("Column2"), "Le DataFrame doit inclure toutes les étiquettes de colonnes définies.");
    }

    @Test
    void testGetColumn() {
        Series<?> column = dataFrame.getColumn("Column1");
        assertNotNull(column, "Le DataFrame doit retourner une colonne valide pour une étiquette existante.");
        assertEquals(series1, column, "Le DataFrame doit retourner la colonne correcte correspondant à l'étiquette.");
    }

    @Test
    void testGetColumnWithInvalidLabel() {
        Series<?> column = dataFrame.getColumn("InvalidColumn");
        assertNull(column, "Le DataFrame doit retourner null pour une étiquette de colonne inexistante.");
    }

    @Test
    void testGetRowCount() {
        assertEquals(3, dataFrame.getRowCount(), "Le DataFrame doit calculer correctement le nombre de lignes.");
    }

    @Test
    void testGetColumnCount() {
        assertEquals(2, dataFrame.getColumnCount(), "Le DataFrame doit calculer correctement le nombre de colonnes.");
    }

    @Test
    void testPrintFull() {
        // Redirect System.out to capture output
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        // Call printFull()
        dataFrame.printFull();
        System.out.flush();
        String output = baos.toString();
        // Reset System.out
        System.setOut(originalOut);

        String expected = "Column1\tColumn2\n" +
                          "1\tA\n" +
                          "2\tB\n" +
                          "3\tC\n";
        assertEquals(expected, output, "Le DataFrame doit afficher toutes les lignes et colonnes correctement.");
    }

    @Test
    void testPrintHead() {
        // Redirect System.out to capture output
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        // Call printHead(2)
        dataFrame.printHead(2);
        System.out.flush();
        String output = baos.toString();
        // Reset System.out
        System.setOut(originalOut);

        String expected = "Column1\tColumn2\n" +
                          "1\tA\n" +
                          "2\tB\n";
        assertEquals(expected, output, "Le DataFrame doit afficher correctement les premières lignes demandées.");
    }

    @Test
    void testPrintTail() {
        // Redirect System.out to capture output
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        // Call printTail(2)
        dataFrame.printTail(2);
        System.out.flush();
        String output = baos.toString();
        // Reset System.out
        System.setOut(originalOut);

        String expected = "Column1\tColumn2\n" +
                          "2\tB\n" +
                          "3\tC\n";
        assertEquals(expected, output, "Le DataFrame doit afficher correctement les dernières lignes demandées.");
    }
}