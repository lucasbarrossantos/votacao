package br.com.votacao.domain.model;

import br.com.votacao.domain.model.enums.TipoVoto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Builder
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
public class Voto {

    public Voto() {}

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Usuario usuario;

    @ManyToOne
    private Pauta pauta;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TipoVoto tipoVoto;

}
