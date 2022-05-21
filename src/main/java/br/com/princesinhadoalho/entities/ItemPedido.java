package br.com.princesinhadoalho.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "itemPedido")
public class ItemPedido implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idItemPedido;
	
	private Integer quantidade;
	
	private Double subTotal;
	
	private Float desconto;
	
	@ManyToOne
	@JoinColumn(name = "IdProduto", nullable = false)
	private Produto produto;
	
}
