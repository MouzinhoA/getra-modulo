package br.edu.ifpb.es.daw.rest;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpb.es.daw.rest.dto.NotaFiscalBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.NotaFiscalResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.NotaFiscalSalvarRequestDTO;
import br.edu.ifpb.es.daw.service.NotaFiscalService;

@RestController
@RequestMapping("/nota-fiscal")
public class NotaFiscalRestController {

	private final NotaFiscalService notaFiscalService;

	@Autowired
    public NotaFiscalRestController(NotaFiscalService notaFiscalService) {
        this.notaFiscalService = notaFiscalService;
    }

	@GetMapping
	public ResponseEntity<List<NotaFiscalResponseDTO>> listar() {
		List<NotaFiscalResponseDTO> resultado = notaFiscalService.recuperarTodos();
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<NotaFiscalResponseDTO> adicionar(@RequestBody @Valid NotaFiscalSalvarRequestDTO dto) {
		NotaFiscalResponseDTO resultado = notaFiscalService.criar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}

	@GetMapping("/{lookupId}")
	public ResponseEntity<NotaFiscalResponseDTO> recuperarPor(@PathVariable UUID lookupId) {
		NotaFiscalResponseDTO resultado = notaFiscalService.buscarPor(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}")
	public ResponseEntity<NotaFiscalResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid NotaFiscalSalvarRequestDTO dto) {
		NotaFiscalResponseDTO resultado = notaFiscalService.atualizar(lookupId, dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@DeleteMapping("/{lookupId}")
	public ResponseEntity<Void> remover(@PathVariable UUID lookupId) {
		notaFiscalService.remover(lookupId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/buscar")
	public ResponseEntity<Page<NotaFiscalResponseDTO>> buscar(NotaFiscalBuscarDTO dto) {
		Page<NotaFiscalResponseDTO> resultado = notaFiscalService.buscar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}/autorizar")
	public ResponseEntity<NotaFiscalResponseDTO> autorizar(@PathVariable UUID lookupId) {
		NotaFiscalResponseDTO resultado = notaFiscalService.autorizar(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}/cancelar")
	public ResponseEntity<NotaFiscalResponseDTO> cancelar(@PathVariable UUID lookupId) {
		NotaFiscalResponseDTO resultado = notaFiscalService.cancelar(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
}
