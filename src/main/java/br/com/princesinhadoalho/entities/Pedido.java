package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pedidos")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idPedido")
	private Integer idPedido;

	@Column(length = 15)
	@Temporal(TemporalType.DATE)
	private Date dataPedido;

	@Column(length = 15)
	@Temporal(TemporalType.DATE)
	private Date dataEntrega;

	@Column(length = 15)
	private Double total;

	@ManyToOne
	@JoinColumn(name = "idCliente", nullable = false)
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "idVendedor", nullable = false)
	private Vendedor vendedor;

	@ManyToOne
	@JoinColumn(name = "idSituacaoPedido", nullable = false)
	private SituacaoPedido situacaoPedido;
	
	
	
}