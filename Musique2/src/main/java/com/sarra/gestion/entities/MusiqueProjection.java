package com.sarra.gestion.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "nomMusique", types = { Musique.class })
public interface MusiqueProjection {
	public String getNomMusique();

}
