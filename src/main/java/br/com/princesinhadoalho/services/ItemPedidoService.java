/*package br.com.princesinhadoalho.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.itensPedido.ItemPedidoDTO;
import br.com.princesinhadoalho.dtos.pedidos.PedidoGetDTO;
import br.com.princesinhadoalho.entities.ItemPedido;
import br.com.princesinhadoalho.entities.Pedido;
import br.com.princesinhadoalho.entities.Produto;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.repositories.ItemPedidoRepository;
import br.com.princesinhadoalho.repositories.ProdutoRepository;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class ItemPedidoService {

	private final ItemPedidoRepository itemPedidoRepository;
	private final ProdutoRepository produtoRepository;
	private final ModelMapper mapper;

	public ItemPedido cadastrar(ItemPedidoDTO dto) {

		Optional<Produto> result = produtoRepository.findById(idProduto);
		if (result.isEmpty()) {
			throw new EntityNotFoundException("Produto não encontrado.");
		}
		Produto produto = result.get();
				
		ItemPedido itemPedido = new ItemPedido(pedido, produto, dto.getQuantidade(), produto.getValorVenda());
		
		itemPedidoRepository.save(item);
	
		return item;
	}
	
/*	public List<PedidoGetDTO> buscarPedidos() {

		List<Pedido> list = pedidoRepository.findAll();
		List<PedidoGetDTO> listaGetDto = new ArrayList<PedidoGetDTO>();

		for (Pedido pedido : list) {
			PedidoGetDTO getDto = new PedidoGetDTO();
			mapper.map(pedido, getDto);

			listaGetDto.add(getDto);
		}

		return listaGetDto;
	}
	
	public PedidoGetDTO buscarId(Integer idPedido) {

		Optional<Pedido> result = pedidoRepository.findById(idPedido);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Pedido não encontrado.");
		}

		Pedido pedido = result.get();

		PedidoGetDTO getDto = new PedidoGetDTO();
		mapper.map(pedido, getDto);

		return getDto;
	}
	
	public String excluir(Integer idPedido) {

		Optional<Pedido> result = pedidoRepository.findById(idPedido);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Pedido não encontrado.");
		}

		Pedido pedido = result.get();

		pedidoRepository.delete(pedido);

		return "Pedido Nº " + result.get().getNumeroPedido() + " excluído com sucesso.";
	}
*
}
*/