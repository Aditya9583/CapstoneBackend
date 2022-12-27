package net.guides.springboot.crud.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import net.guides.springboot.crud.model.AddProduct;




	@Repository
	public interface ProductReposistory extends MongoRepository<AddProduct, Long>{

		

	}

