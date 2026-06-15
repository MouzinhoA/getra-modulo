package br.edu.ifpb.es.daw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifpb.es.daw.model.Parceiro;
import br.edu.ifpb.es.daw.repository.ParceiroRepository;
import br.edu.ifpb.es.daw.rest.dto.ParceiroBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.ParceiroResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.ParceiroSalvarRequestDTO;
import br.edu.ifpb.es.daw.mapper.ParceiroMapper;

@Service
public class ParceiroService {

	private final ParceiroRepository repository;
	private final ParceiroMapper mapper;

	@Autowired
	public ParceiroService(ParceiroMapper mapper, ParceiroRepository repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	@Transactional
	public ParceiroResponseDTO criar(ParceiroSalvarRequestDTO dto) {
		Parceiro objNovo = mapper.from(dto);
		Parceiro objCriado = repository.save(objNovo);
		return mapper.from(objCriado);
	}

	public List<ParceiroResponseDTO> recuperarTodos() {
		return repository.findAll()
				.stream()
				.map(mapper::from)
				.toList();
	}

	private Parceiro ensureExists(UUID lookupId) {
		Optional<Parceiro> objOpt = repository.findByLookupId(lookupId);
		return objOpt.orElseThrow(() -> new IllegalArgumentException(
				String.format("Entidade 'Parceiro' de lookupId '%s' não foi encontrada!", lookupId)));
	}

	public ParceiroResponseDTO buscarPor(UUID lookupId) {
		Parceiro obj = ensureExists(lookupId);
		return mapper.from(obj);
	}

	@Transactional
	public ParceiroResponseDTO atualizar(UUID lookupId, ParceiroSalvarRequestDTO dto) {
		Parceiro objExistente = ensureExists(lookupId);
		objExistente.setNomeRazaoSocial(dto.nomeRazaoSocial());
		objExistente.setCpfCnpj(dto.cpfCnpj());
		objExistente.setEmailContato(dto.emailContato());
		objExistente.setDadosBancariosPix(dto.dadosBancariosPix());
		Parceiro objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}

	@Transactional
	public void remover(UUID lookupId) {
		Optional<Parceiro> objOpt = repository.findByLookupId(lookupId);
		objOpt.ifPresent(obj -> repository.delete(obj));
	}

	public Page<ParceiroResponseDTO> buscar(ParceiroBuscarDTO dto) {
		Page<Parceiro> parceiroPage = repository.buscarPor(dto, PageRequest.of(dto.númeroPágina(), dto.tamanhoPágina()));
		return parceiroPage.map(mapper::from);
	}
}
