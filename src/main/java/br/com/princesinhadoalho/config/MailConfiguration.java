//package br.com.princesinhadoalho.config;
//
//import java.util.Properties;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
//@Configuration
//public class MailConfiguration {
//
//	// armazenando os valores das variáveis de ambiente
//	@Value("${spring.mail.username}")
//	private String myEmail;
//
//	@Value("${spring.mail.password}")
//	private String myPassword;
//
//	// método para configurar as propriedades de envio de email..
//	@Bean
//	public JavaMailSender getJavaMailSender() {
//
//		JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
//
//		// configuração do serviço SMTP (envio de email)
//		mailSenderImpl.setHost("smtp.gmail.com");
//		mailSenderImpl.setPort(587);
//
//		// configuração da conta de email utilizada para enviar emails
//		mailSenderImpl.setUsername(myEmail);
//		mailSenderImpl.setPassword(myPassword);
//
//		Properties props = mailSenderImpl.getJavaMailProperties();
//		props.put("mail.transport.protocol", "smtp");
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//		props.put("mail.debug", "true");
//
//		return mailSenderImpl;
//	}
//
//}