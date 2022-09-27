package br.com.princesinhadoalho.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.produtos.ProdutoGetDTO;
import br.com.princesinhadoalho.dtos.produtos.ProdutoPostDTO;
import br.com.princesinhadoalho.dtos.produtos.ProdutoPutDTO;
import br.com.princesinhadoalho.entities.Fornecedor;
import br.com.princesinhadoalho.entities.Produto;
import br.com.princesinhadoalho.enums.Ativo;
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

	private ModelMapper mapper;
	private final ProdutoRepository produtoRepository;
	private final FornecedorRepository fornecedorRepository;
	private final EnderecoService enderecoService;

	// CADASTRAR
	public ProdutoGetDTO cadastrar(ProdutoPostDTO dto) {

		Optional<Produto> result = produtoRepository.findByNomeProdutoAndDescricaoAndPesoAndValorVenda(
				dto.getNomeProduto(), dto.getDescricao(), dto.getPeso(), dto.getValorVenda());
		
		
		if (result.isPresent()) {
			throw new BadRequestException("Produto já cadastrado. cód: " + result.get().getCodigo());
		}

		Optional<Fornecedor> fornecedor = fornecedorRepository.findById(dto.getIdFornecedor());
		Produto produto = new Produto();

		produto.setNomeProduto(dto.getNomeProduto());
		produto.setCodigo(RandomHelper.gerarCodigoProdutoAleatorio());
		produto.setDescricao(dto.getDescricao());
		produto.setDataCadastro(Calendar.getInstance().getTime());
		produto.setAtivo(Ativo.SIM);
		produto.setPeso(dto.getPeso());
		produto.setValorCusto(dto.getValorCusto());
		produto.setValorVenda(dto.getValorVenda());
		produto.setFornecedor(fornecedor.get());
		produto.setMargemLucro(dto.getValorVenda() - dto.getValorCusto());

		produtoRepository.save(produto);

		return new ProdutoGetDTO(produto);
	}

	// BUSCAR TODOS OS PRODUTOS
	public List<ProdutoGetDTO> buscarProdutos() {

		List<Produto> list = produtoRepository.findAll();
		List<ProdutoGetDTO> listaGetDto = new ArrayList<ProdutoGetDTO>();

		for (Produto produto : list) {
			ProdutoGetDTO getDto = mapper.map(produto, ProdutoGetDTO.class);

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

	//	ProdutoGetDTO getDto = mapper.map(produto, ProdutoGetDTO.class);

		return new ProdutoGetDTO(produto);
	}

	// ATUALIZAR UM PRODUTO
	public ProdutoGetDTO atualizar(ProdutoPutDTO dto) {

		Optional<Produto> result = produtoRepository.findById(dto.getIdProduto());

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Produto não encontrado.");
		}
		
		Optional<Fornecedor> fornecedor = fornecedorRepository.findById(dto.getIdFornecedor());
		Produto produto = result.get();
		
		produto.setNomeProduto(dto.getNomeProduto());
		produto.setDescricao(dto.getDescricao());
		produto.setAtivo(Ativo.valueOf(dto.getAtivo()));
		produto.setPeso(dto.getPeso());
		produto.setValorCusto(dto.getValorCusto());
		produto.setValorVenda(dto.getValorVenda());
		produto.setFornecedor(fornecedor.get());

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
		Fornecedor fornecedor = produto.getFornecedor();
		Set<Produto> produtos =	fornecedor.getProdutos();
		
		// excluindo o fornecedor do produto
		if(produtos.size() == 1) {
			produtoRepository.delete(produto);
			
			if (fornecedor.getEndereco() != null) {
				enderecoService.excluir(fornecedor.getEndereco().getIdEndereco());
			}
			fornecedorRepository.delete(fornecedor);

			return "Produto " + result.get().getNomeProduto() + " excluído com sucesso.";
		}
		// excluindo apenas o produto 
		produtoRepository.delete(produto);

		return "Produto " + result.get().getNomeProduto() + " excluído com sucesso.";
	}

/*	// CONVERTER UM Produto EM ProdutoGetDTO
	public ProdutoGetDTO getProduto(Produto produto) {
		ProdutoGetDTO getDto = new ProdutoGetDTO();
		mapper.map(produto, getDto);

		if (produto.getDataCadastro() == null) {
			getDto.setDataCadastro(null);
			return getDto;
		}

		// converte a data em uma string
		getDto.setDataCadastro(DateHelper.toString(produto.getDataCadastro()));

		return getDto;
	}
*/
}
