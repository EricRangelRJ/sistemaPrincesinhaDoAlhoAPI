package br.com.princesinhadoalho.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.princesinhadoalho.entities.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer>{

}
