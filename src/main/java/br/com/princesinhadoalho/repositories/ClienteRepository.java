package br.com.princesinhadoalho.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.princesinhadoalho.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	public Optional<Cliente> findByCpf(String cpf);
		
}