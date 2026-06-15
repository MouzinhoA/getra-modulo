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

import br.edu.ifpb.es.daw.rest.dto.RecorrenciaBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.RecorrenciaResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.RecorrenciaSalvarRequestDTO;
import br.edu.ifpb.es.daw.service.RecorrenciaService;

@RestController
@RequestMapping("/recorrencia")
public class RecorrenciaRestController {

	private final RecorrenciaService recorrenciaService;

	@Autowired
    public RecorrenciaRestController(RecorrenciaService recorrenciaService) {
        this.recorrenciaService = recorrenciaService;
    }

	@GetMapping
	public ResponseEntity<List<RecorrenciaResponseDTO>> listar() {
		List<RecorrenciaResponseDTO> resultado = recorrenciaService.recuperarTodos();
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<RecorrenciaResponseDTO> adicionar(@RequestBody @Valid RecorrenciaSalvarRequestDTO dto) {
		RecorrenciaResponseDTO resultado = recorrenciaService.criar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}

	@GetMapping("/{lookupId}")
	public ResponseEntity<RecorrenciaResponseDTO> recuperarPor(@PathVariable UUID lookupId) {
		RecorrenciaResponseDTO resultado = recorrenciaService.buscarPor(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}")
	public ResponseEntity<RecorrenciaResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid RecorrenciaSalvarRequestDTO dto) {
		RecorrenciaResponseDTO resultado = recorrenciaService.atualizar(lookupId, dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@DeleteMapping("/{lookupId}")
	public ResponseEntity<Void> remover(@PathVariable UUID lookupId) {
		recorrenciaService.remover(lookupId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/buscar")
	public ResponseEntity<Page<RecorrenciaResponseDTO>> buscar(RecorrenciaBuscarDTO dto) {
		Page<RecorrenciaResponseDTO> resultado = recorrenciaService.buscar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}/ativar")
	public ResponseEntity<RecorrenciaResponseDTO> ativar(@PathVariable UUID lookupId) {
		RecorrenciaResponseDTO resultado = recorrenciaService.ativar(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}/desativar")
	public ResponseEntity<RecorrenciaResponseDTO> desativar(@PathVariable UUID lookupId) {
		RecorrenciaResponseDTO resultado = recorrenciaService.desativar(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
}
