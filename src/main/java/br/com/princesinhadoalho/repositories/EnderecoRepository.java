package br.com.princesinhadoalho.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.princesinhadoalho.entities.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
	
	public Optional<Endereco> findByLogradouroAndNumeroAndCepAndComplemento(String logradouro, String numero, String cep, String Complemento);
	
}