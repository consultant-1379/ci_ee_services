package com.ericsson.eniq.events.server.integritytests.stubs;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ericsson.eniq.events.server.templates.exception.ResourceInitializationException;
import com.ericsson.eniq.events.server.templates.utils.TemplateUtils;

import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_DISCONNECTION;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_RRC_ESTABLISHMENT_CAUSE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_TRIGGER_POINT;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_CFA_UTRAN_RANAP_CAUSE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_ECNO_MAPPING;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_RABTYPE;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_RSCP_MAPPING;
import static com.ericsson.eniq.events.server.test.temptables.TempTableNames.TEMP_DIM_E_RAN_ULINT_MAPPING;

/**
 * Place # before every table/view name in query
 * @author EEMECOY
 *
 */
public class ReplaceTablesWithTempTablesTemplateUtils extends TemplateUtils {

    private static final String REPDB_TEMPLATES_FOLDER = "repdb";

    private final static Map<String, String> extraKeysToFindAndReplace = new HashMap<String, String>();

    public static final String TEMP_TABLE_PREFIX = "#";

    public ReplaceTablesWithTempTablesTemplateUtils() {
        super();
        extraKeysToFindAndReplace.clear(); //clearing the static map
    }

    @Override
    public String getQueryFromTemplate(final String templateFile, final Map<String, ?> parameters)
            throws ResourceInitializationException {
        final String queryFromTemplate = super.getQueryFromTemplate(templateFile, parameters);
        if (isADataQuery(templateFile)) {
            return findAndReplaceAllTablesWithTemporaryEquivalents(queryFromTemplate);
        }
        return queryFromTemplate;
    }

    private boolean isADataQuery(final String templateFile) {
        final String parentDirectory = getParentDirectory(templateFile);
        return !parentDirectory.contains(REPDB_TEMPLATES_FOLDER);
    }

    private String getParentDirectory(final String templateFile) {
        return templateFile.substring(0, templateFile.lastIndexOf("/"));
    }

    private String findAndReplaceAllTablesWithTemporaryEquivalents(final String queryFromTemplate) {
        String modifiedQuery = queryFromTemplate;
        final Map<String, String> keysToFindAndReplace = getKeysToFindAndReplace();
        for (final String key : keysToFindAndReplace.keySet()) {
            modifiedQuery = replaceAll(modifiedQuery, key, keysToFindAndReplace.get(key));
        }
        return modifiedQuery;
    }

    private Map<String, String> getKeysToFindAndReplace() {
        final Map<String, String> keysToFindAndReplace = new HashMap<String, String>();
        keysToFindAndReplace.putAll(extraKeysToFindAndReplace);
        keysToFindAndReplace.put("EVENT_E", "#EVENT_E");
        keysToFindAndReplace.put("GROUP_TYPE", "#GROUP_TYPE");
        keysToFindAndReplace.put("DIM_E_SGEH_APN", "#DIM_E_SGEH_APN");
        keysToFindAndReplace.put("DIM_E_LTE_APN", "#DIM_E_LTE_APN");
        keysToFindAndReplace.put("DIM_E_SGEH_HIER321", "#DIM_E_SGEH_HIER321");
        keysToFindAndReplace.put("DIM_Z_SGEH_HIER321", "#DIM_Z_SGEH_HIER321");
        keysToFindAndReplace.put("DIM_E_LTE_ERBS", "#DIM_E_LTE_ERBS");
        keysToFindAndReplace.put("DIM_E_LTE_HIER321", "#DIM_E_LTE_HIER321");
        keysToFindAndReplace.put("DIM_E_LTE_EUCELL", "#DIM_E_LTE_EUCELL");
        keysToFindAndReplace.put("DIM_E_RAN_RNC", "#DIM_E_RAN_RNC");
        keysToFindAndReplace.put("VERSIONING", "#VERSIONING");
        keysToFindAndReplace.put("TPACTIVATION", "#TPACTIVATION");
        keysToFindAndReplace.put("DIM_E_SGEH_CAUSECODE", "#DIM_E_SGEH_CAUSECODE");
        keysToFindAndReplace.put("DIM_E_LTE_CAUSECODE", "#DIM_E_LTE_CAUSECODE");
        keysToFindAndReplace.put("DIM_E_SGEH_SUBCAUSECODE", "#DIM_E_SGEH_SUBCAUSECODE");
        keysToFindAndReplace.put("DIM_E_LTE_SUBCAUSECODE", "#DIM_E_LTE_SUBCAUSECODE");
        keysToFindAndReplace.put("DIM_E_SGEH_CAUSE_PROT_TYPE", "#DIM_E_SGEH_CAUSE_PROT_TYPE");
        keysToFindAndReplace.put("DIM_E_LTE_CAUSE_PROT_TYPE", "#DIM_E_LTE_CAUSE_PROT_TYPE");
        keysToFindAndReplace.put("DIM_E_SGEH_CC_SCC", "#DIM_E_SGEH_CC_SCC");
        keysToFindAndReplace.put("DIM_E_LTE_CC_SCC", "#DIM_E_LTE_CC_SCC");
        keysToFindAndReplace.put("DIM_E_IMSI_MSISDN", "#DIM_E_IMSI_MSISDN");
        keysToFindAndReplace.put("DIM_E_RAN_CFA", "#DIM_E_RAN_CFA");
        keysToFindAndReplace.put("DIM_E_LTE_CFA_EVENTTYPE", "#DIM_E_LTE_CFA_EVENTTYPE");
        keysToFindAndReplace.put("DIM_E_LTE_CFA_ERAB_SETUP_RESULT", "#DIM_E_LTE_CFA_ERAB_SETUP_RESULT");
        keysToFindAndReplace.put("DIM_E_LTE_CFA_ERAB_SETUP_REQ_PCI", "#DIM_E_LTE_CFA_ERAB_SETUP_REQ_PCI");
        keysToFindAndReplace.put("DIM_E_LTE_CFA_ERAB_SETUP_REQ_PVI", "#DIM_E_LTE_CFA_ERAB_SETUP_REQ_PVI");
        keysToFindAndReplace.put("DIM_E_LTE_CFA_ERAB_SETUP_ATT_ACC_TYPE", "#DIM_E_LTE_CFA_ERAB_SETUP_ATT_ACC_TYPE");
        keysToFindAndReplace.put("DIM_E_LTE_CFA_ERAB_SETUP_FAIL_3GPP_CAUSE_GROUP",
                "#DIM_E_LTE_CFA_ERAB_SETUP_FAIL_3GPP_CAUSE_GROUP");
        keysToFindAndReplace.put("DIM_E_LTE_CFA_ERAB_SETUP_SUCC_ACC_TYPE", "#DIM_E_LTE_CFA_ERAB_SETUP_SUCC_ACC_TYPE");
        keysToFindAndReplace.put("DIM_E_RAN_HFA_EVENTTYPE", "#DIM_E_RAN_HFA_EVENTTYPE");
        keysToFindAndReplace.put("DIM_E_LTE_CFA_CAUSE_CODE", "#DIM_E_LTE_CFA_CAUSE_CODE");
        keysToFindAndReplace.put("DIM_E_LTE_HFA_CAUSE_CODE", "#DIM_E_LTE_HFA_CAUSE_CODE");
        keysToFindAndReplace.put("DIM_E_LTE_CFA_INTERNAL_RELEASE_CAUSE", "#DIM_E_LTE_CFA_INTERNAL_RELEASE_CAUSE");
        keysToFindAndReplace.put("DIM_E_LTE_CFA_RRC_ESTABL_CAUSE", "#DIM_E_LTE_CFA_RRC_ESTABL_CAUSE");
        keysToFindAndReplace.put("DIM_E_LTE_CFA_TRIGGERING_NODE", "#DIM_E_LTE_CFA_TRIGGERING_NODE");
        keysToFindAndReplace.put("DIM_E_LTE_HFA_EVENTTYPE", "#DIM_E_LTE_HFA_EVENTTYPE");
        keysToFindAndReplace.put("DIM_E_LTE_HFA_ERAB_SETUP_REQ_PCI", "#DIM_E_LTE_HFA_ERAB_SETUP_REQ_PCI");
        keysToFindAndReplace.put("DIM_E_LTE_HFA_ERAB_SETUP_REQ_PVI", "#DIM_E_LTE_HFA_ERAB_SETUP_REQ_PVI");
        keysToFindAndReplace.put("DIM_E_RAN_HFA_EVENTTYPE", "#DIM_E_RAN_HFA_EVENTTYPE");
        keysToFindAndReplace.put("DIM_E_RAN_HFA_CATEGORY", "#DIM_E_RAN_HFA_CATEGORY");
        keysToFindAndReplace.put("DIM_E_RAN_HFA_CAUSE_CODE_VALUE", "#DIM_E_RAN_HFA_CAUSE_CODE_VALUE");
        keysToFindAndReplace.put("DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE", "#DIM_E_RAN_HFA_SUB_CAUSE_CODE_VALUE");
        keysToFindAndReplace.put("DIM_Z_SGEH_HIER321_CELL", "#DIM_Z_SGEH_HIER321_CELL");
        keysToFindAndReplace.put("GROUP_TYPE_E_LTE_TRAC", "#GROUP_TYPE_E_LTE_TRAC");
        keysToFindAndReplace.put("DC_Z_ALARM_INFO_RAW", "#DC_Z_ALARM_INFO_RAW");
        keysToFindAndReplace.put("DIM_E_RAN_RABTYPE", TEMP_DIM_E_RAN_RABTYPE);
        keysToFindAndReplace.put("DIM_E_RAN_ECNO_MAPPING", TEMP_DIM_E_RAN_ECNO_MAPPING);
        keysToFindAndReplace.put("DIM_E_RAN_RSCP_MAPPING", TEMP_DIM_E_RAN_RSCP_MAPPING);
        keysToFindAndReplace.put("DIM_E_RAN_ULINT_MAPPING", TEMP_DIM_E_RAN_ULINT_MAPPING);
        keysToFindAndReplace.put("DIM_E_RAN_CFA_DISCONNECTION", TEMP_DIM_E_RAN_CFA_DISCONNECTION);
        keysToFindAndReplace.put("DIM_E_RAN_CFA_RRC_ESTABLISHMENT_CAUSE", TEMP_DIM_E_RAN_CFA_RRC_ESTABLISHMENT_CAUSE);
        keysToFindAndReplace.put("DIM_E_RAN_CFA_TRIGGER_POINT", TEMP_DIM_E_RAN_CFA_TRIGGER_POINT);
        keysToFindAndReplace.put("DIM_E_RAN_CFA_UTRAN_RANAP_CAUSE", TEMP_DIM_E_RAN_CFA_UTRAN_RANAP_CAUSE);
        keysToFindAndReplace.put("DIM_E_RAN_RABTYPE", TEMP_DIM_E_RAN_RABTYPE);
        keysToFindAndReplace.put("DIM_E_RAN_ECNO_MAPPING", TEMP_DIM_E_RAN_ECNO_MAPPING);
        keysToFindAndReplace.put("DIM_E_RAN_RSCP_MAPPING", TEMP_DIM_E_RAN_RSCP_MAPPING);
        keysToFindAndReplace.put("DIM_E_RAN_ULINT_MAPPING", TEMP_DIM_E_RAN_ULINT_MAPPING);
        keysToFindAndReplace.put("DIM_E_LTE_CFA_GUMMEI_TYPE", "#DIM_E_LTE_CFA_GUMMEI_TYPE");
        keysToFindAndReplace.put("DIM_E_LTE_CFA_TTI_BUNDLING_MODE", "#DIM_E_LTE_CFA_TTI_BUNDLING_MODE");
        keysToFindAndReplace.put("##", TEMP_TABLE_PREFIX); //for some 4G templates the parsing happened twice - cleanup by replacing the ##
        extraKeysToFindAndReplace.clear();
        return keysToFindAndReplace;
    }

    private String replaceAll(final String originalText, final String find, final String replace) {
        if (originalText.contains(replace)) {
            return originalText;
        }
        final Pattern pattern = Pattern.compile(find);
        final Matcher matcher = pattern.matcher(originalText);
        return matcher.replaceAll(replace);
    }

    public static void addTableNameToReplace(final String realTableName, final String tempTableName) {
        extraKeysToFindAndReplace.put(realTableName, tempTableName);
    }

    /**
     * Replace all instances of the given table name with its equivalent
     * eg all occurrences of the EVENT_E_SGEH_XXX table name in the query will be replaced with #EVENT_E_SGEH_XXX
     *
     */
    public static String useTemporaryTableFor(final String realTable) {
        final String temporaryTableName = TEMP_TABLE_PREFIX + realTable;
        extraKeysToFindAndReplace.put(realTable, temporaryTableName);
        return temporaryTableName;
    }

}