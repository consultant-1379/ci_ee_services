package com.ericsson.eniq.events.server.utils;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class CalculatorFactory {

    /**
     * getSumCalcaulator is used to get a calcautor for sum
     * 
     * BitInteger performance is lower than long or int, but BigInteger can resolve overflow issues
     * Another solution is using long or int first, but use BigInteger when it overflows.  
     * These two solutions are tested:
     *     Platform: Core Dure 2.0GB, Windows Vista
     *        Input: list.size() = 8 without overflow
     *               exclusiveColumns.size() = 8
     *       Repeat: 10 000 000
     * ByBigInteger: 50s
     *       ByLong: 30s
     *     
     *     So the performance is acceptable by using BigInteger
     * 
     * @return A Calcautor for sum using BigInteger
     */
    public static Calculator getSumCalcaulator() {
        return getSumCalcaulatorByBigInteger();
    }

    /**
     * get sum calculator by using BigInteger
     * @return sum calculator
     */
    static Calculator getSumCalcaulatorByBigInteger() { // don't use private only for unittest
        return new Calculator() {

            private Set<Integer> exclusiveColumnIndexs;

            private BigInteger max;

            private BigInteger min;

            {
                min = null;
                max = null;
            }

            @Override
            public List<String> calc(final List<String> list, final List<String> aExclusiveColumnIndexs)
                    throws NumberFormatException {
                if (list == null || list.isEmpty()) {
                    return new ArrayList<String>();
                }
                initExclusiveColumnIndexs(aExclusiveColumnIndexs);
                BigInteger sum = BigInteger.ZERO;
                for (int i = 0; i < list.size(); ++i) {
                    if (!exclusiveColumnIndexs.contains(i + 1)) {// exclusiveColumnIndexs is 1 based index
                        final BigInteger elem = new BigInteger(list.get(i));
                        sum = sum.add(elem);
                        updateMaxMin(elem);
                    }
                }
                updateMaxMin(sum);
                final List<String> rst = new ArrayList<String>(list);
                rst.add(sum.toString());
                return rst;
            }

            @Override
            public String max() {
                if (max != null) {
                    return max.toString();
                }
                return MAX_VALUE_DEF;
            }

            @Override
            public String min() {
                if (min != null) {
                    return min.toString();
                }
                return MIN_VALUE_DEF;
            }

            private void updateMaxMin(final BigInteger value) {
                if (max == null || max.compareTo(value) == -1) {
                    max = value;
                }
                if (min == null || min.compareTo(value) == 1) {
                    min = value;
                }
            }

            private void initExclusiveColumnIndexs(final List<String> aExclusiveColumnIndexs)
                    throws NumberFormatException {
                exclusiveColumnIndexs = new HashSet<Integer>();
                if (aExclusiveColumnIndexs != null) {
                    for (final String elem : aExclusiveColumnIndexs) {
                        if (elem != null) {
                            exclusiveColumnIndexs.add(Integer.parseInt(elem));
                        }
                    }
                }
            }
        };
    }

    /**
     * get sum calculator by using Long first, if it fails, use BigInteger
     * @return sum calculator
     */
    static Calculator getSumCalcaulatorByLong() { // don't use private only for unittest
        return new Calculator() {

            private Set<Integer> exclusiveColumnIndexs;

            private long min;

            private boolean setMin;

            private long max;

            private boolean setMax;

            private Calculator calc;

            {
                calc = null;
                min = Long.MAX_VALUE;
                max = Long.MIN_VALUE;
                setMin = false;
                setMax = false;

            }

            @Override
            public List<String> calc(final List<String> list, final List<String> aExclusiveColumnIndexs)
                    throws NumberFormatException {
                if (list == null || list.isEmpty()) {
                    return new ArrayList<String>();
                }
                List<String> rst = null;
                try {
                    initExclusiveColumnIndexs(aExclusiveColumnIndexs);
                    long sum = 0L;
                    long tmp = 0L;
                    for (int i = 0; i < list.size(); ++i) {
                        // sum
                        if (!exclusiveColumnIndexs.contains(i + 1)) { // exclusiveColumnIndexs is 1 based index
                            final long elem = Long.parseLong(list.get(i));
                            if (sum == 0 || elem == 0) {
                                sum += elem;
                            } else if ((sum > 0 && elem < 0) || (sum < 0 && elem > 0)) {
                                sum += elem;
                            } else { // (sum < 0 && elem < 0) || (sum > 0 && elem > 0)
                                tmp = sum;
                                sum += elem;
                                if ((tmp > 0 && sum <= 0) || (tmp < 0 && sum >= 0)) { // overflow
                                    throw new LongOverFlowException();
                                }
                            }
                            updateMaxMin(elem);
                        }
                    }
                    updateMaxMin(sum);
                    rst = new ArrayList<String>(list);
                    rst.add(Long.toString(sum));
                } catch (final NumberFormatException e) {
                    // Long.parseLong(list.get(i)) throws NumberFormatException may because list.get(i)
                    // is bigger than Long.MAX_VALUE, so use BigInteger to have a try
                    calc = getSumCalcaulatorByBigInteger();
                    rst = calc.calc(list, aExclusiveColumnIndexs);
                } catch (final LongOverFlowException e) {
                    calc = getSumCalcaulatorByBigInteger();
                    rst = calc.calc(list, aExclusiveColumnIndexs);
                }
                return rst;
            }

            @Override
            public String max() {
                if (calc != null) {
                    return calc.max();
                }
                if (setMax) {
                    return Long.toString(max);
                }
                return MAX_VALUE_DEF;
            }

            @Override
            public String min() {
                if (calc != null) {
                    return calc.min();
                }
                if (setMin) {
                    return Long.toString(min);
                }
                return MIN_VALUE_DEF;
            }

            private void updateMaxMin(final long value) {
                if (value > max) {
                    max = value;
                    setMax = true;
                }
                if (value < min) {
                    min = value;
                    setMin = true;
                }
            }

            private void initExclusiveColumnIndexs(final List<String> aExclusiveColumnIndexs)
                    throws NumberFormatException {
                exclusiveColumnIndexs = new HashSet<Integer>();
                if (aExclusiveColumnIndexs != null) {
                    for (final String elem : aExclusiveColumnIndexs) {
                        if (elem != null) {
                            exclusiveColumnIndexs.add(Integer.parseInt(elem));
                        }
                    }
                }
            }

            class LongOverFlowException extends Exception {
            }

        };
    }

    private CalculatorFactory() {
    }
}
