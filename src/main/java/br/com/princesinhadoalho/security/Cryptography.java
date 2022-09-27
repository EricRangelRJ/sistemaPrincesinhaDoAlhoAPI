package br.com.princesinhadoalho.security;

import java.security.MessageDigest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Cryptography {

	
	// atributo para gerar a criptografia em MD5..
		private static MessageDigest messageDigest = null;

		// método estático anônimo para inicializar o atributo MessageDigest
		static {
			try {
				messageDigest = MessageDigest.getInstance("MD5");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// método para converter um valor criptografado
		// de byte[] para string hexadecimal..
		private static char[] hexCodes(byte[] text) {
			char[] hexOutput = new char[text.length * 2];
			String hexString;

			for (int i = 0; i < text.length; i++) {
				hexString = "00" + Integer.toHexString(text[i]);
				hexString.toUpperCase().getChars(hexString.length() - 2, hexString.length(), hexOutput, i * 2);
			}

			return hexOutput;
		}

		// método para realizar a criptografia..
		public static String encrypt(String value) {
			log.info("ENTROU EM CRIPTOGRAFAR SENHA DO USUÁRIO..");

			try {
				if (value != null) {
					return new String(hexCodes(messageDigest.digest(value.getBytes()))).toLowerCase();	
				}
			} catch (Exception e) {
				log.error("ERRO:", e);
				
			}
			

			return null;
		}
}

