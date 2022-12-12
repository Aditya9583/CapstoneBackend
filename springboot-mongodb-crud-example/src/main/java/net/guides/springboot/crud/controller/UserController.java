package net.guides.springboot.crud.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot.crud.model.User;
import net.guides.springboot.crud.repository.UserReposistory;
import net.guides.springboot.crud.service.SequenceGeneratorService;



@CrossOrigin(origins = "http://localhost:4200",maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class UserController {
	@Autowired
	private UserReposistory userReposistory;
	@Autowired
	private JavaMailSender emailSender;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	public void sendEmail(String toEmail,String subject,String body) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("onlinegroceryhm@gmail.com");
		message.setTo(toEmail);
		message.setText(body);
		message.setSubject(subject);
		emailSender.send(message);
		
	}
	
	@PostMapping("/users")
	public User createEmployee( @RequestBody User user) {
		System.out.println(user);
		user.setId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
		sendEmail(user.getEmailId(),"Thank you for registration","Hi "+user.getUsername() +"Thank you for registration");
		return userReposistory.save(user);
	}
//	@GetMapping("/users/{emailId}")
//	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
//			throws ResourceNotFoundException {
//		Employee employee = employeeRepository.findById(employeeId)
//				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
//		return ResponseEntity.ok().body(employee);
//	}
}
