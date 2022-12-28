package net.guides.springboot.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot.crud.Util.JwtUtil;
import net.guides.springboot.crud.exception.ResourceNotFoundException;
import net.guides.springboot.crud.model.AddProduct;
import net.guides.springboot.crud.model.AuthRequest;
import net.guides.springboot.crud.model.AuthenticateModel;
import net.guides.springboot.crud.model.User;
import net.guides.springboot.crud.model.VendorNumbers;
import net.guides.springboot.crud.repository.ProductReposistory;
import net.guides.springboot.crud.repository.UserReposistory;
import net.guides.springboot.crud.service.SequenceGeneratorService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class WelcomeController {
	@Autowired
	private UserReposistory userReposistory;
	@Autowired
	private ProductReposistory productReposistory;

	@Autowired
	private JwtUtil jwtutil;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JavaMailSender emailSender;
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	@Autowired
	private AuthenticateModel model;
	@GetMapping("/")
	public String welcome() {
		return "Welcome to India";
	}
	
	@GetMapping("/vendors")
	public ResponseEntity<List<User>> getEmployeeById( )
		throws ResourceNotFoundException {
	List<User> user = userReposistory.findByrole("Vendor");
			
	return ResponseEntity.ok().body(user);
}
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
	
	@PostMapping("/authenticate")
	public AuthenticateModel  generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		User user= userReposistory.findByusername(authRequest.getUsername());
		model.setUsername(authRequest.getUsername());
		model.setRole(user.getRole());
		model.setToken(jwtutil.generateToken(authRequest.getUsername()));
	}catch(Exception ex)
		{
		throw new Exception("Invalid username or password");
		}
		
		
		return model;
		
	}
	
	
	@PostMapping("/vendorsPermit")
	public ResponseEntity<User> putVendorByUsername(@RequestBody VendorNumbers vendors){
		User user= userReposistory.findByusername(vendors.getUsername());
		user.setRole("Vendor_access");
		userReposistory.save(user);
		return ResponseEntity.ok().body(user);
	}
	
	@PostMapping("/addProducts")
	public ResponseEntity<AddProduct> addProducts(@RequestBody AddProduct products)
	{
		products.setId(sequenceGeneratorService.generateSequence(AddProduct.SEQUENCE_NAME));
		productReposistory.save(products);
		return ResponseEntity.ok().body(products);
	}
	
	@GetMapping("/products")
	public ResponseEntity<List<AddProduct>> getallProduct(){
		List<AddProduct> products=productReposistory.findAll();
		return ResponseEntity.ok().body(products);
	}

	
}
