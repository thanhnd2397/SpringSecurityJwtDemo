package com.example.springsecurityjwt.model.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "value")
public class Value {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = true)
    @ToString.Exclude
    private Item item;

    @Column(name = "text")
    private String text;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "ext")
    private String ext;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Value value = (Value) o;
        return id != null && Objects.equals(id, value.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
