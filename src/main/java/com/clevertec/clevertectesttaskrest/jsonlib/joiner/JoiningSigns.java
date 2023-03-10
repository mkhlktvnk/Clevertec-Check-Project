package com.clevertec.clevertectesttaskrest.jsonlib.joiner;

import com.clevertec.clevertectesttaskrest.jsonlib.constant.JsonSigns;

public enum JoiningSigns {
    SINGLE_OBJECT(JsonSigns.COMMA, JsonSigns.OPENING_BRACE, JsonSigns.CLOSING_BRACE),
    OBJECTS_SEQUENCE(JsonSigns.COMMA, JsonSigns.OPENING_BRACKET, JsonSigns.CLOSING_BRACKET);

    private final String divider;
    private final String prefix;
    private final String suffix;

    JoiningSigns(String divider, String prefix, String suffix) {
        this.divider = divider;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getDivider() {
        return divider;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getSuffix() {
        return suffix;
    }
}
