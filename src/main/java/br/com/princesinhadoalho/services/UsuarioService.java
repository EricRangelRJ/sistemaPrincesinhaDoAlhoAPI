package br.com.princesinhadoalho.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.usuarios.UsuarioGetDTO;
import br.com.princesinhadoalho.dtos.usuarios.UsuarioPostDTO;
import br.com.princesinhadoalho.dtos.usuarios.UsuarioPutDTO;
import br.com.princesinhadoalho.entities.Usuario;
import br.com.princesinhadoalho.exceptions.BadRequestException;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.repositories.UsuarioRepository;
import br.com.princesinhadoalho.security.Cryptography;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class UsuarioService {

	private final UsuarioRepository repository;
	private final ModelMapper mapper;

	public UsuarioGetDTO cadastrar(UsuarioPostDTO dto) {

		Optional<Usuario> result = repository.findByEmail(dto.getEmail());

		// verificar se o email já está cadastrado no banco
		if (result.isPresent()) {
			throw new BadRequestException("Erro: Email já cadastrado!");
		}

		// Criptografando a senha do usuário
		String senha = Cryptography.encrypt(dto.getSenha());

		// inserindo os dados do Usuário
		Usuario usuario = new Usuario();
		mapper.map(dto, usuario);
		usuario.setSenha(senha);

		// salvando
		repository.save(usuario);

		// passando o usuário para um dto
		UsuarioGetDTO getDto = new UsuarioGetDTO();
		mapper.map(usuario, getDto);
		return getDto;
	}

	public List<UsuarioGetDTO> buscarUsuarios() {

		List<UsuarioGetDTO> listaGetDto = new ArrayList<UsuarioGetDTO>();
		List<Usuario> listaUsuarios = repository.findAll();

		for (Usuario usuario : listaUsuarios) {

			UsuarioGetDTO getDto = new UsuarioGetDTO();
			mapper.map(usuario, getDto);

			listaGetDto.add(getDto);
		}

		return listaGetDto;
	}

	public UsuarioGetDTO buscarId(Integer idUsuario) {

		Optional<Usuario> result = repository.findById(idUsuario);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Usuário não encontrado.");
		}

		Usuario usuario = result.get();

		UsuarioGetDTO getDto = new UsuarioGetDTO();
		mapper.map(usuario, getDto);

		return getDto;
	}

	public UsuarioGetDTO atualizar(UsuarioPutDTO dto) {

		Optional<Usuario> result = repository.findById(dto.getIdUsuario());

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Usuário não encontrado.");
		}

		// Criptografando a senha do usuário
		String senha = Cryptography.encrypt(dto.getSenha());

		// alterando os dados do Usuário encontrado
		Usuario usuario = result.get();
		mapper.map(dto, usuario);
		usuario.setSenha(senha);

		repository.save(usuario);

		UsuarioGetDTO getDto = new UsuarioGetDTO();
		mapper.map(usuario, getDto);

		return getDto;
	}

	public String excluir(Integer idUsuario) {

		Optional<Usuario> result = repository.findById(idUsuario);

		if (result.isEmpty()) {
			throw new EntityNotFoundException("Usuário não encontrado.");
		}

		Usuario usuario = result.get();

		repository.delete(usuario);

		return "Usuário " + result.get().getNome() + " excluído com sucesso.";
	}
}
