package com.ericsson.eniq.events.server.common;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.List;

public final class GroupHashId {
    private final String groupType;

    private final String groupNameColumn;

    private final String tableName;

    private List<String> groupKeys = new ArrayList<String>();

    private static final String UNUSED_DECLARATION = "UnusedDeclaration";

    public GroupHashId(final String groupType, final String tableName, final String groupNameColumn,
            final List<String> groupKeys) {
        this.groupNameColumn = groupNameColumn;
        this.groupType = groupType;
        this.tableName = tableName;
        this.groupKeys.addAll(groupKeys);
        filterGroupKeysWithID();
    }

    /**
     * Get the group type e.g. APN, IMSI or TAC
     * @return The group type
     */
    @SuppressWarnings({ UNUSED_DECLARATION })
    // Used in velocity templates.
    public String getGroupType() {
        return groupType;
    }

    /**
     * Get the group table groupname column
     * @return The group name column on the GROUP_TYPE_E_<> table, usually GROUP_NAME
     */
    @SuppressWarnings({ UNUSED_DECLARATION })
    // Used in velocity templates.
    public String getGroupNameColumn() {
        return groupNameColumn;
    }

    /**
     * Get the group table in dwhdb, usually the table name starts with GROUP_TYPE_E
     * @return The group table in dwhdb
     */
    @SuppressWarnings({ UNUSED_DECLARATION })
    // Used in velocity templates.
    public String getTableName() {
        return tableName;
    }

    /**
     * Get the group data keys
     * @return Group data keys
     */
    @SuppressWarnings({ UNUSED_DECLARATION })
    // Used in velocity templates.
    public List<String> getGroupKeys() {
        return groupKeys;
    }

    /**
     * This method will check whether the group table is for controller or 
     * Access area. The logic in DB will have HIER3_ID for controller and
     * HIER321_ID for CELL. Hence only HIER3_ID or HIER321_ID should be used
     * in case of controller and access area
     */
    private void filterGroupKeysWithID() {
        if (tableName.endsWith(GROUP_TYPE_HIER3) && groupKeys != null) {
            populateGroupKeys(CONTROLLER_SQL_ID);
        } else if (tableName.endsWith(GROUP_TYPE_HIER1) && groupKeys != null) {
            populateGroupKeys(CELL_SQL_ID);
        } else if (tableName.contains(GROUP_TYPE_EVENT_SRC_CS) && groupKeys != null) {
            populateGroupKeys(EVENT_SOURCE_SQL_ID);
        }
    }

    /**
     * This method is used to filter the groupkeys list based on the match key provided
     * as parameter 
     * @param matchKey used to filter the group keys
     */
    private void populateGroupKeys(final String matchKey) {
        final List<String> mssJoinKey = new ArrayList<String>();
        for (final String joinKey : groupKeys) {
            if (joinKey.matches(matchKey)) {
                mssJoinKey.add(joinKey);
            }
        }
        groupKeys = mssJoinKey;
    }

    /**
     * Returns a string representation of the object.
     * @return  a string representation of the object.
     */
    @Override
    public String toString() {
        return "Group{" + "groupType='" + groupType + '\'' + ", groupNameColumn='" + groupNameColumn + '\''
                + ", tableName='" + tableName + '\'' + ", groupKeys=[" + groupKeys + "]}";
    }
}
