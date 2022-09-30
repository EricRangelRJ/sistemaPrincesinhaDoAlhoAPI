package br.com.princesinhadoalho.dtos.pedidos;

import java.util.HashSet;
import java.util.Set;

import br.com.princesinhadoalho.dtos.clientes.ClienteGetDTO;
import br.com.princesinhadoalho.dtos.itensPedido.ItemPedidoGetDTO;
import br.com.princesinhadoalho.dtos.vendedores.VendedorGetDTO;
import br.com.princesinhadoalho.entities.ItemPedido;
import br.com.princesinhadoalho.entities.Pedido;
import br.com.princesinhadoalho.helpers.DateHelper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PedidoGetDTO {
	
	private Integer idPedido;
	private String numeroPedido;
	private String dataPedido;
	private String dataEntrega;
	private String situacao;
	private Double desconto;
	private Double total;
	private ClienteGetDTO cliente;
	private VendedorGetDTO vendedor;
	private Set<ItemPedidoGetDTO> itens;
		
	// Convertendo um Pedido em Dto via construtor
	public PedidoGetDTO(Pedido pedido) {
		this.idPedido = pedido.getIdPedido();
		this.numeroPedido = pedido.getNumeroPedido();
		this.dataPedido = DateHelper.toString(pedido.getDataPedido());
		this.dataEntrega = DateHelper.toString(pedido.getDataEntrega());
		this.situacao = pedido.getSituacao().toString();
		this.desconto = pedido.getDesconto();
		this.total = pedido.getTotal();
		this.cliente = new ClienteGetDTO(pedido.getCliente());
		this.vendedor = new VendedorGetDTO(pedido.getVendedor());
		
		Set<ItemPedidoGetDTO> listaDto = new HashSet<>();
		
		for (ItemPedido itemPedido : pedido.getItens()) {
			ItemPedidoGetDTO itemDto = new ItemPedidoGetDTO(itemPedido);
			
			listaDto.add(itemDto);
		}
		
		this.itens = listaDto;
	}
	
}
