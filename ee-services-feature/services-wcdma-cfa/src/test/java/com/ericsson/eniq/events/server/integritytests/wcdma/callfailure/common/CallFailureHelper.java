package com.ericsson.eniq.events.server.integritytests.wcdma.callfailure.common;
/**
 * 
 * @author eadrhyn
 *
 * Helper class for any static calculations that need to be accessed across the Integrity tests
 *
 */
public class CallFailureHelper {
    /**
     * Calculate the Failure rate in percentage format given the number of call failures and the number of total calls
     * @param callFailures
     * @param allCalls
     * @return
     */
    public static double calculateFailureRatePercenatge(double callFailures, double allCalls)
    {
        return (callFailures/allCalls)*100;
    }
     
}