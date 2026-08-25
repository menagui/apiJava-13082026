package com.guilherme.api13_08_2026;

public class LimiteDePokemonsException extends RuntimeException {

	public LimiteDePokemonsException() {
		super("O usuário já possui 6 Pokemons na sua party");
	}
}