package com.example.springsecurityjwt.model.entities.type;

import java.util.stream.Stream;

public enum VariableItemType {
    TEXT(1), IMAGE(2);

    private Integer value;

    /**
     * @param value
     */
    private VariableItemType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public static VariableItemType fromValue(Integer value) {
        return Stream.of(VariableItemType.values()).filter(c -> c.getValue().equals(value)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
