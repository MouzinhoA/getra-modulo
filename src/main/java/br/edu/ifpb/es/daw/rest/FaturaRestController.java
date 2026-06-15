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

import br.edu.ifpb.es.daw.rest.dto.FaturaBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.FaturaResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.FaturaSalvarRequestDTO;
import br.edu.ifpb.es.daw.service.FaturaService;

@RestController
@RequestMapping("/fatura")
public class FaturaRestController {

	private final FaturaService faturaService;

	@Autowired
    public FaturaRestController(FaturaService faturaService) {
        this.faturaService = faturaService;
    }

	@GetMapping
	public ResponseEntity<List<FaturaResponseDTO>> listar() {
		List<FaturaResponseDTO> resultado = faturaService.recuperarTodos();
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<FaturaResponseDTO> adicionar(@RequestBody @Valid FaturaSalvarRequestDTO dto) {
		FaturaResponseDTO resultado = faturaService.criar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}

	@GetMapping("/{lookupId}")
	public ResponseEntity<FaturaResponseDTO> recuperarPor(@PathVariable UUID lookupId) {
		FaturaResponseDTO resultado = faturaService.buscarPor(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}")
	public ResponseEntity<FaturaResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid FaturaSalvarRequestDTO dto) {
		FaturaResponseDTO resultado = faturaService.atualizar(lookupId, dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@DeleteMapping("/{lookupId}")
	public ResponseEntity<Void> remover(@PathVariable UUID lookupId) {
		faturaService.remover(lookupId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/buscar")
	public ResponseEntity<Page<FaturaResponseDTO>> buscar(FaturaBuscarDTO dto) {
		Page<FaturaResponseDTO> resultado = faturaService.buscar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}/pagar")
	public ResponseEntity<FaturaResponseDTO> pagar(@PathVariable UUID lookupId) {
		FaturaResponseDTO resultado = faturaService.pagar(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}/cancelar")
	public ResponseEntity<FaturaResponseDTO> cancelar(@PathVariable UUID lookupId) {
		FaturaResponseDTO resultado = faturaService.cancelar(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
}
