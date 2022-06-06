package br.com.princesinhadoalho.dtos.pedidos;

import lombok.Getter;

@Getter
public class PedidoPostDTO {

	private String numeroPedido;
	private String dataPedido;
	private String dataEntrega;
	private Double total;
	private Integer idCliente;

}
