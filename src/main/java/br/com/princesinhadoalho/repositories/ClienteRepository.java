package br.com.princesinhadoalho.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.princesinhadoalho.entities.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer>{
	
	@Query("from Cliente c where c.cpf = :param")
	public Cliente findByCpf(@Param("param") String cpf);
	

}
