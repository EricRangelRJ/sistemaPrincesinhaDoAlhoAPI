package br.com.princesinhadoalho.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.princesinhadoalho.entities.TipoEnderecoEntity;

@Repository
public interface TipoEnderecoRepository extends JpaRepository<TipoEnderecoEntity, Integer> {

}
