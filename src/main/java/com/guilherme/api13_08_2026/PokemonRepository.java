package com.guilherme.api13_08_2026;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Integer>{

	List<Pokemon> findByNomeContainingIgnoreCase(String nome);

	List<Pokemon> findByUsuarioId(Integer idUsuario);

	long countByUsuarioId(Integer idUsuario);
	
}