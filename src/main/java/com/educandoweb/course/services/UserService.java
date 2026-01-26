package com.educandoweb.course.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	// Você não cria o UserRepository com new UserRepository().
	//O Spring cria esse objeto (bean) e “entrega” ele pra sua classe quando ela inicia.
	//Assim sua classe fica mais limpa, desacoplada e testável.
	
	
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
}
