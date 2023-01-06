package br.com.princesinhadoalho.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.princesinhadoalho.entities.ClienteEntity;

@Repository
public interface ClienteRepository2 extends JpaRepository<ClienteEntity, Integer>{
	
	
}