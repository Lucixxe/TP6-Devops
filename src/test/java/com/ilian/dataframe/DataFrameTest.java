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
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        dataFrame.printFull();
        System.out.flush();
        String output = baos.toString().trim();
        System.setOut(originalOut);

        String[] lines = output.split("\n");
        assertEquals("Column1\tColumn2", lines[0].trim());
        assertEquals("1\tA", lines[1].trim());
        assertEquals("2\tB", lines[2].trim());
        assertEquals("3\tC", lines[3].trim());
    }

    @Test
    void testPrintHead() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        dataFrame.printHead(2);
        System.out.flush();
        String output = baos.toString().trim();
        System.setOut(originalOut);

        String[] lines = output.split("\n");
        assertEquals("Column1\tColumn2", lines[0].trim());
        assertEquals("1\tA", lines[1].trim());
        assertEquals("2\tB", lines[2].trim());
    }

    @Test
    void testPrintTail() {
        PrintStream originalOut = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        dataFrame.printTail(2);
        System.out.flush();
        String output = baos.toString().trim();
        System.setOut(originalOut);

        String[] lines = output.split("\n");
        assertEquals("Column1\tColumn2", lines[0].trim());
        assertEquals("2\tB", lines[1].trim());
        assertEquals("3\tC", lines[2].trim());
    }

    @Test
    void testGetStatisticsOnNumericColumn() {
        Series<Integer> numericSeries = new Series<>("ages", Arrays.asList(20, 30, 25, 35));
        DataFrame df = new DataFrame(Arrays.asList(numericSeries, series2));

        Map<String, Double> stats = df.getStatistics("ages");

        assertNotNull(stats);
        assertEquals(4.0, stats.get("count"));
        assertEquals(27.5, stats.get("mean"), 0.01);
        assertEquals(20.0, stats.get("min"));
        assertEquals(35.0, stats.get("max"));
    }

    @Test
    void testGetStatisticsOnNonNumericColumn() {
        Map<String, Double> stats = dataFrame.getStatistics("Column2");
        assertNull(stats, "Les statistiques doivent être nulles pour une colonne non numérique.");
    }

    @Test
    void testGetStatisticsOnInvalidColumn() {
        Map<String, Double> stats = dataFrame.getStatistics("NonExistent");
        assertNull(stats, "Les statistiques doivent être nulles pour une colonne inexistante.");
    }

    @Test
    void testSelectRows() {
        DataFrame subFrame = dataFrame.selectRows(1, 3);  // Doit contenir les lignes d’index 1 et 2

        assertEquals(2, subFrame.getRowCount(), "Le sous-DataFrame doit contenir exactement 2 lignes.");
        assertEquals(2, subFrame.getColumnCount(), "Le sous-DataFrame doit conserver le même nombre de colonnes.");

        assertEquals(2, subFrame.getColumn("Column1").get(0));
        assertEquals("B", subFrame.getColumn("Column2").get(0));
        assertEquals(3, subFrame.getColumn("Column1").get(1));
        assertEquals("C", subFrame.getColumn("Column2").get(1));
    }

    @Test
    void testSelectColumns() {
        DataFrame selected = dataFrame.selectColumns("Column2");

        assertEquals(1, selected.getColumnCount(), "Le DataFrame sélectionné doit contenir une seule colonne.");
        assertNotNull(selected.getColumn("Column2"), "La colonne 'Column2' doit exister dans le DataFrame sélectionné.");
        assertNull(selected.getColumn("Column1"), "La colonne 'Column1' ne doit pas exister dans le DataFrame sélectionné.");
        assertEquals(3, selected.getRowCount(), "Le nombre de lignes doit rester inchangé.");
    }

}
