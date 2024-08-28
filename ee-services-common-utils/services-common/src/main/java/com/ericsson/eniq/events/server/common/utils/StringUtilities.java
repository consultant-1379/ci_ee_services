/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2009 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */

package com.ericsson.eniq.events.server.common.utils;

import java.util.Arrays;
import java.util.Collection;
import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

public abstract class StringUtilities {

    /**
     * Method to quickly convert one or more Strings to an Array. 
     * Especially useful when creating String[] on the fly in code which generate a PMD Warning
     * @param strings
     * @return
     */
    public static String[] StringsToArray(final String... strings) {
        return strings;
    }

    /**
     * Method to quickly convert one or more Strings to a Collection. 
     * 
     * @param strings
     * @return
     */
    public static Collection<String> StringsToCollection(final String... strings) {
        return Arrays.asList(strings);
    }

    /**
     * Method to convert an Array of objects to a String. Will use the toString value of each object create a comma-
     * separated list of these
     * Returns an empty String when Array is null or empty 
     * @param objects
     * @return String
     */
    public static String arrayToCsvString(final Object objects[]) {
        return arrayToCsvString(objects, EMPTY_STRING);
    }

   /**
    * Added for TR HK97709 to improve the memory usage
    *
    * This method will remove a substring from the StringBuilder starting from the first character to the end.
    * It is a good practise to clean the sub string specially in loops to improve the memory usage.
    *
    * @param strBuilder StringBuilder that is to cleaned.
    */
   public static void clearStringBuilder(final StringBuilder strBuilder) {

      try {
         strBuilder.delete(0, strBuilder.length());
      } catch (final StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
      }
   }

    /**
     * Method to convert an Array of objects to a String. Will use the toString value of each object create a comma-
     * separated list of these
     * @param objects
     * @param returnWhenEmpty, a special String to be returned if the Array is empty,null or the resulting string
     * is empty 
     * @return String
     */
    public static String arrayToCsvString(final Object objects[], final String returnWhenEmpty) {
        if (objects == null) {
            return returnWhenEmpty;
        }
        if (objects.length == 0) {
            return returnWhenEmpty;
        }
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < objects.length; i++) {
            if (i > 0) {
                builder.append(COMMA_CHAR);
            }
            builder.append(String.valueOf(objects[i]));
        }
        if (builder.length() == 0) {
            return returnWhenEmpty;
        }
        final String result = builder.toString();
        clearStringBuilder(builder);
        return result;
    }

    /**
     * Convert an Array of String to a separated list.
     * Each element will be terminated by the given separator char (even the last element)
     * If the input array is empty the result will be an empty String
     * @param stringArray 
     * @param separator Character to terminate each element with
     * @param expectedElementSize To optimise the conversion a StringBuffer is created by calculated the number of 
     * elements times the give (maximum) element size  
     * @return String
     */
    public static String stringArrayToString(final String[] stringArray, final char separator,
            final int expectedElementSize) {
        // StringBuilder large enough for long elements
        final StringBuilder builder = new StringBuilder(expectedElementSize * stringArray.length);
        for (final String element : stringArray) {
            builder.append(element);
            builder.append(separator);
        }
        final String result = builder.toString();
        clearStringBuilder(builder); // For TR HK97709 memory usage improvement.
        return result;
    }

    /**
     * Convert a collection of Strings to a Single string 
     * separated by comma
     * 
     * @param stringList
     * @return
     */
    public static String convertCollectionToCommaSeparatedString(final Collection<String> stringList) {
        return convertCollectionToString(stringList, COMMA_CHAR);
    }

    /**
     * Convert a String[] to a single String where each element is 
     * separated by comma
     * 
     * @param array
     * @return
     */
    public static String convertStringArrayToCommaSeparatedString(final String[] array) {
        return convertStringArrayToString(array, COMMA_CHAR);
    }

    /**
     * Convert a collection of Strings to a Single string separated by
     * the passed separator String.
     * 
     * @param stringList
     * @param separator
     * @return
     */
    public static String convertCollectionToString(final Collection<String> stringList, final char separator) {

        final StringBuilder result = new StringBuilder();

        for (final String item : stringList) {
           result.append(item);
           result.append(separator);
        }

        if (result.length() > 0) {
            return result.substring(0, result.length() - 1);
        }
        return EMPTY_STRING;
    }

    /**
     * Convert a String[] to a single String where each element is 
     * separated by the separator string
     * 
     * @param array
     * @param separator
     * @return
     */
    public static String convertStringArrayToString(final String[] array, final char separator) {

        final StringBuilder result = new StringBuilder((array.length) + (array.length));

        for (final String item : array) {
            result.append(item);
            result.append(separator);
        }

        if (result.length() > 0) {
            return result.substring(0, result.length() - 1);
        }
        return EMPTY_STRING;
    }
}
