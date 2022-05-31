package com.sarra.gestion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sarra.gestion.entities.Genre;
import com.sarra.gestion.entities.Musique;
import com.sarra.gestion.repos.GenreRepository;
import com.sarra.gestion.repos.MusiqueRepository;

@Service
public class MusiqueServiceImpl implements MusiqueService {
	@Autowired
	MusiqueRepository musiqueRepository;
	@Autowired
	GenreRepository genreRepository;
	@Override
	public Musique saveMusique(Musique m) {
		return musiqueRepository.save(m);
	}
	@Override
	public Musique updateMusique(Musique m) {
	return musiqueRepository.save(m);
	}
	@Override
	public void deleteMusique(Musique m) {
		musiqueRepository.delete(m);
	}
	@Override
	public void deleteMusiqueById(Long id) {
		musiqueRepository.deleteById(id);
	}
	@Override
	public Musique getMusique(Long id) {
	return musiqueRepository.findById(id).get();
	}
	@Override
	public List<Musique> getAllMusiques() {
	return musiqueRepository.findAll();
	}
		
	@Override
	public List<Musique> findByNomMusique(String nom) {
	return musiqueRepository.findByNomMusique(nom);
	}
	@Override
	public List<Musique> findByNomMusiqueContains(String nom) {
	return musiqueRepository.findByNomMusiqueContains(nom);
	}
	@Override
	public List<Musique> findByNomDuree(String nom, Double duree) {
		return musiqueRepository.findByNomDuree(nom, duree);
	}
	@Override
	public List<Musique> findByGenre(Genre genre) {
	return musiqueRepository.findByGenre(genre);
	}
	@Override
	public List<Musique> findByGenreIdGenre(Long id) {
	return musiqueRepository.findByGenreIdGenre(id);
	}

	@Override
	public List<Musique> findByOrderByNomMusiqueAsc() {
	return musiqueRepository.findByOrderByNomMusiqueAsc();
	}
	@Override
	public List<Musique> trieMusiquesNomsDuree() {
	return musiqueRepository.trierMusiquesNomsDuree();
	}
	
	@Override
	public Page<Musique> getAllMusiquesParPage(int page, int size) {
	return musiqueRepository.findAll(PageRequest.of(page, size));
	}
	@Override
	public List<Genre> getAllGenres() {
		
		return genreRepository.findAll();
	}
	
	
	}