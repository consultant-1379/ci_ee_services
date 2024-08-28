package com.ericsson.eniq.events.server.common;

/**
 * User preferences VO
 *
 * @author ejedmar
 * @since 2011
 */
public class UserPreferencesType {

    /**
     * preferences data column/param name
     */
    public static final String SETTINGS_COLUMN = "settings";

    /**
     * data version column/param name
     */
    public static final String VERSION_COLUMN = "version";

    /**
     * username column/param name
     */
    public static final String USERNAME_COLUMN = "username";


    private String username;

    private Integer version;

    /**
     * JSON clob
     */
    private String userPreferencesData;

    public UserPreferencesType() {
    }

    public UserPreferencesType(final String username, final Integer version, final String userPreferencesData) {
        super();
        this.username = username;
        this.version = version;
        this.userPreferencesData = userPreferencesData;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(final Integer version) {
        this.version = version;
    }

    public String getUserPreferencesData() {
        return userPreferencesData;
    }

    public void setUserPreferencesData(final String userPreferencesData) {
        this.userPreferencesData = userPreferencesData;
    }

}
