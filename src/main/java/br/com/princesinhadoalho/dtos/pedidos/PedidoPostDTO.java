package br.com.princesinhadoalho.dtos.pedidos;

import java.util.List;

import br.com.princesinhadoalho.dtos.itensPedido.ItemPedidoPostDTO;
import lombok.Getter;

@Getter
public class PedidoPostDTO {

	private String situacao;
	private Double desconto;
	private Integer idCliente;
	private Integer idVendedor;
	private List<ItemPedidoPostDTO> itens;
}
