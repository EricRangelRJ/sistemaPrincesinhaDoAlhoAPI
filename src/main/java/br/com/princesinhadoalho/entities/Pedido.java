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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Pedido implements Serializable {

	
	private static final long serialVersionUID = -1318735330355748410L;

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

	@Getter @Setter
	@ManyToOne
	@JoinColumn(name = "idVendedor", nullable = false)
	private Vendedor vendedor;

	@Getter @Setter
	@ManyToOne
	@JoinColumn(name = "idCliente", nullable = false)
	private Cliente cliente;

	@Getter	@Setter
	@ManyToOne
	@JoinColumn(name = "idSituacaoPedido", nullable = false)
	private SituacaoPedido situacaoPedido;

}
