package com.sarra.gestion;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sarra.gestion.entities.Musique;
import com.sarra.gestion.service.MusiqueService;

@SpringBootApplication
public class Musique2Application implements CommandLineRunner  {
	@Autowired

	MusiqueService musiqueService;
	public static void main(String[] args) {
	SpringApplication.run(Musique2Application.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		//musiqueService.saveMusique(new Musique("hello", 3.0, new Date()));
		
	}

}
