package br.com.sistema.forum.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import br.com.sistema.forum.modelo.Topico;

public class TopicoDto {
	private long id;
	private String titulo;
	private String mensagem;
	private LocalDateTime dataCriacao;

	public TopicoDto(Topico topico) {
		super();
		this.id = topico.getId();
		this.titulo = topico.getTitulo();
		this.mensagem = topico.getMensagem();
		this.dataCriacao = topico.getDataCriacao();
	}

	public long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getMensagem() {
		return mensagem;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public static List<TopicoDto> converter(List<Topico> topicos) {
		// List<TopicoDto> list = new ArrayList<TopicoDto>();
		// for (Topico topico : topicos) {
		// TopicoDto topicoDto = new TopicoDto(topico);
		// list.add(topicoDto);
		// }

		// java 8 pode fazer assim, é a mesma coisa do algoritimo acima
		return topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
		// return list;

	}

}
