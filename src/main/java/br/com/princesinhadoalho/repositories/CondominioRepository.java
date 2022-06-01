package br.com.princesinhadoalho.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.princesinhadoalho.entities.Condominio;

@Repository
public interface CondominioRepository extends JpaRepository<Condominio, Integer>{
 
}
