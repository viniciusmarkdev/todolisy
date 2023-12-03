package br.com.marcos.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.marcos.todolist.repository.IUserRepository;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class filterTaskAuth  extends  OncePerRequestFilter{

	
         @Autowired
         private IUserRepository userRepository;
         
         
         
	
	/**
	 * 
	 * OncePerRequestFilter - classe serve para não precisarmos transformar ServletRequest request
	 * ou ServletResponse response em HttpServletRequest
	 * 
	 */
         
 
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		var servletPath =  request.getServletPath();
		if(servletPath.startsWith("/tasks/") ) {
			

			var authorization = request.getHeader("Authorization");
			var  authEncoded = authorization.substring("Basic".length()).trim();
			byte[] authDecode = Base64.getDecoder().decode(authEncoded);
			var authString = new String(authDecode);
			System.out.println("Authorization");
			System.out.println(authorization);
			System.out.println(authDecode);
			System.out.println(authString);
			String [] credentials = authString.split(":");
			String username = credentials[0];
			String password = credentials[1];
			System.out.println(username);
			System.out.println(password);
			
			//Validar usuário  no banco de dados
			
			var user = this.userRepository.findByUsername(username);
			if(user == null) {
			
			     response.sendError(401, "Usuário sem autorização");
			}
			else {
				
				 
				//Validar senha
				var passwordVerify = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
				if(passwordVerify.verified) {
					    //Segue viagem 
					    request.setAttribute("idUser", user.getId());
					    System.out.println("Chegou no filtro");
						filterChain.doFilter(request, response);
					
				}
				else {
					
					 response.sendError(401, "Usuário sem autorização");
					
				}
			
				
				
				
			}
			
			
		
			
		}
		
		else {
			filterChain.doFilter(request, response);
			
		}
	
		//Pegar a autenticação (Usuario e senha)
		
		
		
		
		
		
		

		
	}
	
	/**
	 * @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	   
		//Validar se o usuário tem a permissão 
		
		//Buscar no banco de dados 
		
		//Classe que serve como pré-processador
		//Podemos barra requisições ou podemos deixar o usuário fazer a requisição
		//Executar alguma ação
        System.out.println("Chegou no filtro");
		chain.doFilter(request, response);
		
			
	}
	
	 * 
	 * 
	 * 
	 */
	
	
	

}
