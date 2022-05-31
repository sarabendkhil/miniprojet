package com.sarra.gestion.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

@Entity
public class Musique {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idMusique;
	@NotNull
	@Size (min = 4,max = 50)
	private String nomMusique;
	@Min(value = 1)
	 @Max(value = 10)
	private Double dureeMusique;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@PastOrPresent
	private Date dateSortie;
	@ManyToOne
	private Genre genre;


	public Musique() {
		super();
	}


	public Long getIdMusique() {
		return idMusique;
	}


	public void setIdMusique(Long idMusique) {
		this.idMusique = idMusique;
	}


	public String getNomMusique() {
		return nomMusique;
	}


	public void setNomMusique(String nomMusique) {
		this.nomMusique = nomMusique;
	}


	public Double getDureeMusique() {
		return dureeMusique;
	}


	public void setDureeMusique(Double dureeMusique) {
		this.dureeMusique = dureeMusique;
	}


	public Date getDateSortie() {
		return dateSortie;
	}


	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}


	public Genre getGenre() {
		return genre;
	}


	public void setGenre(Genre genre) {
		this.genre = genre;
	}


	public Musique(String nomMusique, Double dureeMusique, Date dateSortie) {
		super();
		this.nomMusique = nomMusique;
		this.dureeMusique = dureeMusique;
		this.dateSortie = dateSortie;
	}

	
	
	


}
