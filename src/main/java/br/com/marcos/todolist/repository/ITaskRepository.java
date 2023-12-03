package br.com.marcos.todolist.repository;

import org.springframework.stereotype.Repository;

import br.com.marcos.todolist.model.TaskModel;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public  interface ITaskRepository  extends JpaRepository<TaskModel, UUID>{
	
	
	 
	    List<TaskModel> findByIdUser(UUID userId);
	    
	    TaskModel findByIdAndIdUser(UUID id , UUID idUser);

}
