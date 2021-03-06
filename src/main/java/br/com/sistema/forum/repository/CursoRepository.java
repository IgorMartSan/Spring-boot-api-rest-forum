package br.com.sistema.forum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.sistema.forum.modelo.Curso;

public interface CursoRepository extends JpaRepository<Curso, Long> {

	Curso findByNome(String nomeCurso);



}
