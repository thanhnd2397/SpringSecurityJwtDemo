package com.example.springsecurityjwt.model.entities;

import com.example.springsecurityjwt.model.entities.type.VariableItemType;
import com.example.springsecurityjwt.model.entities.type.converter.VariableItemTypeConverter;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    @Convert(converter = VariableItemTypeConverter.class)
    private VariableItemType type;
}
