package br.com.princesinhadoalho.controllers;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.princesinhadoalho.dtos.usuarios.RecuperarSenhaPostDTO;
import br.com.princesinhadoalho.entities.Usuario;
import br.com.princesinhadoalho.helpers.PasswordHelper;
import br.com.princesinhadoalho.repositories.UsuarioRepository;
import br.com.princesinhadoalho.security.Cryptography;
import br.com.princesinhadoalho.senders.MailSender;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@Transactional
@RequestMapping(value = "/api/recuperarsenha")
public class RecuperarSenhaController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private MailSender mailSender;

	@ApiOperation(value = "Recuperar senha")
	@CrossOrigin
	@PostMapping
	public ResponseEntity<String> recuperar(@RequestBody RecuperarSenhaPostDTO dto) {

		try {

			Usuario usuario = usuarioRepository.findByEmail(dto.getEmail());

			if (usuario != null) {

				String novaSenha = PasswordHelper.generateRandomPassword();

				usuario.setSenha(Cryptography.encrypt(novaSenha));
				usuarioRepository.save(usuario);

				enviarNovaSenha(usuario, novaSenha);

				return ResponseEntity.status(HttpStatus.OK)
						.body("Uma nova senha foi gerada\r\n" + "com sucesso.\r\n" + "Verifique seu email.");

			}
				
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body("O email informado não foi encontrado.");			

			

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro: " + e.getMessage());
		}
	}

	// método para realizar o envio da mensagem..
	private void enviarNovaSenha(Usuario usuario, String novaSenha) throws Exception {

		String assunto = "Nova senha gerada com sucesso";
		String mensagem = "Olá, " + usuario.getNome() + "\n\n"
				+ "Sua nova senha foi gerada com sucesso, para acessar o sistema utilize a senha: " + novaSenha 
				+ "\n\n"
				+ "Att, \n" 
				+ "Princesinha do Alho";

		// enviando o email..
		
		mailSender.sendMessage(usuario.getEmail(), assunto, mensagem);
	}

}
