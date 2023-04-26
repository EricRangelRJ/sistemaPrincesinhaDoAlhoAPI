//package br.com.princesinhadoalho.senders;
//
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Component;
//
//import lombok.AllArgsConstructor;
//
//@Component
//@AllArgsConstructor
//public class MailSender {
//
//	private JavaMailSender javaMailSender;
//
//	// método para realizar o envio do email..
//	public void sendMessage(String destinatario, String assunto, String mensagem) {
//
//		SimpleMailMessage mailMessage = new SimpleMailMessage();
//
//		mailMessage.setTo(destinatario);
//		mailMessage.setSubject(assunto);
//		mailMessage.setText(mensagem);
//
//		javaMailSender.send(mailMessage);
//	}
//}
