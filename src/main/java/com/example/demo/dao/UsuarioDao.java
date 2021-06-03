package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario,Long>{
	Usuario findByUsername(String username);
}
