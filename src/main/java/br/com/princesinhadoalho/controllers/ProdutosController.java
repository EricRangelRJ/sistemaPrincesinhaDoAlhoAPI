package br.com.princesinhadoalho.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.princesinhadoalho.dtos.produtos.ProdutoGetDTO;
import br.com.princesinhadoalho.dtos.produtos.ProdutoPostDTO;
import br.com.princesinhadoalho.dtos.produtos.ProdutoPutDTO;
import br.com.princesinhadoalho.exceptions.ServiceException;
import br.com.princesinhadoalho.services.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@AllArgsConstructor
@Api(tags = "Menu Produtos")
@RequestMapping(value = "/api/produtos")
public class ProdutosController {

	private final ProdutoService service;

	@PostMapping
	@ApiOperation(value = "Cadastrar produto")
	public ResponseEntity<ProdutoGetDTO> cadastrar(@RequestBody ProdutoPostDTO dto) {

		try {
			ProdutoGetDTO getDto = service.cadastrar(dto);
			return ResponseEntity.status(HttpStatus.CREATED).body(getDto);

		} catch (ServiceException e) {
			log.error("ERRO:", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping
	@ApiOperation(value = "Buscar produtos cadastrados")
	public ResponseEntity<List<ProdutoGetDTO>> buscarProdutos() {

		try {
			List<ProdutoGetDTO> lista = service.buscarProdutos();
			return ResponseEntity.status(HttpStatus.OK).body(lista);

		} catch (ServiceException e) {
			log.error("ERRO:", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{idProduto}")
	@ApiOperation(value = "Buscar produto pelo Id")
	public ResponseEntity<ProdutoGetDTO> buscarId(@PathVariable("idProduto") Integer idProduto) {

		try {
			ProdutoGetDTO getDto = service.buscarId(idProduto);
			return ResponseEntity.ok(getDto);

		} catch (ServiceException e) {
			log.error("ERRO:", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	@ApiOperation(value = "Atualizar produto")
	public ResponseEntity<ProdutoGetDTO> atualizar(@RequestBody ProdutoPutDTO dto) {

		try {
			ProdutoGetDTO getDto = service.atualizar(dto);
			return ResponseEntity.ok(getDto);

		} catch (ServiceException e) {
			log.error("ERRO:", e);
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/{idProduto}")
	@ApiOperation(value = "Excluir produto pelo Id")
	public ResponseEntity<String> excluir(@PathVariable Integer idProduto) {

		try {
			String response = service.excluir(idProduto);
			return ResponseEntity.ok(response);
			
		} catch (ServiceException e) {
			log.error("ERRO:", e);
			return ResponseEntity.internalServerError().body(e.getMessage());
		}
	}

}
