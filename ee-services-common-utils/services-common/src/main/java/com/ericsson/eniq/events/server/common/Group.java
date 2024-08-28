package com.ericsson.eniq.events.server.common;

import static com.ericsson.eniq.events.server.common.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.List;

public final class Group {
    private final String groupType;
    private final String groupNameColumn;
    private final String tableName;
    private List<String> groupKeys = new ArrayList<String>();
    private static final String UNUSED_DECLARATION = "UnusedDeclaration";

    public Group(final String groupType, final String tableName,
                 final String groupNameColumn, final List<String> groupKeys){
        this.groupNameColumn = groupNameColumn;
        this.groupType = groupType;
        this.tableName = tableName;
        this.groupKeys.addAll(groupKeys);
        filterGroupKeysWithID();  
    }

    /**
     * This method will check whether the group table is for controller or 
     * Access area. The in rep DB group types will have HIER3_ID,HIER321_ID etc
     * which is not present in sgeh and lte raw tables.So filter them out
     */
    private void filterGroupKeysWithID(){
    	if(tableName.contains(GROUP_TYPE_HIER3) && groupKeys != null){
    		final List<String> mssJoinKey = new ArrayList<String>();
    		for(final String joinKey : groupKeys){
    			if(joinKey.matches("HIERARCHY_[1-3]+")){
    				mssJoinKey.add(joinKey);
    			}
    		}
    		groupKeys = mssJoinKey ;
    	}else if(tableName.endsWith(GROUP_TYPE_SGSN) && groupKeys != null){
            final List<String> mssJoinKey = new ArrayList<String>();
            for(final String joinKey : groupKeys){
                if(!joinKey.matches(EVENT_SOURCE_SQL_ID)){
                    mssJoinKey.add(joinKey);
                }
            }
            groupKeys = mssJoinKey ;
        }
    }
    
    /**
     * Get the group type e.g. APN, IMSI or TAC
     * @return The group type
     */
    @SuppressWarnings({UNUSED_DECLARATION}) // Used in velocity templates.
    public String getGroupType() {
        return groupType;
    }

    /**
     * Get the group table groupname column
     * @return The group name column on the GROUP_TYPE_E_<> table, usually GROUP_NAME
     */
    @SuppressWarnings({UNUSED_DECLARATION}) // Used in velocity templates.
    public String getGroupNameColumn() {
        return groupNameColumn;
    }

    /**
     * Get the group table in dwhdb, usually the table name starts with GROUP_TYPE_E
     * @return The group table in dwhdb
     */
    @SuppressWarnings({UNUSED_DECLARATION}) // Used in velocity templates.
    public String getTableName() {
        return tableName;
    }

    /**
     * Get the group data keys
     * @return Group data keys
     */
    @SuppressWarnings({UNUSED_DECLARATION}) // Used in velocity templates.
    public List<String> getGroupKeys() {
        return groupKeys;
    }
    /**
     * Returns a string representation of the object.
     * @return  a string representation of the object.
     */
    @Override
    public String toString() {
        return "Group{" +
            "groupType='" + groupType + '\'' +
            ", groupNameColumn='" + groupNameColumn + '\'' +
            ", tableName='" + tableName + '\'' +
            ", groupKeys=[" + groupKeys +
            "]}";
    }
}
