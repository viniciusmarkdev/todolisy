package br.com.marcos.todolist.service;



import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcos.todolist.model.UserModel;
import br.com.marcos.todolist.repository.IUserRepository;

@Service
public class UserService {
	
	
	

	private final IUserRepository repository;
	
	@Autowired
	public UserService(IUserRepository repository) {
		this.repository =  repository;
	}
	
	
	
	

}
