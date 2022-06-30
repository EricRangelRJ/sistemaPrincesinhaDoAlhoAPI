package br.com.princesinhadoalho.dtos.fornecedores;

import br.com.princesinhadoalho.dtos.enderecos.EnderecoDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecedorGetDTO {

	private Integer idFornecedor;
	private String nome;
	private String cpfCnpj;
	private String telefone1;
	private String telefone2;
	private String email;
	private EnderecoDTO endereco;

}
