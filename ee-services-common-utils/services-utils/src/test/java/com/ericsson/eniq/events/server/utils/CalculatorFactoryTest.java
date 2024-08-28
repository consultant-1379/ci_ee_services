package com.ericsson.eniq.events.server.utils;

import static com.ericsson.eniq.events.server.utils.Calculator.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class CalculatorFactoryTest {

    @Test
    public void testGetSumCalcaulatorByBigInteger() {
        final List<String> data = Arrays.asList("25", "35", "65", "890", "2011-06-24 19:15:00", "-1024", "257");
        final List<String> exclusiveColumns = Arrays.asList("1", "5");
        final List<String> expected = Arrays.asList("25", "35", "65", "890", "2011-06-24 19:15:00", "-1024", "257",
                "223");
        final Calculator calc = CalculatorFactory.getSumCalcaulatorByBigInteger();
        final List<String> actual = calc.calc(data, exclusiveColumns);
        assertEquals(actual, expected);
        assertEquals("890", calc.max());
        assertEquals("-1024", calc.min());
    }

    @Test
    public void testGetSumCalcaulatorByBigIntegerNullList() {
        final List<String> data = null;
        final List<String> exclusiveColumns = Arrays.asList("1", "5");
        final Calculator calc = CalculatorFactory.getSumCalcaulatorByBigInteger();
        assertEquals(0, calc.calc(data, exclusiveColumns).size());
        assertEquals(MAX_VALUE_DEF, calc.max());
        assertEquals(MIN_VALUE_DEF, calc.min());
    }

    @Test
    public void testGetSumCalcaulatorByBigIntegerEmptyList() {
        final List<String> data = new ArrayList<String>();
        final List<String> exclusiveColumns = Arrays.asList("1", "5");
        final Calculator calc = CalculatorFactory.getSumCalcaulatorByBigInteger();
        assertEquals(0, calc.calc(data, exclusiveColumns).size());
        assertEquals(MAX_VALUE_DEF, calc.max());
        assertEquals(MIN_VALUE_DEF, calc.min());
    }

    @Test
    public void testGetSumCalcaulatorByBigIntegerNullExclusiveColumns() {
        final List<String> data = Arrays.asList("25", "35", "65", "890", "0", "-1024", "257");
        final List<String> exclusiveColumns = null;
        final List<String> expected = Arrays.asList("25", "35", "65", "890", "0", "-1024", "257", "248");
        final Calculator calc = CalculatorFactory.getSumCalcaulatorByBigInteger();
        final List<String> actual = calc.calc(data, exclusiveColumns);
        assertEquals(actual, expected);
        assertEquals("890", calc.max());
        assertEquals("-1024", calc.min());
    }

    @Test
    public void testGetSumCalcaulatorByBigIntegerForMultipleList() {
        final List<String> data1 = Arrays.asList("25", "35", "65", "890", "0", "-1024", "257");
        final List<String> data2 = Arrays.asList("256", "78", "65", "1090", "0", "-102", "2570");
        final List<String> data3 = Arrays.asList("2560", "780", "65", "1090", "0", "0", "12570");
        final List<String> exclusiveColumns = Arrays.asList("1", "5");
        final List<String> expected1 = Arrays.asList("25", "35", "65", "890", "0", "-1024", "257", "223");
        final List<String> expected2 = Arrays.asList("256", "78", "65", "1090", "0", "-102", "2570", "3701");
        final List<String> expected3 = Arrays.asList("2560", "780", "65", "1090", "0", "0", "12570", "14505");
        final Calculator calc = CalculatorFactory.getSumCalcaulatorByBigInteger();
        final List<String> actual = calc.calc(data1, exclusiveColumns);
        final List<String> actua2 = calc.calc(data2, exclusiveColumns);
        final List<String> actua3 = calc.calc(data3, exclusiveColumns);
        assertEquals(actual, expected1);
        assertEquals(actua2, expected2);
        assertEquals(actua3, expected3);
        assertEquals("14505", calc.max());
        assertEquals("-1024", calc.min());
    }

    @Test
    public void testGetSumCalcaulatorByLong() {
        final List<String> data = Arrays.asList("25", "35", "65", "890", "2011-06-24 19:15:00", "-1024", "257");
        final List<String> exclusiveColumns = Arrays.asList("1", "5");
        final List<String> expected = Arrays.asList("25", "35", "65", "890", "2011-06-24 19:15:00", "-1024", "257",
                "223");
        final Calculator calc = CalculatorFactory.getSumCalcaulatorByLong();
        final List<String> actual = calc.calc(data, exclusiveColumns);
        assertEquals(actual, expected);
        assertEquals("890", calc.max());
        assertEquals("-1024", calc.min());
    }

    @Test
    public void testGetSumCalcaulatorByLongEmptyList() {
        final List<String> data = new ArrayList<String>();
        final List<String> exclusiveColumns = Arrays.asList("1", "5");
        final Calculator calc = CalculatorFactory.getSumCalcaulatorByLong();
        assertEquals(0, calc.calc(data, exclusiveColumns).size());
        assertEquals(MAX_VALUE_DEF, calc.max());
        assertEquals(MIN_VALUE_DEF, calc.min());
    }

    @Test
    public void testGetSumCalcaulatorByLongNullList() {
        final List<String> data = null;
        final List<String> exclusiveColumns = Arrays.asList("1", "5");
        final Calculator calc = CalculatorFactory.getSumCalcaulatorByLong();
        assertEquals(0, calc.calc(data, exclusiveColumns).size());
        assertEquals(MAX_VALUE_DEF, calc.max());
        assertEquals(MIN_VALUE_DEF, calc.min());
    }

    @Test
    public void testGetSumCalcaulatorByNullExclusiveColumns() {
        final List<String> data = Arrays.asList("25", "35", "65", "890", "0", "-1024", "257");
        final List<String> exclusiveColumns = null;
        final List<String> expected = Arrays.asList("25", "35", "65", "890", "0", "-1024", "257", "248");
        final Calculator calc = CalculatorFactory.getSumCalcaulatorByLong();
        final List<String> actual = calc.calc(data, exclusiveColumns);
        assertEquals(actual, expected);
        assertEquals("890", calc.max());
        assertEquals("-1024", calc.min());
    }

    @Test
    public void testGetSumCalcaulatorByLongOverflow() {
        final List<String> data = Arrays.asList("25", "35", "65", String.format("1%s", Long.MAX_VALUE),
                "2011-06-24 19:15:00", "-1024", "257");
        final List<String> exclusiveColumns = Arrays.asList("1", "2", "5");
        final List<String> expected = Arrays.asList("25", "35", "65", String.format("1%s", Long.MAX_VALUE),
                "2011-06-24 19:15:00", "-1024", "257", "19223372036854775105");
        final Calculator calc = CalculatorFactory.getSumCalcaulatorByLong();
        final List<String> actual = calc.calc(data, exclusiveColumns);
        assertEquals(actual, expected);
        assertEquals(String.format("1%s", Long.MAX_VALUE), calc.max());
        assertEquals("-1024", calc.min());
    }

    @Test
    public void testGetSumCalcaulatorByLongOverflow2() {
        final List<String> data = Arrays.asList("25", "35", "65", Long.toString(Long.MAX_VALUE), "2011-06-24 19:15:00",
                "1024", "257");
        final List<String> exclusiveColumns = Arrays.asList("1", "2", "5");
        final List<String> expected = Arrays.asList("25", "35", "65", Long.toString(Long.MAX_VALUE),
                "2011-06-24 19:15:00", "1024", "257", "9223372036854777153");
        final Calculator calc = CalculatorFactory.getSumCalcaulatorByLong();
        final List<String> actual = calc.calc(data, exclusiveColumns);
        assertEquals(actual, expected);
        assertEquals("9223372036854777153", calc.max());
        assertEquals("65", calc.min());
    }

    @Test
    public void testGetSumCalcaulatorForMultipleList() {
        final List<String> data1 = Arrays.asList("25", "35", "65", "890", "0", "-1024", "257");
        final List<String> data2 = Arrays.asList("256", "78", "65", "1090", "0", "-102", "2570");
        final List<String> data3 = Arrays.asList("2560", "780", "65", "1090", "0", "0", "12570");
        final List<String> exclusiveColumns = Arrays.asList("1", "5");
        final List<String> expected1 = Arrays.asList("25", "35", "65", "890", "0", "-1024", "257", "223");
        final List<String> expected2 = Arrays.asList("256", "78", "65", "1090", "0", "-102", "2570", "3701");
        final List<String> expected3 = Arrays.asList("2560", "780", "65", "1090", "0", "0", "12570", "14505");
        final Calculator calc = CalculatorFactory.getSumCalcaulator();
        final List<String> actual = calc.calc(data1, exclusiveColumns);
        final List<String> actua2 = calc.calc(data2, exclusiveColumns);
        final List<String> actua3 = calc.calc(data3, exclusiveColumns);
        assertEquals(actual, expected1);
        assertEquals(actua2, expected2);
        assertEquals(actua3, expected3);
        assertEquals("14505", calc.max());
        assertEquals("-1024", calc.min());
    }

    /*! Below test is SPM test and it runs too long.  
    @Test
    public void testGetSumCalcaulatorByBigIntegerForLongTime() {
        final int COUNT = 2500;
        final int MAX_COLUMN = 30;
        final NumberGenerator generator = new NumberGenerator();
        final List<String> data = new ArrayList<String>();
        final List<String> exclusiveColumns = new ArrayList<String>();
        final Calculator calc = CalculatorFactory.getSumCalcaulator();
        for (int i = 0; i < COUNT; ++i) {
            for (int j = 0; j < generator.nextColumnCount(MAX_COLUMN); ++j) {
                data.add(generator.next());
            }
            for (int z = 0; z < 5; ++z) {
                exclusiveColumns.add(generator.nextExclusiveColumn(MAX_COLUMN));
            }
            calc.calc(data, exclusiveColumns);
            calc.max();
            calc.min();
        }
    }

    private class NumberGenerator {
        public String next() {
            return Long.toString(rand.nextLong());
        }

        public int nextColumnCount(final int maxColumn) {
            return (Math.abs(rand.nextInt()) % maxColumn) + 1;
        }

        public String nextExclusiveColumn(final int maxColumn) {
            return Integer.toString((Math.abs(rand.nextInt()) % maxColumn) + 2);
        }

        private final Random rand = new Random((new Date()).getTime());
    }
    */

}
