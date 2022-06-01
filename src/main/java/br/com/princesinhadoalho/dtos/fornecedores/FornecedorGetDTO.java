package br.com.princesinhadoalho.dtos.fornecedores;

import br.com.princesinhadoalho.dtos.enderecos.EnderecoGetDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecedorGetDTO {

	private Integer idFornecedor;
	private String nome;
	private String cpfCnpj;
	private EnderecoGetDTO endereco;

}
