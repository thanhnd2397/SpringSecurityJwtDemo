package com.example.springsecurityjwt.model.entities.compositePK;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class ValueId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "id")
    private Integer id;

    @Column(name = "item_id")
    private Integer itemId;
}
