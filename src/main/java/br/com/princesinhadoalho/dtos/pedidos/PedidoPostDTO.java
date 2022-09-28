package br.com.princesinhadoalho.dtos.pedidos;

import java.util.List;

import br.com.princesinhadoalho.dtos.itensPedido.ItemPedidoDTO;
import lombok.Getter;

@Getter
public class PedidoPostDTO {

	private String situacao;
	private Double desconto;
	private Integer idCliente;
	private List<ItemPedidoDTO> itens;
}
