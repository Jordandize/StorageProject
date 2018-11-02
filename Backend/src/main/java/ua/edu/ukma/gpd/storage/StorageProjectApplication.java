package ua.edu.ukma.gpd.storage;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;

import ua.edu.ukma.gpd.storage.dao.RoleDao;
import ua.edu.ukma.gpd.storage.dao.UserDao;
import ua.edu.ukma.gpd.storage.dao.relation.UsersRolesDao;
import ua.edu.ukma.gpd.storage.entity.Role;
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
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UsersRolesDao usersRolesDao;
	
	@PostConstruct
	private void dbinit() {
		dbdrop();
		dbcreate();
		
		User user = new User();
		user.setEmail("buyMyMerch@gmail.com");
		user.setPassword("neverloose");
		user.setName("John");
		user.setSurname("Cena");
		user.setPhone("+480996403177");
		userDao.create(user);
		
		Role role1 = new Role((byte) 1, Role.ROLE_USER);
		Role role2 = new Role((byte) 2, Role.ROLE_KEEPER);
		Role role3 = new Role((byte) 3, Role.ROLE_ADMIN);
		roleDao.create(role1);
		roleDao.create(role2);
		roleDao.create(role3);
		
		usersRolesDao.create(user, role1);
		usersRolesDao.create(user, role2);
	}
	
	private void dbcreate() {
		userDao.createUsersTable();
		roleDao.createRolesTable();
		usersRolesDao.createUsersRolesTable();
	}
	
	@PreDestroy
	private void dbdrop() {
		userDao.dropUsersTable();
		roleDao.dropRolesTable();
		usersRolesDao.dropUsersRolesTable();
	}
	
}
