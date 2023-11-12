package com.example.peterspetitions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PeterspetitionsApplication {

	public static void main(String[] args) {
		Petition p1 = new Petition("hello", "this is a test, hello", new User("peter", "peter@email.com"));
		Petition p2 = new Petition("New Petition", "something about birds?", new User("bob", "b@o.b"));
		//Petition styl = new Petition("some style", "", new User("", ""), "style.css");
		//Petition scpt = new Petition("some Script", "", new User("", ""), "script.js");
		SpringApplication.run(PeterspetitionsApplication.class, args);
	}

}
