package br.com.princesinhadoalho.dtos.clientes;

import br.com.princesinhadoalho.dtos.enderecos.EnderecoDTO;
import br.com.princesinhadoalho.entities.Cliente;
import br.com.princesinhadoalho.helpers.DateHelper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteGetDTO2 {

	private Integer idCliente;
	private String nome;
	private String cpf;
	private String dataNascimento;
	private String telefone1;
	private String telefone2;
	private String email;
	private String observacao;
	private EnderecoDTO endereco;
	
	
	// Convertendo um cliente em Dto via construtor
	public ClienteGetDTO2(Cliente cliente) {
		
		this.idCliente = cliente.getIdCliente();
		this.nome = cliente.getNome();
		this.cpf = cliente.getCpf();
		
		if(cliente.getDataNascimento() != null) {
			this.dataNascimento = DateHelper.toString(cliente.getDataNascimento());

		}
		this.telefone1 = cliente.getTelefone1();
		this.telefone2 = cliente.getTelefone2();
		this.email = cliente.getEmail();
		this.observacao = cliente.getObservacao();
		
		
	}

}
