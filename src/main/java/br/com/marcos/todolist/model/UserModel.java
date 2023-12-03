package br.com.marcos.todolist.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity(name="tb_users")
@Data
public class UserModel {

	@Id
	/*
	 *Universally Unique Identifier 
	 *
	 *
        UUID (Universally Unique Identifier) é um identificador único globalmente reconhecido.
        Ele é frequentemente usado em sistemas distribuídos para identificar de forma exclusiva 
        recursos sem depender de um único ponto de geração centralizada. 
        No contexto de bancos de dados,   UUIDs podem ser utilizados como 
        chaves primárias para garantir unicidade, mesmo em ambientes distribuídos, onde cada parte do sistema
        estão localizadas em diferentes computadores ou servidores.
	 * 
	 */
	@GeneratedValue(generator = "UUID")
	private UUID id ;
	
	/**
	 * 
	 * Coluna com a restrição de valor unico 
	 * , ou seja , não permite 
	 * que haja outra varíavel com 
	 * o mesmo valor
	 * 
	 */
	@Column(unique=true)
	private String username;
	
	private String name;
	
	
	private String password;
	/*
	 * A anotação CreationTimestamp serve para mapearmos  
	 * a hora na qual foi criada a entidade no banco de dados
	 */
	@CreationTimestamp
	private LocalDateTime createdAt;

	
	

	

	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public LocalDateTime getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}


 	
	
	
}
