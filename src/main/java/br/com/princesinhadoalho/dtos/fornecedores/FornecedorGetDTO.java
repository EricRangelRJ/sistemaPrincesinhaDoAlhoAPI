package br.com.princesinhadoalho.dtos.fornecedores;

import br.com.princesinhadoalho.dtos.enderecos.EnderecoDTO;
import br.com.princesinhadoalho.entities.Fornecedor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FornecedorGetDTO {

	private Integer idFornecedor;
	private String nomeFornecedor;
	private String cpfCnpj;
	private String telefone1;
	private String telefone2;
	private String email;
	private EnderecoDTO endereco;
	
	
	// Convertendo um Fornecedor em Dto via construtor
	public FornecedorGetDTO(Fornecedor fornecedor) {
		this.idFornecedor = fornecedor.getIdFornecedor();
		this.nomeFornecedor = fornecedor.getNomeFornecedor();
		this.cpfCnpj = fornecedor.getCpfCnpj();
		this.telefone1 = fornecedor.getTelefone1();
		this.telefone2 = fornecedor.getTelefone2();
		this.email = fornecedor.getEmail();
		
		if(fornecedor.getEndereco() != null) {
			this.endereco = new EnderecoDTO(fornecedor.getEndereco());
		}	
	}

}
