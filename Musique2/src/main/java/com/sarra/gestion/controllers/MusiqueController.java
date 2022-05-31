package com.sarra.gestion.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sarra.gestion.entities.Genre;
import com.sarra.gestion.entities.Musique;
import com.sarra.gestion.entities.Role;
import com.sarra.gestion.entities.User;
import com.sarra.gestion.security.SecurityConfig;
import com.sarra.gestion.service.GenreService;
import com.sarra.gestion.service.MusiqueService;
import com.sarra.gestion.service.UserService;

@Controller
public class MusiqueController {
	@Autowired
	MusiqueService musiqueService;
	
	
	@RequestMapping("/showCreate")
	public String showCreate(ModelMap modelMap)
	{
		List<Genre> gs = musiqueService.getAllGenres();
		Musique musique = new Musique();
		Genre g = new Genre();
		g = gs.get(0); // prendre la première catégorie de la liste
		musique.setGenre(g); //affedter une catégorie par défaut au produit pour éviter le pb avec une catégorie null
		
		modelMap.addAttribute("musique", musique);
	modelMap.addAttribute("mode", "new");
	modelMap.addAttribute("genres", gs);
	return "formMusique";
	}
	
	
	@RequestMapping("/saveMusique")
	public String saveMusique(@Valid Musique musique, BindingResult bindingResult)
	{
	if (bindingResult.hasErrors()) return "formMusique";

	musiqueService.saveMusique(musique);
	return "redirect:/ListeMusiques";
	}



	@RequestMapping("/ListeMusiques")
	public String listeFilms(ModelMap modelMap,

	@RequestParam (name="page",defaultValue = "0") int page,
	@RequestParam (name="size", defaultValue = "2") int size)

	{
	Page<Musique> fl = musiqueService.getAllMusiquesParPage(page, size);
	List<Genre> gs=genreService.getAllGenres();
	modelMap.addAttribute("musiques", fl);
	modelMap.addAttribute("genres", gs);

	modelMap.addAttribute("pages", new int[fl.getTotalPages()]);

	modelMap.addAttribute("currentPage", page);
	return "listeMusiques";
	}	

	@RequestMapping("/supprimerMusique")
	public String supprimerMusique(@RequestParam("id") Long id,

	ModelMap modelMap,
	@RequestParam (name="page",defaultValue = "0") int page,
	@RequestParam (name="size", defaultValue = "2") int size)

	{
	musiqueService.deleteMusiqueById(id);
	Page<Musique> fl = musiqueService.getAllMusiquesParPage(page,size);

	modelMap.addAttribute("musiques", fl);
	modelMap.addAttribute("pages", new int[fl.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	modelMap.addAttribute("size", size);
	return "listeMusiques";
	}

@RequestMapping("/modifierMusique")
public String editerMusique(@RequestParam("id") Long id,ModelMap modelMap)
{
	Musique f= musiqueService.getMusique(id);
modelMap.addAttribute("musique", f);
modelMap.addAttribute("mode", "edit");
List<Genre> gs = musiqueService.getAllGenres();
modelMap.addAttribute("genres", gs);
return "formMusique";
}

@RequestMapping("/updateMusique")
public String updateFilm(@ModelAttribute("musique") Musique musique,
@RequestParam("date") String date,ModelMap modelMap) throws ParseException
{
	//conversion de la date
	 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	 Date dateSortie = dateformat.parse(String.valueOf(date));
	 musique.setDateSortie(dateSortie);

	 musiqueService.updateMusique(musique);
	 List<Musique> fi = musiqueService.getAllMusiques();
	 modelMap.addAttribute("musiques", fi);
	return "listeMusiques";
	}

///********************fonctions Genre**************

@Autowired
GenreService genreService;
@RequestMapping("/showCreateG")
public String showCreateG(ModelMap modelMap)
{
modelMap.addAttribute("genre", new Genre());
modelMap.addAttribute("mode", "new");
return "formGenre";
}
@RequestMapping("/saveGenre")
public String saveGenre(@Valid Genre genre, BindingResult bindingResult)
{
if (bindingResult.hasErrors()) return "formGenre";

genreService.saveGenre(genre);
return "redirect:/ListeGenres";
}
@RequestMapping("/supprimerGenre")
public String supprimerGenre(@RequestParam("id") Long id,

ModelMap modelMap,
@RequestParam (name="page",defaultValue = "0") int page,
@RequestParam (name="size", defaultValue = "2") int size)

{
	genreService.deleteGenreById(id);
Page<Genre> g1 = genreService.getAllGenresParPage(page,size);

modelMap.addAttribute("genres", g1);
modelMap.addAttribute("pages", new int[g1.getTotalPages()]);
modelMap.addAttribute("currentPage", page);
modelMap.addAttribute("size", size);
return "listeGenres";
}

@RequestMapping("/modifierGenre")
public String editerGenre(@RequestParam("id") Long id,ModelMap modelMap)
{
Genre g= genreService.getGenres(id);
modelMap.addAttribute("genre", g);
modelMap.addAttribute("mode", "edit");
return "formGenre";
}

@RequestMapping("/updateGenre")
public String updateGenre(@ModelAttribute("genre") Genre genre,
@RequestParam("date") String date,ModelMap modelMap) throws ParseException
{

 genreService.updateGenres(genre);
 List<Genre> ge= genreService.getAllGenres();
 modelMap.addAttribute("genres", ge);
return "listeGenres";
}

@RequestMapping("/ListeGenres")
public String listeGenres(ModelMap modelMap,

@RequestParam (name="page",defaultValue = "0") int page,
@RequestParam (name="size", defaultValue = "2") int size)

{
Page<Genre> g = genreService.getAllGenresParPage(page, size);
modelMap.addAttribute("genres", g);

modelMap.addAttribute("pages", new int[g.getTotalPages()]);

modelMap.addAttribute("currentPage", page);
return "listeGenres";
}

@RequestMapping("/chercherNom")
public String chercherNom(@RequestParam("nomF") String nom,
		ModelMap modelMap)


{     
	  List <Musique> f = musiqueService.findByNomMusiqueContains(nom);
	  
	  modelMap.addAttribute("musiques",f);

	  return "recherchenom";
} 


@RequestMapping("/chercherGenre")
public String chercherGenre(@ModelAttribute("nomF")Musique formData,ModelMap modelMap, @RequestParam("g") Long g 
) {
	
	List<Genre> m = genreService.findAll();
	modelMap.addAttribute("genres", m);
	
	List<Musique> f=musiqueService.findByGenreIdGenre(g);
	modelMap.addAttribute("musiques",f);

	return "rechercheGenre";
}


//***************** fonctions user ****************


@Autowired
UserService userService;

@RequestMapping("/showCreateU")
public String showCreateU(ModelMap modelMap)
{
	
modelMap.addAttribute("user", new User());

List<Role> role = userService.getRoles();
modelMap.addAttribute("mode", "new");
modelMap.addAttribute("listeroles",role);
return "formUser";
}
@RequestMapping("/saveUser")
public String saveUser(@Valid User user, BindingResult bindingResult)
{
if (bindingResult.hasErrors()) return "formUser";
SecurityConfig sec = new SecurityConfig();
PasswordEncoder passwordEncoder = sec.passwordEncoder();
	user.setPassword(passwordEncoder.encode(user.getPassword()));

userService.saveUser(user);
return "redirect:/ListeUsers";
}

@RequestMapping("/supprimerUser")
public String supprimerUser(@RequestParam("id") Long id, ModelMap modelMap,

	@RequestParam(name = "page", defaultValue = "0") int page,
	@RequestParam(name = "size", defaultValue = "2") int size) {

	User user =userService.getUsers(id);
	user.setRoles(null);
	userService.updateUsers(user);
	userService.deleteUserById(id);
	Page<User> u = userService.getAllUsersParPage(page,
			size);
	modelMap.addAttribute("users", u);
	modelMap.addAttribute("pages", new int[u.getTotalPages()]);
	modelMap.addAttribute("currentPage", page);
	modelMap.addAttribute("size", size);
	return "listeUsers";
}

@RequestMapping("/modifierUser")
public String editerUser(@RequestParam("id") Long id,ModelMap modelMap)
{
User u= userService.getUsers(id);
List<Role> role = userService.getRoles();
modelMap.addAttribute("user", u);
modelMap.addAttribute("listeroles",role);
modelMap.addAttribute("mode", "edit");

return "formUser";
}

@RequestMapping("/updateUser")
public String updateUser(@ModelAttribute("user") User user,
@RequestParam("date") String date,ModelMap modelMap) throws ParseException
{
	SecurityConfig sec = new SecurityConfig();
	 PasswordEncoder passwordEncoder = sec.passwordEncoder();
	user.setPassword(passwordEncoder.encode(user.getPassword()));
 userService.updateUsers(user);
 List<User> ge= userService.getAllUsers();
 List<Role> role = userService.getRoles();
 modelMap.addAttribute("users", ge);
modelMap.addAttribute("listeroles",role);
return "listeUsers";
}

@RequestMapping("/ListeUsers")
public String listeUsers(ModelMap modelMap,

@RequestParam (name="page",defaultValue = "0") int page,
@RequestParam (name="size", defaultValue = "2") int size)

{
Page<User> u = userService.getAllUsersParPage(page, size);
modelMap.addAttribute("users", u);

modelMap.addAttribute("pages", new int[u.getTotalPages()]);

modelMap.addAttribute("currentPage", page);
return "listeUsers";
}


}