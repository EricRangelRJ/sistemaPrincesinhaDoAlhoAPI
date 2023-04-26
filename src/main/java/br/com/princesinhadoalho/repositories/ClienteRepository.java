package br.com.princesinhadoalho.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.princesinhadoalho.entities.ClienteEntity;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer>{
	
	public Optional<ClienteEntity> findByCpf(String cpf);
	
//	// buscando uma lista de ClienteEntitys associados ao endereço fornecido
//	@Query("from ClienteEntity c join c.endereco e where e.idEndereco = :param") //JPQL
//	Optional<List<ClienteEntity>> findByIdEnderecoJoinEndereco(@Param("param") Integer idEndereco);
	
}