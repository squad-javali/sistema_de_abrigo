package com.compass.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "estoque", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id_produto", "id_centro"})
})
public class Estoque implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_produto",nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_centro",nullable = false)
    @ToString.Exclude
    private CentroDeDistribuicao centro;

    private int quantidade;
    private int capacidade;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estoque estoque = (Estoque) o;
        return Objects.equals(id, estoque.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}