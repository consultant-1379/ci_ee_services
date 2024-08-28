package com.ericsson.eniq.events.server.utils.sbr;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jmock.Expectations;
import org.jmock.api.Action;

public class ResultSetExpectations extends Expectations {

    public ResultSetExpectations(final ResultSet mock, final Map<String, List<?>> valueMap, final int iterationNumber,
            final boolean isStrict) throws SQLException {

        nextExpectations(mock, iterationNumber);

        getObjectExpectations(mock, valueMap, isStrict);
    }

    private void getObjectExpectations(final ResultSet mock, final Map<String, List<?>> valueMap, final boolean isStrict)
            throws SQLException {

        for (final Entry<String, List<?>> entry : valueMap.entrySet()) {
            atLeast(1).of(mock).getObject(entry.getKey());
            will(onConsecutiveCalls(buildConsecutiveGetObjectActions(entry)));
        }
        if (!isStrict) {
            allowing(mock).getObject(with(any(String.class)));
            will(returnValue(1));
        }
    }

    private Action[] buildConsecutiveGetObjectActions(final Entry<String, List<?>> entry) {
        final List<Action> getObjectActions = new ArrayList<Action>();
        for (final Object value : entry.getValue()) {
            getObjectActions.add(returnValue(value));
        }
        return getObjectActions.toArray(new Action[] {});
    }

    private void nextExpectations(final ResultSet mock, final int iterationNumber) throws SQLException {
        atLeast(1).of(mock).next();
        will(onConsecutiveCalls(buildConsecutiveNextActions(iterationNumber)));
    }

    private Action[] buildConsecutiveNextActions(final int iterationNumber) {
        final Action[] nextActions = new Action[iterationNumber + 1];
        for (int i = 0; i < iterationNumber; i++) {
            nextActions[i] = returnValue(true);
        }
        nextActions[iterationNumber] = returnValue(false);
        return nextActions;
    }
}