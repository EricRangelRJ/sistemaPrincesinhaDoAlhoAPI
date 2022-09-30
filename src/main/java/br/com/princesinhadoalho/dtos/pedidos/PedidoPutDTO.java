package br.com.princesinhadoalho.dtos.pedidos;

import javax.validation.constraints.NotNull;

import lombok.Getter;

@Getter
public class PedidoPutDTO {

	@NotNull
	private Integer idPedido;
//	private String numeroPedido; - NÃO PERMITIDO ALTERAR
//	private String dataPedido;  - NÃO PERMITIDO ALTERAR
	private String dataEntrega;
	private String situacao;
	private Double desconto;
	
	@NotNull
	private Integer idCliente;
	
	@NotNull
	private Integer idVendedor;

}
