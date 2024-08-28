/**
 * -----------------------------------------------------------------------
 *     Copyright (C) 2012 LM Ericsson Limited.  All rights reserved.
 * -----------------------------------------------------------------------
 */
package com.ericsson.eniq.events.server.utils.sbr;


/**
 * Timestamp Serilizer
 * @author ehaoswa
 * @since 2012
 *
 */
//Temp disable
public class TimestampSerializer {
    //extends SerializerBase<Timestamp> {

    //    private final SerializerBase<Object> DEFAULT = new StdKeySerializer();
    //
    //    private final SimpleDateFormat dateFormat;
    //
    //    public TimestampSerializer(final String tzOffset) {
    //        super(Timestamp.class);
    //        dateFormat = new SimpleDateFormat(ApplicationConstants.DATE_TIME_FORMAT + ":ss:SSS");
    //        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT" + tzOffset));
    //    }
    //
    //    @Override
    //    public void serialize(final Timestamp timestamp, final JsonGenerator jsonGenerator,
    //            final SerializerProvider serializerProvider) throws IOException {
    //        final String formattedDate = dateFormat.format(timestamp);
    //        jsonGenerator.writeString(formattedDate);
    //    }
    //
    //    @Override
    //    public JsonNode getSchema(final SerializerProvider provider, final Type typeHint) throws JsonMappingException {
    //        return DEFAULT.getSchema(provider, typeHint);
    //    }
}
