package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.Persona;

public interface IPersonaDao extends CrudRepository<Persona, Long>{

}
