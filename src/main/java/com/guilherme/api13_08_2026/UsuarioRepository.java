package com.guilherme.api13_08_2026;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{

	List<Usuario> findByNome(String nome);
	
}