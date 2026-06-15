package br.edu.ifpb.es.daw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifpb.es.daw.model.Servico;
import br.edu.ifpb.es.daw.repository.ServicoRepository;
import br.edu.ifpb.es.daw.rest.dto.ServicoBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.ServicoResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.ServicoSalvarRequestDTO;
import br.edu.ifpb.es.daw.mapper.ServicoMapper;

@Service
public class ServicoService {

	private final ServicoRepository repository;
	private final ServicoMapper mapper;

	@Autowired
	public ServicoService(ServicoMapper mapper, ServicoRepository repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	@Transactional
	public ServicoResponseDTO criar(ServicoSalvarRequestDTO dto) {
		Servico objNovo = mapper.from(dto);
		Servico objCriado = repository.save(objNovo);
		return mapper.from(objCriado);
	}

	public List<ServicoResponseDTO> recuperarTodos() {
		return repository.findAll()
				.stream()
				.map(mapper::from)
				.toList();
	}

	private Servico ensureExists(UUID lookupId) {
		Optional<Servico> objOpt = repository.findByLookupId(lookupId);
		return objOpt.orElseThrow(() -> new IllegalArgumentException(
				String.format("Entidade 'Servico' de lookupId '%s' não foi encontrada!", lookupId)));
	}

	public ServicoResponseDTO buscarPor(UUID lookupId) {
		Servico obj = ensureExists(lookupId);
		return mapper.from(obj);
	}

	@Transactional
	public ServicoResponseDTO atualizar(UUID lookupId, ServicoSalvarRequestDTO dto) {
		Servico objExistente = ensureExists(lookupId);
		objExistente.setNome(dto.nome());
		objExistente.setDescricao(dto.descrição());
		objExistente.setValorPadrao(dto.valorPadrao());
		Servico objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}

	@Transactional
	public void remover(UUID lookupId) {
		Optional<Servico> objOpt = repository.findByLookupId(lookupId);
		objOpt.ifPresent(obj -> repository.delete(obj));
	}

	public Page<ServicoResponseDTO> buscar(ServicoBuscarDTO dto) {
		Page<Servico> servicoPage = repository.buscarPor(dto, PageRequest.of(dto.númeroPágina(), dto.tamanhoPágina()));
		return servicoPage.map(mapper::from);
	}
}
