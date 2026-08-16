package com.guilherme.api13_08_2026;

public class UsuarioNaoEncontradoException extends RuntimeException {

	public UsuarioNaoEncontradoException() {
		super("Usuário não encontrado");
	}

}
