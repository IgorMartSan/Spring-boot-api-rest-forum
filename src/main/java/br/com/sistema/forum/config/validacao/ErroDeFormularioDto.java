package br.com.sistema.forum.config.validacao;

public class ErroDeFormularioDto {
	private String Compo;
	private String Erro;
	
	
	public ErroDeFormularioDto(String compo, String erro) {
		Compo = compo;
		Erro = erro;
	}


	public String getCompo() {
		return Compo;
	}


	public String getErro() {
		return Erro;
	}


	

}
