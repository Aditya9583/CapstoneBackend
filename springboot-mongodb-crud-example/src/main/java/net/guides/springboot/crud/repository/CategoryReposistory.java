package net.guides.springboot.crud.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import net.guides.springboot.crud.model.AddCategory;


@Repository
public interface CategoryReposistory extends MongoRepository<AddCategory, Long>{



}
