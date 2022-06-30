package br.com.princesinhadoalho.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.princesinhadoalho.entities.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	public Optional<Cliente> findByCpf(String cpf);
	
	// buscando uma lista de clientes associados ao endereço fornecido
	@Query("from Cliente c join c.endereco e where e.idEndereco = :param") //JPQL
	Optional<List<Cliente>> findByIdEnderecoJoinEndereco(@Param("param") Integer idEndereco);
	
}