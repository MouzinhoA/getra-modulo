package br.edu.ifpb.es.daw.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.edu.ifpb.es.daw.exception.EstadoInválidoException;
import br.edu.ifpb.es.daw.model.Fatura;
import br.edu.ifpb.es.daw.model.NotaFiscal;
import br.edu.ifpb.es.daw.repository.NotaFiscalRepository;
import br.edu.ifpb.es.daw.rest.dto.NotaFiscalBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.NotaFiscalResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.NotaFiscalSalvarRequestDTO;
import br.edu.ifpb.es.daw.mapper.NotaFiscalMapper;

@Service
public class NotaFiscalService {

	private final NotaFiscalRepository repository;
	private final NotaFiscalMapper mapper;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public NotaFiscalService(NotaFiscalMapper mapper, NotaFiscalRepository repository) {
		this.mapper = mapper;
		this.repository = repository;
	}

	@Transactional
	public NotaFiscalResponseDTO criar(NotaFiscalSalvarRequestDTO dto) {
		NotaFiscal objNovo = mapper.from(dto);
		NotaFiscal objCriado = repository.save(objNovo);
		return mapper.from(objCriado);
	}

	public List<NotaFiscalResponseDTO> recuperarTodos() {
		return repository.findAll()
				.stream()
				.map(mapper::from)
				.toList();
	}

	private NotaFiscal ensureExists(UUID lookupId) {
		Optional<NotaFiscal> objOpt = repository.findByLookupId(lookupId);
		return objOpt.orElseThrow(() -> new IllegalArgumentException(
				String.format("Entidade 'NotaFiscal' de lookupId '%s' não foi encontrada!", lookupId)));
	}

	public NotaFiscalResponseDTO buscarPor(UUID lookupId) {
		NotaFiscal obj = ensureExists(lookupId);
		return mapper.from(obj);
	}

	@Transactional
	public NotaFiscalResponseDTO atualizar(UUID lookupId, NotaFiscalSalvarRequestDTO dto) {
		NotaFiscal objExistente = ensureExists(lookupId);
		if ("AUTORIZADA".equalsIgnoreCase(objExistente.getStatusApi())) {
			throw new EstadoInválidoException("Não pode editar uma nota fiscal já autorizada.");
		}
		objExistente.setNumero(dto.numero());
		objExistente.setDataEmissao(dto.dataEmissao());
		objExistente.setValorTotal(dto.valorTotal());
		objExistente.setStatusApi(dto.statusApi());
		objExistente.setIdExtGovApi(dto.idExtGovApi());
		objExistente.setLinkXml(dto.linkXml());
		objExistente.setLinkPdf(dto.linkPdf());
		objExistente.setFatura(entityManager.getReference(Fatura.class, dto.idFatura()));
		NotaFiscal objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}

	@Transactional
	public void remover(UUID lookupId) {
		Optional<NotaFiscal> objOpt = repository.findByLookupId(lookupId);
		objOpt.ifPresent(obj -> repository.delete(obj));
	}

	public Page<NotaFiscalResponseDTO> buscar(NotaFiscalBuscarDTO dto) {
		Page<NotaFiscal> notaPage = repository.buscarPor(dto, PageRequest.of(dto.númeroPágina(), dto.tamanhoPágina()));
		return notaPage.map(mapper::from);
	}

	@Transactional
	public NotaFiscalResponseDTO autorizar(UUID lookupId) {
		NotaFiscal objExistente = ensureExists(lookupId);
		if ("AUTORIZADA".equalsIgnoreCase(objExistente.getStatusApi())) {
			return mapper.from(objExistente);
		}
		objExistente.setStatusApi("AUTORIZADA");
		NotaFiscal objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}

	@Transactional
	public NotaFiscalResponseDTO cancelar(UUID lookupId) {
		NotaFiscal objExistente = ensureExists(lookupId);
		if ("CANCELADA".equalsIgnoreCase(objExistente.getStatusApi())) {
			return mapper.from(objExistente);
		}
		objExistente.setStatusApi("CANCELADA");
		NotaFiscal objAtualizado = repository.save(objExistente);
		return mapper.from(objAtualizado);
	}
}
