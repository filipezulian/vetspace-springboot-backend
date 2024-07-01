package com.pin.vetspace.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Table(name = "relatorio_consulta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelatorioConsulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "relatorio_id")
    private Long id;

    @Column(name = "material", columnDefinition = "integer[]")
    private int[] material;

    @ManyToOne
    @JoinColumn(name = "consulta", referencedColumnName = "consulta_id")
    private Consulta consulta;

    @Column(name = "servico", columnDefinition = "integer[]")
    private int[] servico;

    @ManyToOne
    @JoinColumn(name = "funcionario", referencedColumnName = "user_id")
    private Usuario funcionario;

    @Column(name = "valor_total")
    private Double valorTotal;
}
