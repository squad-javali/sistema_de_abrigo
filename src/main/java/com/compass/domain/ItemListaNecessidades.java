package com.compass.domain;

import java.io.Serial;
import java.io.Serializable;
import lombok.*;
import jakarta.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "lista_necess")
public class ItemListaNecessidades implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name="id_produto")
    private Integer idProduto;

    @ManyToOne
    @JoinColumn(name="id_lista")
    private Integer idLista;

    private Integer quantidade;


}
