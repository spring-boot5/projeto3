package estudos.io.projeto3.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import estudos.io.projeto3.entity.User;
import estudos.io.projeto3.repository.UserRepository;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	UserRepository userRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		System.out.println("Entrou no initializr");
		List<User> users = userRepository.findAll();

		if (users.isEmpty()) {
			createUser("User1 Name1", "user1@gmail.com");
			createUser("User2 Name2", "user2@gmail.com");
			createUser("User3 Name3", "user3@gmail.com");
			createUser("User4 Name4", "user4@gmail.com");
			createUser("User5 Name5", "user5@gmail.com");
		}
		
		User user = userRepository.getOne(2L);
		
		System.out.println(user.getName());
		
		user = userRepository.findByName("User3 Name3");
		
		System.out.println(user.getName());
		
		User user2 = userRepository.findByNameQualquerCoisa("User4");
		
		System.out.println(user2.getName());
		
		User user3 = userRepository.findByEmail("user1@gmail.com");
		
		System.out.println(user3.getEmail());
	}
	
	public void createUser(String name, String email) {
		User user = new User(name, email);
		
		userRepository.save(user);
	}

}
