package com.example.demo.web;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Bean
	public LocaleResolver localeResolver() {
		var session_locale_resolver = new SessionLocaleResolver();
		session_locale_resolver.setDefaultLocale(new Locale("es"));
		return session_locale_resolver;
	}
	
	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		var locale_change_interceptor = new LocaleChangeInterceptor();
		locale_change_interceptor.setParamName("lang");
		return locale_change_interceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registro) {
		registro.addInterceptor(localeChangeInterceptor());
		
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registro) {
		registro.addViewController("/").setViewName("index");
		registro.addViewController("/login");
		registro.addViewController("/errors/403").setViewName("/errors/403");
	}

}
