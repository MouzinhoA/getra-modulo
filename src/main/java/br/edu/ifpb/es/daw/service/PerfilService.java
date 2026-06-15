package br.edu.ifpb.es.daw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifpb.es.daw.model.Perfil;
import br.edu.ifpb.es.daw.repository.PerfilRepository;
import br.edu.ifpb.es.daw.rest.dto.PerfilBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.PerfilResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.PerfilSalvarRequestDTO;
import br.edu.ifpb.es.daw.mapper.PerfilMapper;

@Service
public class PerfilService {

	private final PerfilRepository repository;
	private final PerfilMapper mapper;

	@Autowired
	public PerfilService(PerfilMapper mapper, PerfilRepository repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	@Transactional
	public PerfilResponseDTO criar(PerfilSalvarRequestDTO dto) {
		Perfil objNovo = mapper.from(dto);
		Perfil objCriado = repository.save(objNovo);
		return mapper.from(objCriado);
	}

	public List<PerfilResponseDTO> recuperarTodos() {
		return repository.findAll()
				.stream()
				.map(mapper::from)
				.toList();
	}

	private Perfil ensureExists(UUID lookupId) {
		Optional<Perfil> objOpt = repository.findByLookupId(lookupId);
		return objOpt.orElseThrow(() -> new IllegalArgumentException(
				String.format("Entidade 'Perfil' de lookupId '%s' não foi encontrada!", lookupId)));
	}

	public PerfilResponseDTO buscarPor(UUID lookupId) {
		Perfil obj = ensureExists(lookupId);
		return mapper.from(obj);
	}

	@Transactional
	public PerfilResponseDTO atualizar(UUID lookupId, PerfilSalvarRequestDTO dto) {
		Perfil objExistente = ensureExists(lookupId);
		objExistente.setNome(dto.nome());
		objExistente.setPermissoes(dto.permissoes());
		Perfil objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}

	@Transactional
	public void remover(UUID lookupId) {
		Optional<Perfil> objOpt = repository.findByLookupId(lookupId);
		objOpt.ifPresent(obj -> repository.delete(obj));
	}

	public Page<PerfilResponseDTO> buscar(PerfilBuscarDTO dto) {
		Page<Perfil> perfilPage = repository.buscarPor(dto, PageRequest.of(dto.númeroPágina(), dto.tamanhoPágina()));
		return perfilPage.map(mapper::from);
	}
}
