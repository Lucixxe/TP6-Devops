package com.ilian.dataframe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testDataFrameConstruction() {
        Series<Integer> ages = new Series<>("Age", Arrays.asList(25, 30, 35));
        Series<String> noms = new Series<>("Nom", Arrays.asList("Alice", "Bob", "Charlie"));

        DataFrame df = new DataFrame(List.of(ages, noms));

        assertEquals(3, df.getRowCount());
        assertEquals(2, df.getColumnCount());
        assertNotNull(df.getColumn("Age"));
        assertNotNull(df.getColumn("Nom"));
    }
}
