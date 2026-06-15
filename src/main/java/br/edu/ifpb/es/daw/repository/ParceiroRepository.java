package br.edu.ifpb.es.daw.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.ifpb.es.daw.model.Parceiro;
import br.edu.ifpb.es.daw.rest.dto.ParceiroBuscarDTO;

@Repository
public interface ParceiroRepository extends JpaRepository<Parceiro, Long> {

	Optional<Parceiro> findByLookupId(UUID lookupId);

	@Query("SELECT p FROM Parceiro p WHERE (:nomeRazaoSocial IS NULL OR LOWER(p.nomeRazaoSocial) LIKE LOWER(CONCAT('%', :nomeRazaoSocial, '%')))")
	Page<Parceiro> buscarPor(String nomeRazaoSocial, Pageable pageable);

	@Query("SELECT p FROM Parceiro p WHERE (:#{#dto.nomeRazaoSocial} IS NULL OR LOWER(p.nomeRazaoSocial) LIKE LOWER(CONCAT('%', :#{#dto.nomeRazaoSocial}, '%')))")
	Page<Parceiro> buscarPor(ParceiroBuscarDTO dto, Pageable pageable);
}
