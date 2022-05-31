package com.sarra.gestion.RestConroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sarra.gestion.entities.Musique;
import com.sarra.gestion.service.MusiqueService;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MusiqueRESTController {
	@Autowired
	MusiqueService musiqueService;
	@RequestMapping(method = RequestMethod.GET)
	public List<Musique> getAllFilms() {
	return musiqueService.getAllMusiques();
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	public Musique getFilmById(@PathVariable("id") Long id) {
	return musiqueService.getMusique(id);
	 }
	
	@RequestMapping(method = RequestMethod.POST)
	public Musique createProduit(@RequestBody Musique musique) {
	return musiqueService.saveMusique(musique);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Musique updateMusique(@RequestBody Musique musique) {
	return musiqueService.updateMusique(musique);
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
	public void deleteMusique(@PathVariable("id") Long id)
	{
	musiqueService.deleteMusiqueById(id);
	}
	
	@RequestMapping(value="/musiquegenre/{idGenre}",method = RequestMethod.GET)
	public List<Musique> getMusiquesByGenreId(@PathVariable("idGenre") Long idGenre) {
	return musiqueService.findByGenreIdGenre(idGenre);
	}

	
	
	
}
