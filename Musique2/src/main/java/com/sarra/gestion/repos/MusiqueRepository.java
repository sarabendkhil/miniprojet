package com.sarra.gestion.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sarra.gestion.entities.Genre;
import com.sarra.gestion.entities.Musique;


@RepositoryRestResource(path = "rest")
public interface MusiqueRepository extends JpaRepository<Musique, Long> {
	 @Query("select m from Musique m  where m.nomMusique like 'ai'")
	 List<Musique> findByNomMusique(@Param("nomM") String nom);
	 
	
	
	@Query("select m from Musique m where m.nomMusique like %:nom and m.dureeMusique > :duree")
	List<Musique> findByNomDuree (@Param("nom") String nom,@Param("duree") Double duree);
	
	List<Musique> findByNomMusiqueContains(String nom);
	 
	 @Query("select m from Musique m where m.genre = ?1")
	 List<Musique> findByGenre (Genre genre);
	 
	 
	 List<Musique> findByGenreIdGenre( Long id);

	 List<Musique> findByOrderByNomMusiqueAsc();
	 
	 @Query("select m from Musique m order by m.nomMusique ASC, m.dureeMusique DESC")
		List<Musique> trierMusiquesNomsDuree ();

	 
}