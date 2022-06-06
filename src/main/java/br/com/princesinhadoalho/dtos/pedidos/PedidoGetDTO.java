package br.com.princesinhadoalho.dtos.pedidos;

import br.com.princesinhadoalho.dtos.clientes.ClienteGetDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoGetDTO {
	
	private Integer idPedido;
	private String numeroPedido;
	private String dataPedido;
	private String dataEntrega;
	private Double total;
	private ClienteGetDTO cliente;

}
