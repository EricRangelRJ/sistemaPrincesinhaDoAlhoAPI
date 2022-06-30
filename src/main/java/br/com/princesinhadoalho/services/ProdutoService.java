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

	private final ProdutoRepository produtoRepository;
	private final FornecedorRepository fornecedorRepository;

	private ModelMapper mapper;

	public ProdutoGetDTO cadastrar(ProdutoPostDTO dto) {

		
		Optional<Produto> result = produtoRepository.findByCodigo(dto.getCodigo());
		if (result.isPresent()) {
			throw new BadRequestException("Produto já cadastrado.");
		}
		
		Optional<Fornecedor> result2 = fornecedorRepository.findById(dto.getIdFornecedor());	
		if (result2.isEmpty()) {
			throw new EntityNotFoundException("Fornecedor não encontrado.");
		}
		
		Fornecedor fornecedor = result2.get();
		
		Produto produto = new Produto();
		
		produto.setNome(dto.getNome());
		produto.setCodigo(dto.getCodigo());
		produto.setDescricao(dto.getDescricao());
		produto.setDataCadastro(DateHelper.toDate(dto.getDataCadastro()));
		produto.setAtivo(false);
		produto.setPeso(25.5);
		produto.setValorCusto(10.0);
		produto.setValorVenda(15.0);
		produto.setMargemLucro(5.0);
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
