package com.guilherme.api13_08_2026;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

	private final PokemonRepository pokemonRepository;
	private final UsuarioRepository usuarioRepository;
	private final PokemonMapper pokemonMapper;

	public PokemonService(
			PokemonRepository pokemonRepository,
			UsuarioRepository usuarioRepository,
			PokemonMapper pokemonMapper
	) {
		this.pokemonRepository = pokemonRepository;
		this.usuarioRepository = usuarioRepository;
		this.pokemonMapper = pokemonMapper;
	}

	private Pokemon buscarEntidadePorId(int id) {
		return pokemonRepository
				.findById(id)
				.orElseThrow(PokemonNaoEncontradoException::new);
	}

	public PokemonResponseDTO criarPokemon(PokemonRequestDTO dto) {

		Usuario usuario = usuarioRepository.findById(dto.getIdUsuario())
				.orElseThrow(UsuarioNaoEncontradoException::new);

		long quantidadeDePokemons =
				pokemonRepository.countByUsuarioId(dto.getIdUsuario());

		if (quantidadeDePokemons >= 6) {
			throw new LimiteDePokemonsException();
		}

		Pokemon pokemon = pokemonMapper.paraEntity(dto, usuario);

		Pokemon pokemonSalvo = pokemonRepository.save(pokemon);

		return pokemonMapper.paraResponseDTO(pokemonSalvo);
	}

	public List<PokemonResponseDTO> listarTodos() {
		return pokemonRepository.findAll()
				.stream()
				.map(pokemonMapper::paraResponseDTO)
				.toList();
	}

	public PokemonResponseDTO buscarPokemon(int id) {
		Pokemon pokemon = pokemonRepository
				.findById(id)
				.orElseThrow(PokemonNaoEncontradoException::new);

		return pokemonMapper.paraResponseDTO(pokemon);
	}

	public List<PokemonResponseDTO> buscarPorNome(String nome) {
		return pokemonRepository
				.findByNomeContainingIgnoreCase(nome)
				.stream()
				.map(pokemonMapper::paraResponseDTO)
				.toList();
	}

	public List<PokemonResponseDTO> buscarPorTreinador(int id) {
		return pokemonRepository
				.findByUsuarioId(id)
				.stream()
				.map(pokemonMapper::paraResponseDTO)
				.toList();
	}

	public PokemonResponseDTO atualizarPokemon(int id, PokemonRequestDTO pokemonAtualizadoDTO) {

		Pokemon pokemon = buscarEntidadePorId(id);

		Usuario usuario = usuarioRepository
				.findById(pokemonAtualizadoDTO.getIdUsuario())
				.orElseThrow(UsuarioNaoEncontradoException::new);

		pokemonMapper.atualizarEntity(pokemonAtualizadoDTO, usuario, pokemon);

		Pokemon pokemonAtualizado = pokemonRepository.save(pokemon);

		return pokemonMapper.paraResponseDTO(pokemonAtualizado);
	}

	public void deletarPokemon(int id) {
		Pokemon pokemon = pokemonRepository
				.findById(id)
				.orElseThrow(PokemonNaoEncontradoException::new);

		pokemonRepository.delete(pokemon);
	}


}