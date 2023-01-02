package net.guides.springboot.crud.controller;

import java.util.List;
import java.util.Optional;

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
import net.guides.springboot.crud.model.AddCart;
import net.guides.springboot.crud.model.AddCategory;
import net.guides.springboot.crud.model.AddProduct;
import net.guides.springboot.crud.model.AuthRequest;
import net.guides.springboot.crud.model.AuthenticateModel;
import net.guides.springboot.crud.model.ProductDetailsModel;
import net.guides.springboot.crud.model.ReviewModel;
import net.guides.springboot.crud.model.User;
import net.guides.springboot.crud.model.VendorNumbers;
import net.guides.springboot.crud.repository.CartReposistory;
import net.guides.springboot.crud.repository.CategoryReposistory;
import net.guides.springboot.crud.repository.ProductReposistory;
import net.guides.springboot.crud.repository.ReviewReposistory;
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
	private CategoryReposistory categoryReposistory;
	@Autowired
	private CartReposistory cartReposistory;
	@Autowired
	private ReviewReposistory reviewReposistory;
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
	public ResponseEntity<List<User>> getEmployeeByrole( )
		throws ResourceNotFoundException {
	List<User> user = userReposistory.findByrole("Vendor");
			
	return ResponseEntity.ok().body(user);
}
	//to send mail to user
	public void sendEmail(String toEmail,String subject,String body) {
	SimpleMailMessage message=new SimpleMailMessage();
	message.setFrom("onlinegroceryhm@gmail.com");
	message.setTo(toEmail);
	message.setText(body);
	message.setSubject(subject);
	emailSender.send(message);
		
}
	
	
	@PostMapping("/users")
	public User createCustomer( @RequestBody User user) {
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
		model.setEmailId(user.getEmailId());
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
	@PostMapping("/productDetails")
	public ResponseEntity<Optional<AddProduct>>getOneProduct(@RequestBody ProductDetailsModel productdetails){
		Optional<AddProduct> product=productReposistory.findById(productdetails.getId());
		return ResponseEntity.ok().body(product);
	}

	
	@PostMapping("/addCategory")
	public ResponseEntity<AddCategory> postCategory(@RequestBody AddCategory category){
		category.setId(sequenceGeneratorService.generateSequence(AddCategory.SEQUENCE_NAME));
		categoryReposistory.save(category);
		return ResponseEntity.ok().body(category);
		
	}
	@PostMapping("/updatePrice")
	public ResponseEntity<AddProduct>updatePrice(@RequestBody AddProduct category)
	{
		AddProduct product=productReposistory.findByid(category.getId());
		product.setPrice(category.getPrice());
		productReposistory.save(product);
		return ResponseEntity.ok().body(product);
	}
	@PostMapping("/addtocart")
	public ResponseEntity<AddCart> addtocart(@RequestBody AddCart cart)
	{
		cart.setId(sequenceGeneratorService.generateSequence(AddCart.SEQUENCE_NAME));
		cartReposistory.save(cart);
		String productdetails="";
		for(int i=0;i<cart.getProducts().size();i++)
		{
			productdetails=productdetails+(i+1)+" "+cart.getProducts().get(i)+"\n";
		}
		String message="Your order details are:\n"+productdetails+ "\n Thank you for shopping with us.Your order will be delivered soon."; 
		sendEmail(cart.getEmail(), "Thank you for shopping with us", message);
		return ResponseEntity.ok().body(cart);
		
	}
	@PostMapping("/addReview")
	public ResponseEntity<ReviewModel>addReviews(@RequestBody ReviewModel review)
	{
		review.setId(sequenceGeneratorService.generateSequence(ReviewModel.SEQUENCE_NAME));
		reviewReposistory.save(review);
		return ResponseEntity.ok().body(review);
		
	}
	
	@GetMapping("/getAllReview")
	public ResponseEntity<List<ReviewModel>> getallReview(){
		List<ReviewModel> reviews=reviewReposistory.findAll();
		return ResponseEntity.ok().body(reviews);
	}

	
}
