package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	List<Post> findByTitleContainingAllIgnoreCase(String text);

	@Query("{ 'title' : { $regex: ?0, $options: 'i' } }")
	List<Post> seachTitle(String text);

	@Query("{ $and: [ { date: { $gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title' : { $regex: ?0, $options: 'i' } }, { 'body' : { $regex: ?0, $options: 'i' } }, { 'comments.text' : { $regex: ?0, $options: 'i' } }, { 'comments.author' : { $regex: ?0, $options: 'i' } }, { 'author.name' : { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> fullSeach(String text, Date minDate, Date maxDate);
}
