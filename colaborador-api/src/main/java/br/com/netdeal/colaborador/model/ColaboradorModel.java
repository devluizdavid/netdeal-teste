package br.com.netdeal.colaborador.model;

import java.math.BigDecimal;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import org.hibernate.envers.Audited;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "colaborador")
@Audited
public class ColaboradorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "{name.notempty}")
    private String nome;

    @NotEmpty(message = "{name.notempty}")
    private String senha;

    private BigDecimal score;

    @ManyToOne
    @JoinColumn(name = "colaborador_pai_id")
    private ColaboradorModel colaboradorPai;

}
