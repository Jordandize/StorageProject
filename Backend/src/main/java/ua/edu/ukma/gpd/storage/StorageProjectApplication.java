package ua.edu.ukma.gpd.storage;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import ua.edu.ukma.gpd.storage.dao.UserDao;
import ua.edu.ukma.gpd.storage.entity.User;

@SpringBootApplication
@Controller
public class StorageProjectApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(StorageProjectApplication.class, args);
	}

	/*
	 * Test BD content
	 */
	
	@Autowired
	private UserDao userDao;
	
	@PostConstruct
	public void dbinit() {
		dbdrop();
		userDao.createUsersTable();
		User user = new User();
		user.setEmail("maestro777@gmail.com");
		user.setPassword("nomargarite");
		user.setName("John");
		user.setSurname("Cena");
		userDao.create(user);
	}
	
	@PreDestroy
	public void dbdrop() {
		userDao.dropUsersTable();
	}
	
}
