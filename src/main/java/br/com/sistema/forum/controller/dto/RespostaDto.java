package br.com.sistema.forum.controller.dto;

import java.time.LocalDateTime;

import br.com.sistema.forum.modelo.Resposta;

public class RespostaDto {
private long id;
private String mensagem;
private LocalDateTime dataCriacao;
private String nomeAutor;


public RespostaDto(Resposta resposta) {
	this.id = resposta.getId();
	this.mensagem = resposta.getMensagem();
	this.dataCriacao = resposta.getDataCriacao();
	this.nomeAutor = resposta.getAutor().getNome();
}



}
