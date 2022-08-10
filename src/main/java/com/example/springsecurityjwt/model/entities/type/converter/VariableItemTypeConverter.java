package com.example.springsecurityjwt.model.entities.type.converter;

import com.example.springsecurityjwt.model.entities.type.VariableItemType;

import javax.persistence.AttributeConverter;

public class VariableItemTypeConverter implements AttributeConverter<VariableItemType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(VariableItemType attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.getValue();
    }

    @Override
    public VariableItemType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return VariableItemType.fromValue(dbData);
    }

}
