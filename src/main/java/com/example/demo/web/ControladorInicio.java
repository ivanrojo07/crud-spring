package com.example.demo.web;


import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.dao.IPersonaDao;
import com.example.demo.domain.Persona;
import com.example.demo.service.PersonaServiceImpl;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class ControladorInicio {
	
	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(ControladorInicio.class);
	
	@Autowired
	private PersonaServiceImpl personaService;
	
	@GetMapping("/")
	public String index(Model model, @AuthenticationPrincipal User user, Persona persona) {
		logger.info("usuario: "+user.getUsername());
		var personas = personaService.listarPersonas();
		model.addAttribute("personas", personas);
		var saldoTotal = 0D;
		for(var p:personas) {
			saldoTotal += p.getSaldo();
		}
		model.addAttribute("saldoTotal", saldoTotal);
		model.addAttribute("totalClientes", personas.size());
		return "index";
	}
	
	@GetMapping("/agregar")
	public String agregar(Persona persona) {
		return "formulario_persona";
	}
	
	@PostMapping("/guardar")
	public String guardar(@Valid Persona persona, Errors errors) {
		if(errors.hasErrors()) {
			return "formulario_persona";
		}
		
		personaService.guardar(persona);			
		
		return "redirect:/";
	}
	
	@GetMapping("/editar/{idPersona}")
	public String editar(Persona persona, Model model) {
		var persona_find = personaService.encontrarPersona(persona);
		model.addAttribute("persona", persona_find);
		return "formulario_persona";
	}
	
	@GetMapping(value="/eliminar/{idPersona}")
	public String eliminar(Persona persona) {
		personaService.eliminar(persona);
		return "redirect:/";
	}
}
