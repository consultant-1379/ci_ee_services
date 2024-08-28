package com.ericsson.eniq.events.server.utils;

import java.util.List;

public interface Calculator {

    List<String> calc(List<String> list, final List<String> aExclusiveColumnIndexs) throws NumberFormatException;

    String max();

    String min();

    String MAX_VALUE_DEF = "0";

    String MIN_VALUE_DEF = "0";
}
