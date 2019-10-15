package com.pa1.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import io.jsonwebtoken.lang.Arrays;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebConfiguration  extends WebSecurityConfigurerAdapter{
  
//Definir as configurações básicas das URL's que necessitam ou não de autenticação/autorização
	
	
	//quais caminhos são liberados
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**"
	};

	//
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/espacos/**",
			"/reservas/**"
	};
	
	//
	@Override
	protected void configure(HttpSecurity http) throws Exception {

	
		//Para o  bean ser ativado
		http.cors().and().csrf().disable();
		//Todos os caminhos que tiver aqui pode ser acessado, caso contrário, exige a  autenticação
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
			.antMatchers(PUBLIC_MATCHERS).permitAll()
			.anyRequest().authenticated();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
}
