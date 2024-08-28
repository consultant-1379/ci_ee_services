/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.sbr;

/**
 * JSON transformer (temp disabled)
 * @author ehaoswa
 * @since 2012
 *
 */
public class DynaBeanTransformer /*implements ResultSetTransformer<String>*/{

    //    private final String tzOffset;
    //
    //    public DynaBeanTransformer(final String tzOffset) {
    //        this.tzOffset = tzOffset;
    //    }
    //
    //    @Override
    //    public String transform(final List<ResultSet> results) throws SQLException {
    //        throw new UnsupportedOperationException();
    //    }
    //
    //    @Override
    //    public String transform(final ResultSet resultSet) throws SQLException {
    //        final DynaBeans dynaBeans = resultSetToDynaBeans(resultSet);
    //        final RecordCollection collection = convert(dynaBeans);
    //        return serialize(collection, tzOffset);
    //    }
    //
    //    private DynaBeans resultSetToDynaBeans(final ResultSet resultSet) {
    //        //Array to hold resulting dynabeans
    //        DynaBeans result = null;
    //        try {
    //            // To hold copied list
    //            final ResultSetDynaClass resultSetDynaClass = new ResultSetDynaClass(resultSet);
    //            //get the properties from by examining the result set metadata
    //            final DynaProperty[] properties = resultSetDynaClass.getDynaProperties();
    //            //create a new dynabean class description with the properties from the result set
    //            final BasicDynaClass bdc = new BasicDynaClass("Result", BasicDynaBean.class, properties);
    //            //add the extra property
    //            final Iterator rowIterator = resultSetDynaClass.iterator();
    //
    //            final ArrayList<DynaBean> dynaBeans = new ArrayList<DynaBean>();
    //            //iterate over the collection
    //            while (rowIterator.hasNext()) {
    //                final DynaBean oldRow = (DynaBean) rowIterator.next();
    //                //create a new instance of the dynabean
    //                final DynaBean newRow = bdc.newInstance();
    //                //copy the properties between the instances
    //                PropertyUtils.copyProperties(newRow, oldRow);
    //                //add to result set
    //                dynaBeans.add(newRow);
    //            }
    //            result = DynaBeans.create(properties, dynaBeans);
    //        } catch (final SQLException sql) {
    //            ServicesLogger.error(getClass().getName(), "transform", "Failed to iterate the result set", sql);
    //        } catch (final Exception e) {
    //            ServicesLogger.error(getClass().getName(), "transform", "Failed to create dynabean instance", e);
    //        }
    //
    //        return result;
    //    }
    //
    //    private RecordCollection convert(final DynaBeans beans) {
    //        final RecordMetaData description = new RecordMetaData(RecordCategory.SESSION, RecordType.RAB);
    //        final DynaProperty[] properties = beans.getProperties();
    //        for (final DynaProperty property : properties) {
    //            final String name = property.getName();
    //            final Class type = property.getType();
    //            final FieldMetaData metaData = new FieldMetaData(name, type.getName(), "");
    //            description.AddFieldMetaData(metaData);
    //        }
    //
    //        final ArrayList<Object[]> data = new ArrayList<Object[]>();
    //        for (final DynaBean bean : beans.getBeans()) {
    //            final Object[] record = new Object[properties.length];
    //            for (int i = 0, propertiesLength = properties.length; i < propertiesLength; i++) {
    //                final DynaProperty property = properties[i];
    //                final Object value = bean.get(property.getName());
    //                record[i] = value;
    //            }
    //            data.add(record);
    //        }
    //
    //        return new RecordCollection(description, data);
    //    }
    //
    //    private String serialize(final RecordCollection collection, final String tzOffset) {
    //        final ObjectMapper mapper = new ObjectMapper();
    //        final SimpleModule module = new SimpleModule("datetime", new Version(1, 0, 0, ""));
    //        final TimestampSerializer serializer = new TimestampSerializer(tzOffset);
    //        module.addSerializer(serializer);
    //        mapper.registerModule(module);
    //
    //        try {
    //            final String payload = mapper.writeValueAsString(collection);
    //            return JSONUtils.JSONSuccessResult(payload);
    //        } catch (final Exception e) {
    //            ServicesLogger.error(getClass().getName(), "serialize", "Exception Serialising object", e);
    //            return JSONUtils.createJSONErrorResult("Failed to serialize json");
    //        }
    //    }

}
