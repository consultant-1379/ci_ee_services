package com.ericsson.eniq.events.server.common.tablesandviews;

import static org.hamcrest.collection.IsCollectionContaining.*;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

/**
 * Test the TechPackTablesObject
 * Not reusing the Base mock test in services-test, that would introduce a cyclic dependency between services-common
 * and services-test
 * @author eemecoy
 *
 */
public class TechPackTablesTest {

    TechPack mockedTechPack;

    private final Mockery mockery = new JUnit4Mockery();
    {
        mockery.setImposteriser(ClassImposteriser.INSTANCE);
    }

    static String TABLE_STRING = "TABLE_STRING";

    @Before
    public void setUp() {
        mockedTechPack = mockery.mock(TechPack.class);
    }

    @Test
    public void testGetErrTablesHandleStringReturnFromTechPack() {
        final TechPackTables objToTest = new TechPackTables(TableType.AGGREGATION);
        objToTest.addTechPack(mockedTechPack);
        mockery.checking(new Expectations() {
            {
                one(mockedTechPack).getErrTables("ERR");
                will(returnValue(TABLE_STRING));
            }
        });
        final List<String> returned = objToTest.getErrTables();

        assertThat(returned, hasItem(TABLE_STRING));
    }

    @Test
    public void testGetErrTablesHandleListReturnFromTechPack() {
        final TechPackTables objToTest = new TechPackTables(TableType.RAW);
        objToTest.addTechPack(mockedTechPack);
        final List<String> listToReturn = new ArrayList<String>();
        listToReturn.add(TABLE_STRING);
        mockery.checking(new Expectations() {
            {
                one(mockedTechPack).getErrTables("ERR");
                will(returnValue(listToReturn));
            }
        });
        final List<String> returned = objToTest.getErrTables();
        assertThat(returned, hasItem(TABLE_STRING));
    }

    @Test
    public void testGetSucTablesHandleStringReturnFromTechPack() {
        final TechPackTables objToTest = new TechPackTables(TableType.AGGREGATION);
        objToTest.addTechPack(mockedTechPack);
        mockery.checking(new Expectations() {
            {
                one(mockedTechPack).getSucTables("SUC");
                will(returnValue(TABLE_STRING));
            }
        });
        final List<String> returned = objToTest.getSucTables();

        assertThat(returned, hasItem(TABLE_STRING));
    }

    @Test
    public void testGetSucTablesHandleListReturnFromTechPack() {
        final TechPackTables objToTest = new TechPackTables(TableType.RAW);
        objToTest.addTechPack(mockedTechPack);
        final List<String> listToReturn = new ArrayList<String>();
        listToReturn.add(TABLE_STRING);
        mockery.checking(new Expectations() {
            {
                one(mockedTechPack).getSucTables("SUC");
                will(returnValue(listToReturn));
            }
        });
        final List<String> returned = objToTest.getSucTables();
        assertThat(returned, hasItem(TABLE_STRING));
    }
}
