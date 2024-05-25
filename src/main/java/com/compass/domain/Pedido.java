package com.compass.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_abrigo")
    private Abrigo abrigo;

    @ManyToOne
    @JoinColumn(name = "id_centro")
    private CentroDeDistribuicao centro;

    private LocalDate data_pedido;
    private boolean aceite;
    private String motivoRecusa;

}
