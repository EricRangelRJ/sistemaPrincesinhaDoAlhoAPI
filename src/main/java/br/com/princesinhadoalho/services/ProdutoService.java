package br.com.princesinhadoalho.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.produtos.ProdutoGetDTO;
import br.com.princesinhadoalho.dtos.produtos.ProdutoPostDTO;
import br.com.princesinhadoalho.dtos.produtos.ProdutoPutDTO;
import br.com.princesinhadoalho.entities.Fornecedor;
import br.com.princesinhadoalho.entities.Produto;
import br.com.princesinhadoalho.exceptions.BadRequestException;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.helpers.DateHelper;
import br.com.princesinhadoalho.repositories.FornecedorRepository;
import br.com.princesinhadoalho.repositories.ProdutoRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ProdutoService {

	private ProdutoRepository produtoRepository;
	private FornecedorRepository fornecedorRepository;

	private ModelMapper mapper;

	public ProdutoGetDTO cadastrar(ProdutoPostDTO dto) {

		Optional<Produto> result = produtoRepository.findByCodigo(dto.getCodigo());

		if (result.isPresent()) {
			throw new BadRequestException("Produto já cadastrado.");
		}

		Produto produto = new Produto();
		mapper.map(dto, produto);
		
		Optional<Fornecedor> result2 = fornecedorRepository.findById(dto.getIdFornecedor());
		
		Fornecedor fornecedor = result2.get();
		produto.setFornecedor(fornecedor);

		produtoRepository.save(produto);

		ProdutoGetDTO getDto = new ProdutoGetDTO();
		mapper.map(produto, getDto);
		getDto.setDataCadastro(DateHelper.toString(produto.getDataCadastro()));
		
		return getDto;
	}

	public List<ProdutoGetDTO> buscarProdutos() {

		List<Produto> list = produtoRepository.findAll();
		List<ProdutoGetDTO> listaGetDto = new ArrayList<ProdutoGetDTO>();

		for (Produto produto : list) {
			ProdutoGetDTO getDto = new ProdutoGetDTO();
			mapper.map(produto, getDto);

			listaGetDto.add(getDto);
		}

		return listaGetDto;
	}
	
	public ProdutoGetDTO buscarId(Integer idProduto) {

		Optional<Produto> result = produtoRepository.findById(idProduto);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Produto não encontrado.");
		}

		Produto produto = result.get();

		ProdutoGetDTO getDto = new ProdutoGetDTO();
		mapper.map(produto, getDto);

		return getDto;
	}
	
	public ProdutoGetDTO atualizar(ProdutoPutDTO dto) {
		
		Optional<Produto> result = produtoRepository.findById(dto.getIdProduto());
		
		if (result.isEmpty()) {
			throw new EntityNotFoundException("Produto não encontrado.");
		}
		
		Produto produto = result.get();
		mapper.map(dto, produto);
		
		produtoRepository.save(produto);
		
		ProdutoGetDTO getDto = new ProdutoGetDTO();
		mapper.map(produto, getDto);
		getDto.setDataCadastro(DateHelper.toString(produto.getDataCadastro()));

		return getDto;
	}

	public String excluir(Integer idProduto) {

		Optional<Produto> result = produtoRepository.findById(idProduto);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Produto não encontrado.");
		}

		Produto produto = result.get();

		produtoRepository.delete(produto);

		return "Produto " + result.get().getNome() + " excluído com sucesso.";
	}

}
