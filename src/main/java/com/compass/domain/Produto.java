package com.compass.domain;

import com.compass.domain.enums.RoupaSexo;
import com.compass.domain.enums.RoupaTamanho;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "produto", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"descricao", "tamanho", "sexo"})
})
public class Produto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoupaTamanho tamanho;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoupaSexo sexo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
