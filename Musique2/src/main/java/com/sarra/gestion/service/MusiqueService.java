package com.sarra.gestion.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sarra.gestion.entities.Genre;
import com.sarra.gestion.entities.Musique;

public interface MusiqueService {
	Musique saveMusique(Musique f);
	Musique updateMusique(Musique f);
	void deleteMusique(Musique f);
	void deleteMusiqueById(Long id);
	Musique getMusique(Long id);
	List<Musique> getAllMusiques();
	 List<Genre> getAllGenres();
	List<Musique> findByNomMusique(String nom);
	List<Musique> findByNomMusiqueContains(String nom);
	List<Musique> findByNomDuree (String nom, Double duree);
	List<Musique> findByGenre (Genre genre);
	List<Musique> findByGenreIdGenre(Long id);
	List<Musique> findByOrderByNomMusiqueAsc();
	List<Musique> trieMusiquesNomsDuree();
	Page<Musique> getAllMusiquesParPage(int page, int size);

}
