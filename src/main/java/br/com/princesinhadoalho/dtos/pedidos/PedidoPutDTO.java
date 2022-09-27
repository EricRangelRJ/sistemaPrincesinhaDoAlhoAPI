package br.com.princesinhadoalho.dtos.pedidos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoPutDTO {

	private Integer idPedido;
//	private String numeroPedido;NÃO permitido
//	private String dataPedido;
	private String dataEntrega;
	private String situacao;
	private Double desconto;
	private Integer idCliente;
}
