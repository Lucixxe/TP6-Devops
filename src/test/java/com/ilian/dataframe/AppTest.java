package com.ilian.dataframe;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.nio.file.Paths;

public class AppTest {

    @Test
    public void testFromCSV() throws Exception {
        URL resource = getClass().getClassLoader().getResource("test-data.csv");
        assertNotNull(resource, "Erreur : fichier test-data.csv introuvable !");
        String filePath = Paths.get(resource.toURI()).toString();

        DataFrame df = DataFrame.fromCSV(filePath);

        assertEquals(3, df.getRowCount(), "Le nombre de lignes est incorrect");
        assertEquals(3, df.getColumnCount(), "Le nombre de colonnes est incorrect");

        assertEquals("Alice", df.getColumn("Nom").get(0));
        assertEquals("22", df.getColumn("Age").get(0));
        assertEquals("15", df.getColumn("Note").get(0));
    }
}
