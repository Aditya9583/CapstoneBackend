//package net.guides.springboot.crud.controller;
//
//
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.query.Criteria;
//import org.springframework.data.mongodb.core.query.Query;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import net.guides.springboot.crud.model.Login;
//import net.guides.springboot.crud.model.User;
//import net.guides.springboot.crud.repository.UserReposistory;
//import net.guides.springboot.crud.service.SequenceGeneratorService;
//
//
//
//@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
//@RestController
//@RequestMapping("/api/v1")
//public class UserController {
//	@Autowired
//	private UserReposistory userReposistory;
//	@Autowired
//	private JavaMailSender emailSender;
//	
//	@Autowired
//	private SequenceGeneratorService sequenceGeneratorService;
//	
//	@Autowired
//	private MongoTemplate mongoTemplate; 
//	public void sendEmail(String toEmail,String subject,String body) {
//		SimpleMailMessage message=new SimpleMailMessage();
//		message.setFrom("onlinegroceryhm@gmail.com");
//		message.setTo(toEmail);
//		message.setText(body);
//		message.setSubject(subject);
//		emailSender.send(message);
//		
//	}
//	
////	@GetMapping("/")
////	public String welcome() {
////		return "Welcome to Online Grocessay";
////	}
//	@PostMapping("/users")
//	public User createEmployee( @RequestBody User user) {
//		System.out.println(user);
//		user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
//		sendEmail(user.getEmailId(),"Thank you for registration","Hi "+user.getUsername() +"Thank you for registration");
//		return userReposistory.save(user);
//	}
//	@PostMapping("/users/login")
//	public User createSignIn( @RequestBody Login login) {
//		System.out.println(login);
//		Query query = new Query();
//		query.addCriteria(Criteria.where("username").is(login.getUsername()));
//		List<User> users = mongoTemplate.find(query, User.class);
//		if(users.get(0).getPassword().equals(login.getPassword()))
//				{
//					return users.get(0);
//				}
//		
//		return null;
//	}
////	@GetMapping("/users/{emailId}")
////	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
////			throws ResourceNotFoundException {
////		Employee employee = employeeRepository.findById(employeeId)
////				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
////		return ResponseEntity.ok().body(employee);
////	}
//}
