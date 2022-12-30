package net.guides.springboot.crud.repository;



import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import net.guides.springboot.crud.model.AddProduct;




	@Repository
	public interface ProductReposistory extends MongoRepository<AddProduct, Long>{

		AddProduct findByid(long id);

		Optional<AddProduct> findByid(String productid);

		

		

	}

