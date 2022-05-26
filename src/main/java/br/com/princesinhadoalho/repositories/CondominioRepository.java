package br.com.princesinhadoalho.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.princesinhadoalho.entities.Condominio;

@Repository
public interface CondominioRepository extends JpaRepository<Condominio, Integer>{

  public Optional<Condominio> findByNomeCondominio(String nomeCondominio);
  
}
