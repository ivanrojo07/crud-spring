package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.IPersonaDao;
import com.example.demo.domain.Persona;

@Service
public class PersonaServiceImpl implements IPersonaService{
	
	@Autowired
	private IPersonaDao personaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Persona> listarPersonas() {
		return (List<Persona>) personaDao.findAll();
	}

	@Override
	@Transactional
	public void guardar(Persona persona) {
		personaDao.save(persona);
	}

	@Override
	@Transactional
	public void eliminar(Persona persona) {
		personaDao.delete(persona);
	}

	@Override
	@Transactional(readOnly = true)
	public Persona encontrarPersona(Persona persona) {
		Optional<Persona> res =  personaDao.findById(persona.getIdPersona());
		Persona person = res.orElse(null);
		return person;
	}

}
