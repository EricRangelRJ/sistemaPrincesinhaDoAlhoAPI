package br.com.princesinhadoalho.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.princesinhadoalho.dtos.usuarios.RecuperarSenhaPostDTO;
import br.com.princesinhadoalho.entities.Usuario;
import br.com.princesinhadoalho.exceptions.EntityNotFoundException;
import br.com.princesinhadoalho.exceptions.ServiceException;
import br.com.princesinhadoalho.helpers.PasswordHelper;
import br.com.princesinhadoalho.repositories.UsuarioRepository;
import br.com.princesinhadoalho.security.Cryptography;
import br.com.princesinhadoalho.senders.MailSender;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class RecuperarSenhaService {

	private UsuarioRepository repository;
	private final MailSender mailSender;

	public String recuperarSenha(RecuperarSenhaPostDTO dto) {

		// consultando o usuario no banco de dados atraves do email
		Optional<Usuario> result = repository.findByEmail(dto.getEmail());

		if (result.isEmpty()) {
			throw new EntityNotFoundException("O email informado não foi encontrado.");
		}

		// gerar uma nova senha TEMPORÁRIA de forma randômica para o usuário..
		String novaSenha = PasswordHelper.generateRandomPassword();

		// Criptografa a nova senha e atribui ao usuário
		Usuario usuario = result.get();
		usuario.setSenha(Cryptography.encrypt(novaSenha));

		// salva o usuário no banco já com a senha alterada e criptografada
		repository.save(usuario);

		// chamando o método pra envio da mensagem
		String response = enviarNovaSenha(usuario, novaSenha);
		return response;

	}

	// método para realizar o envio da mensagem..
	private String enviarNovaSenha(Usuario usuario, String novaSenha) {

		try {
			String assunto = "Nova senha gerada com sucesso";
			String mensagem = "Olá, " + usuario.getNome() + "\n\n"
					+ "Sua nova senha foi gerada com sucesso, para acessar o sistema utilize a senha: " + novaSenha
					+ "\n\n" + "Att, \n" + "Equipe Princesinha do Alho.";

			// enviando o email.
			mailSender.sendMessage(usuario.getEmail(), assunto, mensagem);

			String response = "Uma nova senha foi gerada com sucesso. Verifique seu email.";
			return response;

		} catch (ServiceException e) {
			String response = "Erro: " + e.getMessage();
			return response;
		}

	}
}
