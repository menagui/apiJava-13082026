package com.guilherme.api13_08_2026;

public class PokemonNaoEncontradoException extends RuntimeException {

	public PokemonNaoEncontradoException() {
		super("Pokémon não encontrado");
	}
}