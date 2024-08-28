package com.ericsson.eniq.events.server.utils.sbr;


/**
 * Created by IntelliJ IDEA.
 * User: eeidpar
 * Date: 22/02/12
 * Time: 08:28
 * To change this template use File | Settings | File Templates.
 */
public interface IObjectmapProcessor<T> {

    Object processObjectMap(T objectMap);
}
