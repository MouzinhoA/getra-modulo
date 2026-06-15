package br.edu.ifpb.es.daw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifpb.es.daw.model.Cliente;
import br.edu.ifpb.es.daw.repository.ClienteRepository;
import br.edu.ifpb.es.daw.rest.dto.ClienteBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.ClienteResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.ClienteSalvarRequestDTO;
import br.edu.ifpb.es.daw.mapper.ClienteMapper;

@Service
public class ClienteService {

	private final ClienteRepository repository;
	private final ClienteMapper mapper;

	@Autowired
	public ClienteService(ClienteMapper mapper, ClienteRepository repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	@Transactional
	public ClienteResponseDTO criar(ClienteSalvarRequestDTO dto) {
		Cliente objNovo = mapper.from(dto);
		Cliente objCriado = repository.save(objNovo);
		return mapper.from(objCriado);
	}

	public List<ClienteResponseDTO> recuperarTodos() {
		return repository.findAll()
				.stream()
				.map(mapper::from)
				.toList();
	}

	private Cliente ensureExists(UUID lookupId) {
		Optional<Cliente> objOpt = repository.findByLookupId(lookupId);
		return objOpt.orElseThrow(() -> new IllegalArgumentException(
				String.format("Entidade 'Cliente' de lookupId '%s' não foi encontrada!", lookupId)));
	}

	public ClienteResponseDTO buscarPor(UUID lookupId) {
		Cliente obj = ensureExists(lookupId);
		return mapper.from(obj);
	}

	@Transactional
	public ClienteResponseDTO atualizar(UUID lookupId, ClienteSalvarRequestDTO dto) {
		Cliente objExistente = ensureExists(lookupId);
		objExistente.setNomeRazaoSocial(dto.nomeRazaoSocial());
		objExistente.setCpfCnpj(dto.cpfCnpj());
		objExistente.setEmail(dto.email());
		objExistente.setTelefone(dto.telefone());
		objExistente.setEndereco(dto.endereco());
		Cliente objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}

	@Transactional
	public void remover(UUID lookupId) {
		Optional<Cliente> objOpt = repository.findByLookupId(lookupId);
		objOpt.ifPresent(obj -> repository.delete(obj));
	}

	public Page<ClienteResponseDTO> buscar(ClienteBuscarDTO dto) {
		Page<Cliente> clientePage = repository.buscarPor(dto, PageRequest.of(dto.númeroPágina(), dto.tamanhoPágina()));
		return clientePage.map(mapper::from);
	}
}
