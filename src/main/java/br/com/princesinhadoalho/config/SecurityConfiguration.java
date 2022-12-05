package br.com.princesinhadoalho.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.princesinhadoalho.filters.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	private Environment env;

	private static final String[] SWAGGER = { "/v2/api-docs", "/swagger-resources", "/swagger-resources/**",
			"/configuration/ui", "/configuration/security", "/swagger-ui.html", "/webjars/**", "/v3/api-docs/**",
			"/swagger-ui/**" };

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}

		// mapear a classe JwtAuthorizationFilter (segurança da API)
		http.cors().and().csrf().disable()
				.addFilterAfter(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
				
				
				/*PERMITIR PROVISORIAMENTE PARA TESTES*/
				
				  .antMatchers("/api/clientes/**").permitAll()
				  .antMatchers("/api/enderecos/**").permitAll()
				  .antMatchers("/api/fornecedores/**").permitAll()
				  .antMatchers("/api/produtos/**").permitAll()
				  .antMatchers("/api/pedidos/**").permitAll()
				  .antMatchers("/api/vendedores/**").permitAll()
				 

	
				// permitir o cadastro de usuário
				.antMatchers("/api/usuarios/**").permitAll()
				// permitir autenticação do usuário
				.antMatchers("/api/auth").permitAll()
				// permitir recuperar senha
				.antMatchers("/api/recuperarsenha").permitAll()
				// permitir a documentação do swagger
				.antMatchers(SWAGGER).permitAll()
				// permitir o acesso ao console do banco de dados h2
				.antMatchers("/h2-console/**").permitAll()
				// permitir o envio de parâmetros adicionais no protocolo HTTP como por ex:
				// Header, Patch, et..
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
		configuration.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
