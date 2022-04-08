package br.com.sistema.forum.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.util.UriComponentsBuilder;

import br.com.sistema.forum.controller.dto.DetalhesDoTopicosDto;
import br.com.sistema.forum.controller.dto.TopicoDto;
import br.com.sistema.forum.controller.form.AtualizacaoTopicoForm;
import br.com.sistema.forum.controller.form.TopicoForm;
import br.com.sistema.forum.modelo.Topico;
import br.com.sistema.forum.repository.CursoRepository;
import br.com.sistema.forum.repository.TopicoRepository;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

	@Autowired
	private TopicoRepository topicoRepository;

	@Autowired
	private CursoRepository cursoRepository;

	@GetMapping
	public List<TopicoDto> lista(String nomeCurso) {// recebe direto na url por um metodo http get ex:
													// http://localhost:8080/topicos?nomecurso=nomeCurso
		System.out.println("---------------------------------------" + nomeCurso);
		if (nomeCurso == null) {
			List<Topico> topicos = topicoRepository.findAll();
			return TopicoDto.converter(topicos);
		} else {
			List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso);
			return TopicoDto.converter(topicos);
		}

	}

	@PostMapping
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm topicoForm,
			UriComponentsBuilder uriBuilder) {
		Topico topico = topicoForm.converteTopicoFormParaTopico(cursoRepository);
		topicoRepository.save(topico);
		URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
		return ResponseEntity.created(uri).body(new TopicoDto(topico));
	}

	@GetMapping("/{id}") // o pathvariable relaciona a variável que vem na url e joga na variável id que
							// foi colocada no como parametro da função detalhar
	public ResponseEntity<DetalhesDoTopicosDto> detalhar(@PathVariable("id") Long id) {

		Optional<Topico> topico = topicoRepository.findById(id);

		if (topico.isPresent()) {
			return ResponseEntity.ok(new DetalhesDoTopicosDto(topico.get()));
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@PutMapping("/{id}") // atualizar um tópico, envia a pela url o id do topico e pelo corpo as
							// atualizações
	@Transactional // todo metodo que tiver um operação de escrita (salvar alterar e atualizar)
					// deve ter o transactional para efetuar o comando para o banco de dados
	public ResponseEntity<TopicoDto> atualizar(@PathVariable("id") Long id,
			@RequestBody @Valid AtualizacaoTopicoForm form) {
		
		Optional<Topico> topicoOptional = topicoRepository.findById(id);// Esse opitional verifica se existe primeiro no bando de dados, se sim e vai realizar a operação
		if (topicoOptional.isPresent()) {
			Topico topico = form.atualizar(id, topicoRepository);
			return ResponseEntity.ok(new TopicoDto(topico));
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}") // deleta com base em um id
	private ResponseEntity<?> publi(@PathVariable("id") Long id) {
		Optional<Topico> topicoOptional = topicoRepository.findById(id);// Esse opitional verifica se existe primeiro no bando de dados, se sim e vai realizar a operação
		if (topicoOptional.isPresent()) {
			topicoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build();
		}		

	}

}
