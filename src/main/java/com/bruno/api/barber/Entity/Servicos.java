package com.bruno.api.barber.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "servicos")
@Entity
public class Servicos {
    private Long id;
    private String nome;
    private Double preco;
    private int duracaoEmMinutos;
}
