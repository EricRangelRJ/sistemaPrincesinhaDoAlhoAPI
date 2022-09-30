package br.com.princesinhadoalho.services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.itensPedido.ItemPedidoPostDTO;
import br.com.princesinhadoalho.entities.ItemPedido;
import br.com.princesinhadoalho.entities.Pedido;
import br.com.princesinhadoalho.entities.Produto;
import br.com.princesinhadoalho.exceptions.BadRequestException;
import br.com.princesinhadoalho.repositories.ItemPedidoRepository;
import br.com.princesinhadoalho.repositories.ProdutoRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ItemPedidoService {

	private final ItemPedidoRepository itemPedidoRepository;
	private final ProdutoRepository produtoRepository;

	public Set<ItemPedido> cadastrar(Pedido pedido, List<ItemPedidoPostDTO> lista) {
		
		Set<ItemPedido> itens = new HashSet<ItemPedido>();
		
		double total = 0.0;
		
		for (ItemPedidoPostDTO itemDto : lista) {
			
			Optional<Produto> prod = produtoRepository.findById(itemDto.getIdProduto());
			Produto produto = prod.get();
			
			if(produto.getAtivo() != "SIM") {
				throw new BadRequestException("O produto: " + produto.getNomeProduto()+ " - cód:" + produto.getCodigo() + ", não está ativo no momento.");
			}

			ItemPedido item = new ItemPedido(pedido, produto, itemDto.getQuantidade(), produto.getValorVenda());
						
			itens.add(item);
			
			// calculando o valor total do pedido, sem o desconto
			total += item.getSubTotal();
		}
		
		if(total < pedido.getDesconto()) {
			throw new BadRequestException("O valor do desconto não pode ser superior ao valor total do pedido.");
		}
		
		// salvando os ítens do pedido
		itemPedidoRepository.saveAll(itens);
		
		return itens;
	}
}
