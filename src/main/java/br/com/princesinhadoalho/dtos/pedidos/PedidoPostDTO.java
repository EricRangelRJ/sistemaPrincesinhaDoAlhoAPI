package br.com.princesinhadoalho.dtos.pedidos;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.princesinhadoalho.dtos.itensPedido.ItemPedidoPostDTO;
import lombok.Getter;

@Getter
public class PedidoPostDTO {

	private String situacao;
	private Double desconto;
	
	@NotNull
	private Integer idCliente;
	
	@NotNull
	private Integer idVendedor;
	
	private List<ItemPedidoPostDTO> itens;
}
