package br.com.princesinhadoalho.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.princesinhadoalho.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	public Optional<Produto> findByCodigo(String codigo);
	
	public Optional<Produto> findByNomeProdutoAndDescricaoAndPesoAndValorVenda(String nomeProduto, String descricao, Double peso, Double valorVenda);
	
/*	// buscando uma lista de produtos associados ao fornecedor do produto
	@Query("from Produto p join p.fornecedor f where f.idFornecedor = :param") //JPQL
	List<Produto> findByIdFornecedorJoinFornecedor(@Param("param") Integer idFornecedor);
*/
}
