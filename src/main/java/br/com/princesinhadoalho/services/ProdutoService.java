package br.com.princesinhadoalho.services;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.produtos.ProdutoGetDTO;
import br.com.princesinhadoalho.dtos.produtos.ProdutoPostDTO;
import br.com.princesinhadoalho.dtos.produtos.ProdutoPutDTO;
import br.com.princesinhadoalho.entities.Fornecedor;
import br.com.princesinhadoalho.entities.Produto;
import br.com.princesinhadoalho.exceptions.BadRequestException;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.helpers.RandomHelper;
import br.com.princesinhadoalho.repositories.FornecedorRepository;
import br.com.princesinhadoalho.repositories.ProdutoRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ProdutoService {

	private final ProdutoRepository produtoRepository;
	private final FornecedorRepository fornecedorRepository;

	// CADASTRAR
	public ProdutoGetDTO cadastrar(ProdutoPostDTO dto) {
		
		if(dto.getValorVenda() < dto.getValorCusto()) {
			throw new BadRequestException("O valor de venda não pode ser inferior ao valor de custo.");
		}
		
		Optional<Produto> result = produtoRepository.findByNomeProdutoAndDescricaoAndPesoAndValorVenda(
				dto.getNomeProduto(), dto.getDescricao(), dto.getPeso(), dto.getValorVenda());
		
		
		if (result.isPresent()) {
			throw new BadRequestException("Produto já cadastrado. cód: " + result.get().getCodigo());
		}

		Optional<Fornecedor> fornec = fornecedorRepository.findById(dto.getIdFornecedor());
		
		if (fornec.isEmpty()) {
			throw new EntityNotFoundException("Fornecedor não encontrado.");
		}
		
		Fornecedor fornecedor = fornec.get();
		String codigo = RandomHelper.gerarCodigoProdutoAleatorio();
		Date dataCadastro = Date.from(Instant.now());
	
		Produto produto = new Produto(dto.getNomeProduto(), codigo, dto.getDescricao(), dataCadastro, 
				dto.getAtivo(), dto.getPeso(),dto.getValorCusto(), dto.getValorVenda(), fornecedor);
	
		produtoRepository.save(produto);

		return new ProdutoGetDTO(produto);
	}

	// BUSCAR TODOS OS PRODUTOS
	public List<ProdutoGetDTO> buscarProdutos() {

		List<Produto> list = produtoRepository.findAll();
		List<ProdutoGetDTO> listaGetDto = new ArrayList<ProdutoGetDTO>();

		for (Produto produto : list) {
			ProdutoGetDTO getDto = new ProdutoGetDTO(produto);

			listaGetDto.add(getDto);
		}

		return listaGetDto;
	}
	
	// BUSCAR UM PRODUTO
	public ProdutoGetDTO buscarId(Integer idProduto) {

		Optional<Produto> result = produtoRepository.findById(idProduto);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Produto não encontrado.");
		}

		Produto produto = result.get();

		return new ProdutoGetDTO(produto);
	}

	// ATUALIZAR UM PRODUTO
	public ProdutoGetDTO atualizar(ProdutoPutDTO dto) {

		if(dto.getValorVenda() < dto.getValorCusto()) {
			throw new BadRequestException("O valor de venda não pode ser inferior ao valor de custo.");
		}
		
		Optional<Produto> result = produtoRepository.findById(dto.getIdProduto());

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Produto não encontrado.");
		}
		
		Optional<Fornecedor> fornec = fornecedorRepository.findById(dto.getIdFornecedor());
		
		if (fornec.isEmpty()) {
			throw new EntityNotFoundException("Fornecedor não encontrado.");
		}
		Fornecedor fornecedor = fornec.get();
		
		Produto produto = result.get();
		produto.atualizar(dto.getNomeProduto(), dto.getDescricao(), dto.getAtivo(), dto.getPeso(),
				dto.getValorCusto(), dto.getValorVenda(), fornecedor);
		
		produtoRepository.save(produto);

		return new ProdutoGetDTO(produto);
	}

	// EXCLUIR UM PRODUTO
	public String excluir(Integer idProduto) {

		Optional<Produto> result = produtoRepository.findById(idProduto);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Produto não encontrado.");
		}

		Produto produto = result.get();
		
	 /*//  MÉTODO PARA EXCLUIR O PRODUTO E SEU FORNECEDOR, CASO O PRODUTO SEJA ÚNICO 
	  
	   Fornecedor fornecedor = produto.getFornecedor();
		Set<Produto> produtos =	fornecedor.getProdutos();
		
		if(produtos.size() == 1) {
			produtoRepository.delete(produto);
			
			if (fornecedor.getEndereco() != null) {
				enderecoService.excluir(fornecedor.getEndereco().getIdEndereco());
			}
			fornecedorRepository.delete(fornecedor);

			return "Produto " + result.get().getNomeProduto() + " excluído com sucesso.";
		}
	*/
		// excluindo apenas o produto 
		produtoRepository.delete(produto);

		return "Produto " + result.get().getNomeProduto() + " excluído com sucesso.";
	}
	
}
