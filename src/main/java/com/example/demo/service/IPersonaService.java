package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.Persona;

public interface IPersonaService {
	
	public List<Persona> listarPersonas();
	
	public void guardar(Persona persona);
	
	public void eliminar(Persona persona);
	
	public Persona encontrarPersona(Persona persona);
}
