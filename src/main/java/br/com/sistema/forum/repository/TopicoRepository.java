package br.com.sistema.forum.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.sistema.forum.modelo.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{

	List<Topico> findByTitulo(String nomeCurso);

	List<Topico> findByCurso_Nome(String nomeCurso);
	//@Query("SELECT * FROM Topico t WHERE t.curso.nome = :nomeCurso")
	//List<Topico> carregarPorNomeDoCurso(String nomeCurso);

}
