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

import br.edu.ifpb.es.daw.rest.dto.ParceiroBuscarDTO;
import br.edu.ifpb.es.daw.rest.dto.ParceiroResponseDTO;
import br.edu.ifpb.es.daw.rest.dto.ParceiroSalvarRequestDTO;
import br.edu.ifpb.es.daw.service.ParceiroService;

@RestController
@RequestMapping("/parceiro")
public class ParceiroRestController {

	private final ParceiroService parceiroService;

	@Autowired
    public ParceiroRestController(ParceiroService parceiroService) {
        this.parceiroService = parceiroService;
    }

	@GetMapping
	public ResponseEntity<List<ParceiroResponseDTO>> listar() {
		List<ParceiroResponseDTO> resultado = parceiroService.recuperarTodos();
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ParceiroResponseDTO> adicionar(@RequestBody @Valid ParceiroSalvarRequestDTO dto) {
		ParceiroResponseDTO resultado = parceiroService.criar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.CREATED);
	}

	@GetMapping("/{lookupId}")
	public ResponseEntity<ParceiroResponseDTO> recuperarPor(@PathVariable UUID lookupId) {
		ParceiroResponseDTO resultado = parceiroService.buscarPor(lookupId);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@PatchMapping("/{lookupId}")
	public ResponseEntity<ParceiroResponseDTO> atualizar(@PathVariable UUID lookupId, @RequestBody @Valid ParceiroSalvarRequestDTO dto) {
		ParceiroResponseDTO resultado = parceiroService.atualizar(lookupId, dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}

	@DeleteMapping("/{lookupId}")
	public ResponseEntity<Void> remover(@PathVariable UUID lookupId) {
		parceiroService.remover(lookupId);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/buscar")
	public ResponseEntity<Page<ParceiroResponseDTO>> buscar(ParceiroBuscarDTO dto) {
		Page<ParceiroResponseDTO> resultado = parceiroService.buscar(dto);
		return new ResponseEntity<>(resultado, HttpStatus.OK);
	}
}
