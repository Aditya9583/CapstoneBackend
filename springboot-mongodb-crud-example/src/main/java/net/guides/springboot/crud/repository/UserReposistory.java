package net.guides.springboot.crud.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import net.guides.springboot.crud.model.User;


	@Repository
	public interface UserReposistory extends MongoRepository<User, Long>{

		User findByusername(String username);

	}

