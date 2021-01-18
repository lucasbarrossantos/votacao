package br.com.votacao.domain.repository;

import br.com.votacao.api.v1.model.ResultadoDTO;
import br.com.votacao.domain.model.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

    Optional<Voto> findByUsuarioIdAndPautaId(Long userId, Long pautaId);

    @Query(nativeQuery = true)
    Optional<ResultadoDTO> resultadoVotacao(Long pautaId);

}
