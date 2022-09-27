package br.com.princesinhadoalho.security;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import br.com.princesinhadoalho.filters.JWTAuthorizationFilter;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenSecurity {

	public static String generateToken(String email) {

		log.info("ENTROU EM GERAR TOKEN PARA O USUÁRIO..");
		
		// chave secreta para geração do TOKEN (Evitar falsificações)
		String secretKey = JWTAuthorizationFilter.SECRET;

		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");

		// PRINCESINHA_JWT -> nome da aplicação que gerou o token!
		String token = Jwts.builder().setId("PRINCESINHA_JWT").setSubject(email)
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 36000000)) // 10horas
				.signWith(SignatureAlgorithm.HS512, secretKey.getBytes()).compact();

		return token;
	}

}
