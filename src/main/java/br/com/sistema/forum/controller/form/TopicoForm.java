package br.com.sistema.forum.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.sistema.forum.modelo.Curso;
import br.com.sistema.forum.modelo.Topico;
import br.com.sistema.forum.repository.CursoRepository;

public class TopicoForm {
	@NotNull @NotEmpty @Length(min=5)
	private String titulo;
	
	@NotNull @NotEmpty @Length(min=10)
	private String mensagem;
	
	@NotNull @NotEmpty
	private String nomeCurso;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNomeCurso() {
		return nomeCurso;
	}

	public void setNomeCurso(String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Topico converteTopicoFormParaTopico(CursoRepository CursoRepository) {
		Curso curso = CursoRepository.findByNome(nomeCurso);
		Topico topico = new Topico(titulo,mensagem,curso);
		return topico;

	}

}
