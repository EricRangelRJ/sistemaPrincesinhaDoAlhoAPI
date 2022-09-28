package br.com.princesinhadoalho.dtos.fornecedores;

import java.util.List;

import br.com.princesinhadoalho.dtos.enderecos.EnderecoDTO;
import br.com.princesinhadoalho.dtos.produtos.ProdutoGetDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FornecedorDTO {

	private Integer idFornecedor;
	private String nomeFornecedor;
	private String cpfCnpj;
	private String telefone1;
	private String telefone2;
	private String email;
	private EnderecoDTO endereco;
	private List<ProdutoGetDTO> produtos;

}
