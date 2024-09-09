package br.com.marcos.todolist.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.marcos.todolist.model.UserModel;
import br.com.marcos.todolist.repository.IUserRepository;
import br.com.marcos.todolist.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
	
	
	
	private final IUserRepository repository;
	private final UserService service;
	
	@Autowired
	public UserController(IUserRepository repository , UserService service) {
		
		
		this.repository =  repository;
		this.service = service;
		
		
	}
	
	

	

	
	@GetMapping("/all")
	public  ResponseEntity <List<UserModel>> getAll(){
		
		return ResponseEntity.ok(repository.findAll()) ;
		
	}
	

	
	@PostMapping
	public ResponseEntity  create (@RequestBody UserModel userModel) {
	
	    var user = this.repository.findByUsername(userModel.getUsername());
	    
	    if(user!= null) {
	    	
	    	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe");
	   
	    }
		
	    else {
	    
	    	var passwordHashred = BCrypt.withDefaults().hashToString(12 , userModel.getPassword().toCharArray());
	    	userModel.setPassword(passwordHashred);
	    	return  ResponseEntity.status(HttpStatus.OK).body(repository.save(userModel));
	    	
	    }
	
	    
	}
	

}
