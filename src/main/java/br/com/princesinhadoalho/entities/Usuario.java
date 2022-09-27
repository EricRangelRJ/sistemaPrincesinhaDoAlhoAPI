package br.com.princesinhadoalho.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.princesinhadoalho.security.Cryptography;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuarios")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Getter
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	
	@Setter
	@Getter
	@Column(length = 50, nullable = false)
	private String nome;
	
	@Setter
	@Getter
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	
	@Column(length = 100, nullable = false)
	private String senha;
	
	public Usuario(String nome, String email, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		// criptografando a  senha
		this.senha = Cryptography.encrypt(senha);
	}
	
	public void setSenha(String senha) {
		this.senha = Cryptography.encrypt(senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(idUsuario, other.idUsuario);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUsuario);
	}

}
