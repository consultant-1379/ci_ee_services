package com.ericsson.eniq.events.server.common;

import com.ericsson.eniq.events.server.common.utils.StringUtilities;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * -----------------------------------------------------------------------
 * Copyright (C) ${year} LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
public class StringUtilitiesTest {
   
   private String[] testInputStringArray = {"Column,1","Column2","Column_3","Column4","Column5","Column6"};
   
   private Collection<String> testInputCollection;
   
   private String testOutputString = "Column,1,Column2,Column_3,Column4,Column5,Column6";
   private String testOutputString2 = "Column,1_Column2_Column_3_Column4_Column5_Column6";

   @Test
   public void convertStringArrayToCommaSeparatedStringTest() {
      String outputStr = StringUtilities.convertStringArrayToCommaSeparatedString(testInputStringArray);
      
      assertEquals(testOutputString,outputStr);
   }
   
   @Test
   public void convertCollectionToCommaSeparatedStringTest() {

      testInputCollection = StringUtilities.StringsToCollection("Column,1","Column2","Column_3","Column4","Column5","Column6");

      String outputStr = StringUtilities.convertCollectionToCommaSeparatedString(testInputCollection);

      assertEquals(testOutputString,outputStr);
   }
   
   @Test
   public void stringArrayToStringTest() {
      String outputStr = StringUtilities.stringArrayToString(testInputStringArray, '_', 30);

      assertEquals(testOutputString2 + "_", outputStr);
   }


   @Test
   public void arrayToCsvStringTest() {
      String outputStr = StringUtilities.arrayToCsvString(testInputStringArray);

      assertEquals(testOutputString,outputStr);
   }

   @Test
   public void clearStringBuilderTest() {
      StringBuilder sBuilder = new StringBuilder();
      sBuilder.append(testOutputString);

      StringUtilities.clearStringBuilder(sBuilder);

      assertEquals("", sBuilder.toString());
   }

}
