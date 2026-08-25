package com.guilherme.api13_08_2026;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PostMapping
    public ResponseEntity<PokemonResponseDTO> criar(
            @Valid @RequestBody PokemonRequestDTO dto
    ) {
        PokemonResponseDTO pokemonCriado =
                pokemonService.criarPokemon(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pokemonCriado);
    }

    @GetMapping
    public ResponseEntity<List<PokemonResponseDTO>> listarTodos() {
        List<PokemonResponseDTO> pokemons =
                pokemonService.listarTodos();

        return ResponseEntity.ok(pokemons);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<PokemonResponseDTO>> buscarPorNome(
            @RequestParam String nome
    ) {
        List<PokemonResponseDTO> pokemons =
                pokemonService.buscarPorNome(nome);

        return ResponseEntity.ok(pokemons);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PokemonResponseDTO> buscarPorId(
            @PathVariable int id
    ) {
        PokemonResponseDTO pokemon =
                pokemonService.buscarPokemon(id);

        return ResponseEntity.ok(pokemon);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonResponseDTO> atualizarPokemon(@PathVariable int id,  @Valid @RequestBody PokemonRequestDTO pokemonAtualizadoDTO) {

        PokemonResponseDTO pokemon = pokemonService.atualizarPokemon(id, pokemonAtualizadoDTO);

        return ResponseEntity.ok(pokemon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        pokemonService.deletarPokemon(id);

        return ResponseEntity.noContent().build();
    }
}