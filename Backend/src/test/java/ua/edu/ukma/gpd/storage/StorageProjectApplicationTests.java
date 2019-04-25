package ua.edu.ukma.gpd.storage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.junit.ClassRule;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
//import org.testcontainers.containers.PostgreSQLContainer;

import ua.edu.ukma.gpd.storage.dto.OrderDto;
import ua.edu.ukma.gpd.storage.dto.RolesDto;
import ua.edu.ukma.gpd.storage.dto.SignupFormDto;
import ua.edu.ukma.gpd.storage.entity.Order;
import ua.edu.ukma.gpd.storage.entity.Product;
import ua.edu.ukma.gpd.storage.entity.User;
import ua.edu.ukma.gpd.storage.enumeration.OrderStatus;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = StorageProjectApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StorageProjectApplicationTests {
	

	     @Autowired
	     private TestRestTemplate restTemplate;

	     @LocalServerPort
	     private int port;

	     private String getRootUrl() {
	         return "http://localhost:" + port;
	     }
	    
	     @Test
	     public void contextLoads() {

	     }
	     int id=2;
	     //Test for ProductsAPI
	     @Test
	     public void testProduct2GetAll() {
	     HttpHeaders headers = new HttpHeaders();
	        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
	        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/products",
	        HttpMethod.GET, entity,String.class);  
	        assertNotNull(response.getBody());
	    }


	    @Test
	    public void testProduct1CRUD() {
	        Product entity = new Product();
	        entity.setActive(true);
	        entity.setAmount(23);
	        entity.setCategoryId(2L);
	        entity.setDescription("Description for Product");
	        entity.setIcon("https://cdn.vox-cdn.com/uploads/chorus_image/image/59319581/A182669_medium.0.jpg");
	        entity.setImage("https://cdn.vox-cdn.com/uploads/chorus_image/image/59319581/A182669_medium.0.jpg");
	        entity.setName("Car");
	        ResponseEntity<ResponseForId> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/products", entity,ResponseForId.class);
	        System.out.println(postResponse.getBody());
	        Long idEnt=  postResponse.getBody().getData();
	        assertNotNull(postResponse.getBody().getHttpStatus());
	        assertNotNull(postResponse.getBody().getData());
	        
	        entity = restTemplate.getForObject(getRootUrl() + "/api/products/" + idEnt, Product.class);
	        assertNotNull(entity);
	        entity.setDescription("Description for Product2");
	        entity.setName("Car2");
	        ResponseEntity<Response> updatedEntity = restTemplate.postForEntity(getRootUrl() + "/api/products/" + idEnt, entity, Response.class);
	        assertNotNull(updatedEntity);
	        assertTrue(updatedEntity.getBody().isData());
	        
	        entity = restTemplate.getForObject(getRootUrl() + "/api/products/" + idEnt, Product.class);
	         assertNotNull(entity);
	         restTemplate.delete(getRootUrl() + "/api/products/" + idEnt);
	         try {
	              entity = restTemplate.getForObject(getRootUrl() + "/api/products/" + idEnt, Product.class);
	         } catch (final HttpClientErrorException e) {
	              assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
	         }
	    }
	    
	    //Test for OrdersAPI
	    @Test
	    public void testOrders() {
	    	HttpHeaders headers = new HttpHeaders();
	        HttpEntity<String> entityAll = new HttpEntity<String>(null, headers);
	        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/orders",
	        HttpMethod.GET, entityAll,String.class);  
	        assertNotNull(response.getBody());
	        
	        
	        OrderDto entity = new OrderDto();
	        Date date= new Date();
	        
	        long time = date.getTime();
	        entity.setAnnotation("something");
	        entity.setCreatedBy((long)2);
	        Product ent = restTemplate.getForObject(getRootUrl() + "/api/products/" + 2, Product.class);
	        HashMap<Long,Integer> hash=new HashMap<Long,Integer>();
	        hash.put(ent.getId(), 2);
	        entity.setProducts(hash);
	        entity.setOrderType(1);
	        ResponseEntity<ResponseForId> postResponse = restTemplate.postForEntity(getRootUrl() + "/api/orders", entity,ResponseForId.class);
	        System.out.println(postResponse.getBody());
	        Long idEnt=  postResponse.getBody().getData();
	        assertNotNull(postResponse.getBody().getHttpStatus());
	        assertEquals(postResponse.getBody().getHttpStatus(), 200);
	        assertNotNull(postResponse.getBody().getData());
	        
	        Order entityOrder = restTemplate.getForObject(getRootUrl() + "/api/orders/oneOrder/" + idEnt, Order.class);
	        System.out.println(entityOrder.getId());
	        assertNotNull(entityOrder);
	        
	        headers = new HttpHeaders();
	        entityAll = new HttpEntity<String>(null, headers);
	        response = restTemplate.exchange(getRootUrl() + "/api/orders_products/products/"+idEnt,
	        HttpMethod.GET, entityAll,String.class);  
	        assertNotNull(response.getBody());
	        
	        headers = new HttpHeaders();
	        entityAll = new HttpEntity<String>(null, headers);
	        response = restTemplate.exchange(getRootUrl() + "/api/orders/"+idEnt+"/ready",
	        HttpMethod.POST, entityAll,String.class);  
	        System.out.println("Data"+response.getBody());
	        assertNotNull(response.getBody());
	        
	        headers = new HttpHeaders();
	        entityAll = new HttpEntity<String>(null, headers);
	        response = restTemplate.exchange(getRootUrl() + "/api/orders/"+idEnt+"/decline",
	        HttpMethod.POST, entityAll,String.class);  
	        System.out.println("Data"+response.getBody());
	        assertNotNull(response.getBody());
	        
	        
	        headers = new HttpHeaders();
	        entityAll = new HttpEntity<String>(null, headers);
	        response = restTemplate.exchange(getRootUrl() + "/api/orders/"+idEnt+"/cancel",
	        HttpMethod.POST, entityAll,String.class);  
	        System.out.println("Data"+response.getBody());
	        assertNotNull(response.getBody());
	        
	        
	        headers = new HttpHeaders();
	        entityAll = new HttpEntity<String>(null, headers);
	        response = restTemplate.exchange(getRootUrl() + "/api/orders/"+idEnt+"/closed",
	        HttpMethod.POST, entityAll,String.class);  
	        System.out.println("Data"+response.getBody());
	        assertNotNull(response.getBody());
	      
	        entityOrder = restTemplate.getForObject(getRootUrl() + "/api/orders/oneOrder/" + idEnt, Order.class);
	        System.out.println(entityOrder.getId());
	        assertNotNull(entityOrder);
	    }
	    
	  //Test for UsersAPI
	    @Test
	    public void testUsers() {
	    	HttpHeaders headers = new HttpHeaders();
	        HttpEntity<String> entityAll = new HttpEntity<String>(null, headers);
	        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/api/users",
	        HttpMethod.GET, entityAll,String.class);  
	        assertNotNull(response.getBody());
	        
	        SignupFormDto entity = new  SignupFormDto();
	        UUID uuid = UUID.randomUUID();
	        String randomUUIDString = uuid.toString()+"@gmail.com";
	        entity.setEmail(randomUUIDString);
	        entity.setName("UserTester");
	        entity.setPassword("123456789");
	        entity.setPasswordRepeat("123456789");
	        entity.setPhone("111");
	        entity.setSurname("Tester");
	        ResponseEntity<ResponseForId> postResponse = restTemplate.postForEntity(getRootUrl() + "/users", entity,ResponseForId.class);
	        System.out.println("User: "+postResponse.getBody().getData());
	        Long idEnt=  postResponse.getBody().getData();
	        assertNotNull(postResponse.getBody().getHttpStatus());
	        assertEquals(postResponse.getBody().getHttpStatus(), 200);
	        assertNotNull(postResponse.getBody().getData());
	        
	        User entityUser = restTemplate.getForObject(getRootUrl() + "/api/users/" + idEnt, User.class);
	        System.out.println(entityUser.getId());
	        assertNotNull(entityUser);
	        
	        
	        headers = new HttpHeaders();
	        entityAll = new HttpEntity<String>(null, headers);
	        response = restTemplate.exchange(getRootUrl() + "/api/users/"+ idEnt+"/roles",
	        HttpMethod.GET, entityAll,String.class);  
	        assertNotNull(response.getBody());
	        
	        RolesDto roleChange = new RolesDto();
	        String[] st={"USER" ,"KEEPER"};
	        roleChange.setName(st);
	        ResponseEntity<Response> postResponse2 = restTemplate.postForEntity(getRootUrl() + "/api/users/"+idEnt+"/roles", roleChange,Response.class);
	        System.out.println("User: "+postResponse2.getBody());
	        assertNotNull(postResponse2.getBody().getHttpStatus());
	        assertEquals(postResponse2.getBody().getHttpStatus(), 200);
	        assertTrue(postResponse2.getBody().isData());
	    }
}
class Response {

	   private int httpStatus;
	   private boolean data;
	   public Response(boolean data){
		   this(data,200);
		  }
	    public Response(boolean data,int httpStatus){
	    this.httpStatus = httpStatus;
	    this.data = data;  

	  }
		public int getHttpStatus() {
			return httpStatus;
		}
		public void setHttpStatus(int httpStatus) {
			this.httpStatus = httpStatus;
		}
		public boolean isData() {
			return data;
		}
		public void setData(boolean data) {
			this.data = data;
		}

		
	    
}
class ResponseForId {

	   private int httpStatus;
	   private Long data;
	   public ResponseForId(Long data){
		   this(data,200);
		  }
	    public ResponseForId(Long data,int httpStatus){
	    this.httpStatus = httpStatus;
	    this.data = data;  

	  }
		public int getHttpStatus() {
			return httpStatus;
		}
		public void setHttpStatus(int httpStatus) {
			this.httpStatus = httpStatus;
		}
		public Long getData() {
			return data;
		}
		public void setData(Long data) {
			this.data = data;
		}

		
	    
}

