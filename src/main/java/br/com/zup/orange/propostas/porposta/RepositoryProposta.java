package br.com.zup.orange.propostas.porposta;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryProposta extends JpaRepository<Proposta, Long> {

    Optional<Proposta> findByDocumento(String documento);
}
