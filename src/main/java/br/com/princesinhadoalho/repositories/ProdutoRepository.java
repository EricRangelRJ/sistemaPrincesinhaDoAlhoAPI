package br.com.princesinhadoalho.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.princesinhadoalho.entities.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	Optional<Produto> findByCodigo(String codigo);
}
