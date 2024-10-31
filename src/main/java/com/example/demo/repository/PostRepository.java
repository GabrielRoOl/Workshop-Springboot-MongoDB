package com.example.demo.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

	List<Post> findByTitleContainingAllIgnoreCase(String text);
	
	@Query("{ 'title' : { $regex: ?0, $options: 'i' } }")
	List<Post> seachTitle(String text);
}
