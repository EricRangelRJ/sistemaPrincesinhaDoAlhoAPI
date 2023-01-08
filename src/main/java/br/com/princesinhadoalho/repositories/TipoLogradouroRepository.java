package br.com.princesinhadoalho.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.princesinhadoalho.entities.TipoLogradouroEntity;

@Repository
public interface TipoLogradouroRepository extends JpaRepository<TipoLogradouroEntity, Integer> {

}
