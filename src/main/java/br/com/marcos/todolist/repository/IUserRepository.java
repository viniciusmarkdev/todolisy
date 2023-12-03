package br.com.marcos.todolist.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.marcos.todolist.model.UserModel;

@Repository
public interface IUserRepository  extends JpaRepository< UserModel , UUID>{
	
	
	UserModel findByUsername(String username);

}
