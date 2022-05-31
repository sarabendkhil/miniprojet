package com.sarra.gestion.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGenre;
	private String nomGenre;
	private String descriptionGenre;
	@JsonIgnore
	@OneToMany(mappedBy = "genre")
	private List<Musique> musiques;
	@Override
	public String toString() {
		return "Genre [idGenre=" + idGenre + ", nomGenre=" + nomGenre + ", descriptionGenre=" + descriptionGenre
				+ ", musiques=" + musiques + "]";
	}
	public Long getIdGenret() {
	return idGenre;
	}
	public void setIdGenre(Long idGenre) {
	this.idGenre = idGenre;
	}
	public String getNomGenre() {
	return nomGenre;
	}
	public void setNomGenre(String nomGenre) {
	this.nomGenre = nomGenre;
	}

}
