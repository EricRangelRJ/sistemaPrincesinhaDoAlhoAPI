package br.com.princesinhadoalho.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.princesinhadoalho.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	@Query("from Cliente c where c.cpf = :param")
	public Cliente findByCpf(@Param("param") String cpf);
	

}