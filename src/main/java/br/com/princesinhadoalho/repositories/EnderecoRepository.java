package br.com.princesinhadoalho.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.princesinhadoalho.entities.EnderecoEntity;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Integer>{
	
	public Optional<EnderecoEntity> findByLogradouroAndNumeroAndCepAndComplemento(String logradouro, String numero, String cep, String Complemento);
	
}