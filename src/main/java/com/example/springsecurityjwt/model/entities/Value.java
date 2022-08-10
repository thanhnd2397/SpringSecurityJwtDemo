package com.example.springsecurityjwt.model.entities;

import com.example.springsecurityjwt.model.entities.compositePK.ValueId;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "value")
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = true)
    private Item item;

    @Column(name = "text")
    private String text;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "ext")
    private String ext;
}
