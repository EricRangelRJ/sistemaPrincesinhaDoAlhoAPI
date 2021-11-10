package br.com.princesinhadoalho.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.com.princesinhadoalho.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{
	
	@Query("from Usuario u where u.email = :param")
	public Usuario findByEmail(@Param("param") String email);

	@Query("from Usuario u where u.email = :param1 and u.senha = :param2")
	public Usuario findByEmailAndSenha(@Param("param1") String email, @Param("param2") String senha);
	
}
