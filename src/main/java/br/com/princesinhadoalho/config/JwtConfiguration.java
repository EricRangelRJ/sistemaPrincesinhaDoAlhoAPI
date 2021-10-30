package br.com.princesinhadoalho.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.princesinhadoalho.filters.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity 
public class JwtConfiguration extends WebSecurityConfigurerAdapter {

	//método para realizar a configuração do JWT
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		//mapear a classe JwtAuthorizationFilter (segurança da API)
		http.csrf().disable().addFilterAfter(
				new JWTAuthorizationFilter(), //Classe que faz a segurança da API
				UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				.antMatchers("/api/auth").permitAll() //permitir autenticação
				.antMatchers("/api/usuarios").permitAll()
				.antMatchers("/api/recuperarsenha").permitAll() //permitir recuperar senha
				//permitir o envio de parametros adicionais no protocolo HTTP
				//como por exemplo Header, Patch, etc..
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll() 
				.anyRequest()
				.authenticated();		
	}	
	
	//configuração para liberar a documentação do SWAGGER
	private static final String[] SWAGGER = {
	            // -- Swagger UI v2
	            "/v2/api-docs",
	            "/swagger-resources",
	            "/swagger-resources/**",
	            "/configuration/ui",
	            "/configuration/security",
	            "/swagger-ui.html",
	            "/webjars/**",
	            // -- Swagger UI v3 (OpenAPI)
	            "/v3/api-docs/**",
	            "/swagger-ui/**"
	            // other public endpoints of your API may be appended to this array
	 };
		
	@Override
	public void configure(WebSecurity web) throws Exception {
	     web.ignoring().antMatchers(SWAGGER);
	}
}
