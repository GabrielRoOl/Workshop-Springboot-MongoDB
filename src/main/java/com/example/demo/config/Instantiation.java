package com.example.demo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.AuthorDTO;
import com.example.demo.dto.ComentDTO;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PostRepository postRepository;

	@Override
	public void run(String... args) throws Exception {

		userRepository.deleteAll();
		postRepository.deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		User gabriel = new User(null, "Gabriel Rodrigues", "gabriel.r@gmail.com");

		userRepository.saveAll(Arrays.asList(maria, alex, bob, gabriel));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para São Paulo. Abraços", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AuthorDTO(maria));
		Post post3 = new Post(null, sdf.parse("21/04/2018"), "Testando post do alex", "Tô estudando pra caramba!", new AuthorDTO(gabriel));

		ComentDTO c1 = new ComentDTO("Boa Viagem mano!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
		ComentDTO c2 = new ComentDTO("Aproveite", sdf.parse("22/03/2018"), new AuthorDTO(bob));
		ComentDTO c3 = new ComentDTO("Tenha um ótimo dia!", sdf.parse("23/03/2018"), new AuthorDTO(alex));
		ComentDTO c4 = new ComentDTO("Dia 31/10/2024 estudo com mongodb!", sdf.parse("31/10/2024"), new AuthorDTO(bob));
		
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		post3.getComments().addAll(Arrays.asList(c4));
		
		postRepository.saveAll(Arrays.asList(post1, post2, post3));

		maria.getPosts().addAll(Arrays.asList(post1, post2));
		gabriel.getPosts().addAll(Arrays.asList(post3));
		
		userRepository.save(maria);
		userRepository.save(gabriel);

	}
}
