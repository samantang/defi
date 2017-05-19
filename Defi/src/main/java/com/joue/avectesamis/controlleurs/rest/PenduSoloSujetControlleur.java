package com.joue.avectesamis.controlleurs.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joue.avectesamis.dao.PenduDao;
import com.joue.avectesamis.entites.Friend;
import com.joue.avectesamis.entites.jeux.Artistes;
import com.joue.avectesamis.entites.jeux.Nobels;
import com.joue.avectesamis.entites.jeux.Pays_Capitale;
import com.joue.avectesamis.entites.jeux.President;
import com.joue.avectesamis.entites.jeux.pendu.PenduDicoSolo;
import com.joue.avectesamis.metier.ChallengeMetier;
import com.joue.avectesamis.models.GameModel;
import com.joue.avectesamis.models.PenduModel;
import com.joue.avectesamis.models.SocialModel;
import com.joue.avectesamis.models.Word;

@Controller
public class PenduSoloSujetControlleur {

	@Autowired
	private PenduDao penduDao;
	@Autowired
	private ChallengeMetier metier;
	
	
	@RequestMapping(value="/soloSujet" , method=RequestMethod.GET)
	public String soloSujets(Model model, HttpServletRequest req, PenduModel penduModel ){
		Map<String, Object> map = new HashMap<String, Object>();
		
		HttpSession session = req.getSession();
		Long id =  1L;
//				(Long) session.getAttribute("id");
		Friend moi = metier.getFriend(id);
		 
//		recuperation de tous mes soloSujets
		penduModel.setMesSujetsoSolo(penduDao.mesSujetsSolos(id));
   
		 
		
		map.put("penduModel", penduModel);
		return "soloSujet";
		
	}
	@RequestMapping(value="penduSoloSujetJeu", method=RequestMethod.GET)
	public String penduSoloSujetJeu(Model model, PenduModel penduModel, HttpServletRequest request){
		HttpSession session = request.getSession();
			
//			Pays
			Random random = new  Random();
			char lettre = (char) (random.nextInt(26)+'a');
			char lettrePays = Character.toUpperCase(lettre);
			List<String> sujetPays = new ArrayList<String>();
			List<Pays_Capitale> sujetPaysP= penduDao.challengeSujetPays(lettre);
			
			for (Pays_Capitale pays_Capitale : sujetPaysP) {
				sujetPays.add(pays_Capitale.getPays());
			}
			String csPays= sujetPays.get(random.nextInt(sujetPays.size()));
			String motPaysEspace = csPays.toUpperCase();
			
//			pour la suppression des espaces
			String motPays = motPaysEspace.replaceAll("\\s", "");
									
			String motCachePays="";
			String motCacheDepartPays="";
			for (int i = 0; i < motPays.length(); i++) {
				motCachePays = new String(motCachePays+"*");
				if (motPays.charAt(i)==lettrePays) {
					motCacheDepartPays = new String(motCacheDepartPays+lettrePays);
				} else {
					motCacheDepartPays = new String(motCacheDepartPays+"*");
				}
			}
			int longueurMotPays = motPays.length();
			
			System.out.println("le pays choisit est : "+motPays);
			String idBouton = String.valueOf(lettrePays);
			String lettreStringPays = String.valueOf(lettrePays);
			
			session.setAttribute("lettreStringPays", lettreStringPays);
			session.setAttribute("motPays", motPays);
			
			model.addAttribute("motCacheDepartPays", motCacheDepartPays);
			model.addAttribute("motCachePays", motCachePays);
			model.addAttribute("motPays", motPays);
			model.addAttribute("lettrePays", lettrePays);
			model.addAttribute("idBoutonPays", idBouton+"Pays");
			model.addAttribute("longueurMotPays", longueurMotPays);
			
			
//			Capitales
			lettre = (char) (random.nextInt(26)+'a');
			char lettreCapitales = Character.toUpperCase(lettre);
			
			List<String> sujetCapitales = new ArrayList<String>();
			List<Pays_Capitale> sujetCapitalesP= penduDao.challengeSujetCapitale(lettre);
			for (Pays_Capitale pays_Capitale : sujetCapitalesP) {
				sujetCapitales.add(pays_Capitale.getCapitale());
			}		
			String csCapitales= sujetCapitales.get(random.nextInt(sujetCapitales.size()));
			String motCapitalesEspace = csCapitales.toUpperCase();
			
			String motCapitales = motCapitalesEspace.replaceAll("\\s", "");
									
			String motCacheCapitales="";
			String motCacheDepartCapitales="";
			for (int i = 0; i < motCapitales.length(); i++) {
				motCacheCapitales = new String(motCacheCapitales+"*");
				if (motCapitales.charAt(i)==lettreCapitales) {
					motCacheDepartCapitales = new String(motCacheDepartCapitales+lettreCapitales);
				} else {
					motCacheDepartCapitales = new String(motCacheDepartCapitales+"*");
				}
			}
			int longueurMotCapitales = motCapitales.length();
			
			System.out.println("la capitale choisie est: "+motCapitales);
			String idBoutonCaptiale = String.valueOf(lettreCapitales);
			String lettreStringCapitale = String.valueOf(lettrePays);
			
			session.setAttribute("lettreStringCapitales", lettreStringCapitale);
			session.setAttribute("motCapitales", motCapitales);
			
			
			model.addAttribute("motCacheDepartCapitales", motCacheDepartCapitales);
			model.addAttribute("motCacheCapitales", motCacheCapitales);
			model.addAttribute("motCapitales", motCapitales);
			model.addAttribute("lettreCapitales", lettreCapitales);
			model.addAttribute("longueurMotCapitales", longueurMotCapitales);
			model.addAttribute("idBoutonCaptiales", idBoutonCaptiale+"Capitales");
			
			
//			Nobels
			lettre = (char) (random.nextInt(26)+'a');
			char lettreNobels = Character.toUpperCase(lettre);
			
			List<String> sujetNobels= new ArrayList<String>();
			List<Nobels> sujetNobelsN = penduDao.challengeSujetNobel(lettre);
			for (Nobels nobels : sujetNobelsN) {
				sujetNobels.add(nobels.getNom());
			}			
			String csNobels= sujetNobels.get(random.nextInt(sujetNobels.size()));
			String motNobelsEspace = csNobels.toUpperCase();
			
			String motNobels = motNobelsEspace.replaceAll("\\s", "");
									
			String motCacheNobels="";
			String motCacheDepartNobels ="";
			for (int i = 0; i < motNobels.length(); i++) {
				motCacheNobels = new String(motCacheNobels+"*");
				if (motNobels.charAt(i)==lettreNobels) {
					motCacheDepartNobels = new String(motCacheDepartNobels+lettreNobels);
				} else {
					motCacheDepartNobels = new String(motCacheDepartNobels+"*");
				}
			}
			int longueurMotNobels = motNobels.length();
			
			System.out.println("la Nobel choisie est: "+motNobels);
			String idBoutonNobels = String.valueOf(lettreNobels);
			String lettreStringNobels = String.valueOf(lettreNobels);
			
			session.setAttribute("lettreStringNobels", lettreStringNobels);
			session.setAttribute("motNobels", motNobels);
			
			model.addAttribute("motCacheDepartNobels", motCacheDepartNobels);
			model.addAttribute("motCacheNobels", motCacheNobels);
			model.addAttribute("motNobels", motNobels);
			model.addAttribute("lettreNobels", lettreNobels);
			model.addAttribute("longueurMotNobels", longueurMotNobels);
			model.addAttribute("idBoutonNobels", idBoutonNobels+"Nobels");
			
//			Artistes
			lettre = (char) (random.nextInt(26)+'a');
			char lettreArtistes = Character.toUpperCase(lettre);
			
			List<String> sujetArtistes = new ArrayList<String>();
			List<Artistes> sujetsArtistesAr= penduDao.challengeSujetArtiste(lettre);
			for (Artistes artistes : sujetsArtistesAr) {
				sujetArtistes.add(artistes.getNom());
			}
			
			String csArtistes= sujetArtistes.get(random.nextInt(sujetArtistes.size()));
			String motArtistesEspace = csArtistes.toUpperCase();
									
			String motArtistes = motArtistesEspace.replaceAll("\\s", "");
			
			String motCacheArtistes="";
			String motCacheDepartArtistes ="";
			for (int i = 0; i < motArtistes.length(); i++) {
				motCacheArtistes = new String(motCacheArtistes+"*");
				if (motArtistes.charAt(i)==lettreArtistes) {
					motCacheDepartArtistes = new String(motCacheDepartArtistes+lettreArtistes);
				} else {
					motCacheDepartArtistes = new String(motCacheDepartArtistes+"*");
				}
			}
			int longueurMotArtistes = motArtistes.length();
			
			System.out.println("lArtiste choisi est: "+motArtistes);
			String idBoutonArtistes = String.valueOf(lettreArtistes);
			String lettreStringArtistes = String.valueOf(lettreArtistes);
			
			session.setAttribute("lettreStringArtistes", lettreStringArtistes);
			session.setAttribute("motArtistes", motArtistes);
			
			model.addAttribute("motCacheDepartArtistes", motCacheDepartArtistes);
			model.addAttribute("motCacheArtistes", motCacheArtistes);
			model.addAttribute("motArtistes", motArtistes);
			model.addAttribute("lettreArtistes", lettreArtistes);
			model.addAttribute("longueurMotArtistes", longueurMotArtistes);
			model.addAttribute("idBoutonArtistes", idBoutonArtistes+"Artistes");
			
//			President
			lettre = (char) (random.nextInt(26)+'a');
			char lettrePresident = Character.toUpperCase(lettre);
			
			List<String> sujetPresident = new ArrayList<String>();
			List<President> sujetPresidentsP = penduDao.challengeSujetPresi(lettre);
			for (President president : sujetPresidentsP) {
				sujetPresident.add(president.getNom());
			}			
			
			String csPresident= sujetPresident.get(random.nextInt(sujetPresident.size()));
			String motPresidentEspace = csPresident.toUpperCase();
			
//			pour la suppression des espaces
			 String motPresident = motPresidentEspace.replaceAll("\\s", "");
			
			
									
			String motCachePresident="";
			String motCacheDepartPresident="";
			for (int i = 0; i < motPresident.length(); i++) {
				motCachePresident = new String(motCachePresident+"*");
				if (motPresident.charAt(i)==lettrePresident) {
					motCacheDepartPresident = new String(motCacheDepartPresident+lettrePresident);
				} else {
					motCacheDepartPresident = new String(motCacheDepartPresident+"*");
				}
			}
			int longueurMotPresident = motPresident.length();
			
			System.out.println("le Presi choisi est: "+motPresident);
			String idBoutonPresident = String.valueOf(lettrePresident);
			String lettreStringPresident = String.valueOf(lettrePresident);
			
			session.setAttribute("lettreStringPresident", lettreStringPresident);
			session.setAttribute("motPresident", motPresident);
			
			model.addAttribute("motCacheDepartPresident", motCacheDepartPresident);
			model.addAttribute("motCachePresident", motCachePresident);
			model.addAttribute("motPresident", motPresident);
			model.addAttribute("lettrePresident", lettrePresident);
			model.addAttribute("longueurMotPresident", longueurMotPresident);
			model.addAttribute("idBoutonPresident", idBoutonPresident+"President");

			
			
			
			int longueurMot = longueurMotPays+longueurMotCapitales+longueurMotNobels+longueurMotArtistes+longueurMotPresident;
			model.addAttribute("longueurMot", longueurMot);
								
		return "penduSoloSujetJeu";
	}
	
	@RequestMapping(value="penduSoloSujetCorrection", method=RequestMethod.GET)
	public String penduSoloSujetCorrection(PenduModel penduModel, HttpServletRequest request){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		Long id = 2L;
		
//		recupération des données saisis par l'utilisateur pour les comparer ceux qui sont en session afin de déterminer un score
		
		
		return "penduSoloSujetCorrection"; 
		
	}
	@RequestMapping(value="resultatPenduPays")
	public String resultatPenduPays(Model model, SocialModel sm, HttpServletRequest req, GameModel gm, PenduModel penduModel){
		HttpSession session = req.getSession();
		String motComplet = (String) session.getAttribute("motPays");
		String nbErreurs = req.getParameter("nbErreursPays");
		int nbErreursInt = Integer.parseInt(nbErreurs);
		
		String lettreDevoilee = (String) session.getAttribute("lettreStringPays");
		char lettreChar = lettreDevoilee.charAt(0);
		
		String motUser = req.getParameter("motUser");
		int points =0;
		int pointsMax =0;
		int pointsPerdus =0;
		int nbFoisLettreDansMot =0;
		if(motUser!="null"){
			for(int i =0; i<motComplet.length(); i++){
				if(motComplet.charAt(i)==lettreChar){
					nbFoisLettreDansMot = nbFoisLettreDansMot + 10;
				}
				if(motComplet.charAt(i)== motUser.charAt(i)){
					System.out.println("motComplet.charAt(i) = "+motComplet.charAt(i)+" motUser.charAt(i)"+motUser.charAt(i));
					points = points + 10;
					pointsMax = pointsMax+10;
				}
				System.out.println("le nombre de points Total "+points);
				
			}
			motUser = null;
		}	
		pointsPerdus = nbErreursInt*3;
		points = points - pointsPerdus;
		
		if(points>nbFoisLettreDansMot){
			points = points - nbFoisLettreDansMot;
			pointsMax = pointsMax - nbFoisLettreDansMot;
		}else {
			points = 0;
		}
//		je peux inserer les données dans la base que si "motUser = null"		
		System.out.println("le nombre de points ganés au final = "+points);
		
		System.out.println("le nombre Erreurs "+nbErreurs);
		
//		Pour les points: chaque lettre rapporte 10 points
//		quand on trouve tout le mot avant la fin, chaque 10 secondes rapportent 5 points de BONUS
		
		
//		bien que le timer n'est plus visible, mais il continu à s'exécuter alors on donne comme longueur du mot un nombre arbitraire
//		pour etre sûr qu'avant la fin du décompte l'utilisateur a quitter la page (le mieux serai d'arreter la timer, A VOIR APRES)
		int longueurMot =5000;
		
		session.setAttribute("pointsPays", points);
		session.setAttribute("pointsMaxPays", pointsMax);
		session.setAttribute("motCompletPays", motComplet);
		session.setAttribute("nbErreursPays", nbErreurs);
		
		model.addAttribute("longueurMot", longueurMot);
		model.addAttribute("nbErreurs", nbErreurs);
		model.addAttribute("motComplet", motComplet);
		model.addAttribute("penduModel", penduModel);
		model.addAttribute("points", points);
		model.addAttribute("pointsMax", pointsMax);
		model.addAttribute("pointsPerdus", pointsPerdus);
		return "resultatPenduPays";
	}
	@RequestMapping(value="resultatPenduCapitales")
	public String resultatPenduCapitales(Model model, SocialModel sm, HttpServletRequest req, GameModel gm, PenduModel penduModel){
		HttpSession session = req.getSession();
		String motComplet = (String) session.getAttribute("motCapitales");
		String nbErreurs = req.getParameter("nbErreursCapitales");
		int nbErreursInt = Integer.parseInt(nbErreurs);
		
		String lettreDevoilee = (String) session.getAttribute("lettreStringCapitales");
		char lettreChar = lettreDevoilee.charAt(0);
		
		String motUser = req.getParameter("motUser");
		int points =0;
		int pointsMax =0;
		int pointsPerdus =0;
		int nbFoisLettreDansMot =0;
		if(motUser!="null"){
			for(int i =0; i<motComplet.length(); i++){
				if(motComplet.charAt(i)==lettreChar){
					nbFoisLettreDansMot = nbFoisLettreDansMot + 10;
				}
				if(motComplet.charAt(i)== motUser.charAt(i)){
					System.out.println("motComplet.charAt(i) = "+motComplet.charAt(i)+" motUser.charAt(i)"+motUser.charAt(i));
					points = points + 10;
					pointsMax = pointsMax+10;
				}
				System.out.println("le nombre de points Total "+points);
				
			}
			motUser = null;
		}	
		pointsPerdus = nbErreursInt*3;
		points = points - pointsPerdus;
		
		if(points>nbFoisLettreDansMot){
			points = points - nbFoisLettreDansMot;
			pointsMax = pointsMax - nbFoisLettreDansMot;
		}else {
			points = 0;
		}
//		je peux inserer les données dans la base que si "motUser = null"		
		System.out.println("le nombre de points ganés au final = "+points);
		
		System.out.println("le nombre Erreurs "+nbErreurs);
		
//		Pour les points: chaque lettre rapporte 10 points
//		quand on trouve tout le mot avant la fin, chaque 10 secondes rapportent 5 points de BONUS
		
		
//		bien que le timer n'est plus visible, mais il continu à s'exécuter alors on donne comme longueur du mot un nombre arbitraire
//		pour etre sûr qu'avant la fin du décompte l'utilisateur a quitter la page (le mieux serai d'arreter la timer, A VOIR APRES)
		int longueurMot =5000;
		
		session.setAttribute("pointsCapitales", points);
		session.setAttribute("pointsMaxCapitales", pointsMax);
		session.setAttribute("motCompletCapitales", motComplet);
		session.setAttribute("nbErreursCapitales", nbErreurs);
		
		
		
		
		model.addAttribute("longueurMot", longueurMot);
		model.addAttribute("nbErreurs", nbErreurs);
		model.addAttribute("motComplet", motComplet);
		model.addAttribute("penduModel", penduModel);
		model.addAttribute("points", points);
		model.addAttribute("pointsMax", pointsMax);
		model.addAttribute("pointsPerdus", pointsPerdus);
		return "resultatPenduCapitales";
	}
	@RequestMapping(value="resultatPenduNobels")
	public String resultatPenduNobels(Model model, SocialModel sm, HttpServletRequest req, GameModel gm, PenduModel penduModel){
		HttpSession session = req.getSession();
		String motComplet = (String) session.getAttribute("motNobels");
		String nbErreurs = req.getParameter("nbErreursNobels");
		int nbErreursInt = Integer.parseInt(nbErreurs);
		
		String lettreDevoilee = (String) session.getAttribute("lettreStringNobels");
		char lettreChar = lettreDevoilee.charAt(0);
		
		String motUser = req.getParameter("motUser");
		int points =0;
		int pointsMax =0;
		int pointsPerdus =0;
		int nbFoisLettreDansMot =0;
		if(motUser!="null"){
			for(int i =0; i<motComplet.length(); i++){
				if(motComplet.charAt(i)==lettreChar){
					nbFoisLettreDansMot = nbFoisLettreDansMot + 10;
				}
				if(motComplet.charAt(i)== motUser.charAt(i)){
					System.out.println("motComplet.charAt(i) = "+motComplet.charAt(i)+" motUser.charAt(i)"+motUser.charAt(i));
					points = points + 10;
					pointsMax = pointsMax+10;
				}
				System.out.println("le nombre de points Total "+points);
				
			}
			motUser = null;
		}	
		pointsPerdus = nbErreursInt*3;
		points = points - pointsPerdus;
		
		if(points>nbFoisLettreDansMot){
			points = points - nbFoisLettreDansMot;
			pointsMax = pointsMax - nbFoisLettreDansMot;
		}else {
			points = 0;
		}
//		je peux inserer les données dans la base que si "motUser = null"		
		System.out.println("le nombre de points ganés au final = "+points);
		
		System.out.println("le nombre Erreurs "+nbErreurs);
		
//		Pour les points: chaque lettre rapporte 10 points
//		quand on trouve tout le mot avant la fin, chaque 10 secondes rapportent 5 points de BONUS
		
		
//		bien que le timer n'est plus visible, mais il continu à s'exécuter alors on donne comme longueur du mot un nombre arbitraire
//		pour etre sûr qu'avant la fin du décompte l'utilisateur a quitter la page (le mieux serai d'arreter la timer, A VOIR APRES)
		int longueurMot =5000;
		
		session.setAttribute("pointsNobels", points);
		session.setAttribute("pointsMaxNobels", pointsMax);
		session.setAttribute("motCompletNobels", motComplet);
		session.setAttribute("nbErreursNobels", nbErreurs);
		
		
		
		model.addAttribute("longueurMot", longueurMot);
		model.addAttribute("nbErreurs", nbErreurs);
		model.addAttribute("motComplet", motComplet);
		model.addAttribute("penduModel", penduModel);
		model.addAttribute("points", points);
		model.addAttribute("pointsMax", pointsMax);
		model.addAttribute("pointsPerdus", pointsPerdus);
		return "resultatPenduCapitales";
	}
	@RequestMapping(value="resultatPenduArtistes")
	public String resultatPenduArtistes(Model model, SocialModel sm, HttpServletRequest req, GameModel gm, PenduModel penduModel){
		HttpSession session = req.getSession();
		String motComplet = (String) session.getAttribute("motArtistes");
		String nbErreurs = req.getParameter("nbErreursArtistes");
		int nbErreursInt = Integer.parseInt(nbErreurs);
		
		String lettreDevoilee = (String) session.getAttribute("lettreStringArtistes");
		char lettreChar = lettreDevoilee.charAt(0);
		
		String motUser = req.getParameter("motUser");
		int points =0;
		int pointsMax =0;
		int pointsPerdus =0;
		int nbFoisLettreDansMot =0;
		if(motUser!="null"){
			for(int i =0; i<motComplet.length(); i++){
				if(motComplet.charAt(i)==lettreChar){
					nbFoisLettreDansMot = nbFoisLettreDansMot + 10;
				}
				if(motComplet.charAt(i)== motUser.charAt(i)){
					System.out.println("motComplet.charAt(i) = "+motComplet.charAt(i)+" motUser.charAt(i)"+motUser.charAt(i));
					points = points + 10;
					pointsMax = pointsMax+10;
				}
				System.out.println("le nombre de points Total "+points);
				
			}
			motUser = null;
		}	
		pointsPerdus = nbErreursInt*3;
		points = points - pointsPerdus;
		
		if(points>nbFoisLettreDansMot){
			points = points - nbFoisLettreDansMot;
			pointsMax = pointsMax - nbFoisLettreDansMot;
		}else {
			points = 0;
		}
//		je peux inserer les données dans la base que si "motUser = null"		
		System.out.println("le nombre de points ganés au final = "+points);
		
		System.out.println("le nombre Erreurs "+nbErreurs);
		
//		Pour les points: chaque lettre rapporte 10 points
//		quand on trouve tout le mot avant la fin, chaque 10 secondes rapportent 5 points de BONUS
		
		
//		bien que le timer n'est plus visible, mais il continu à s'exécuter alors on donne comme longueur du mot un nombre arbitraire
//		pour etre sûr qu'avant la fin du décompte l'utilisateur a quitter la page (le mieux serai d'arreter la timer, A VOIR APRES)
		int longueurMot =5000;
		
		session.setAttribute("pointsArtistes", points);
		session.setAttribute("pointsMaxArtistes", pointsMax);
		session.setAttribute("motCompletArtistes", motComplet);
		session.setAttribute("nbErreursArtistes", nbErreurs);
		
		
		model.addAttribute("longueurMot", longueurMot);
		model.addAttribute("nbErreurs", nbErreurs);
		model.addAttribute("motComplet", motComplet);
		model.addAttribute("penduModel", penduModel);
		model.addAttribute("points", points);
		model.addAttribute("pointsMax", pointsMax);
		model.addAttribute("pointsPerdus", pointsPerdus);
		return "resultatPenduCapitales";
	}
	@RequestMapping(value="resultatPenduPresidents")
	public String resultatPenduPresidents(Model model, SocialModel sm, HttpServletRequest req, GameModel gm, PenduModel penduModel){
		HttpSession session = req.getSession();
		String motComplet = (String) session.getAttribute("motPresidents");
		String nbErreurs = req.getParameter("nbErreursPresidents");
		int nbErreursInt = Integer.parseInt(nbErreurs);
		
		String lettreDevoilee = (String) session.getAttribute("lettreStringPresidents");
		char lettreChar = lettreDevoilee.charAt(0);
		
		String motUser = req.getParameter("motUser");
		int points =0;
		int pointsMax =0;
		int pointsPerdus =0;
		int nbFoisLettreDansMot =0;
		if(motUser!="null"){
			for(int i =0; i<motComplet.length(); i++){
				if(motComplet.charAt(i)==lettreChar){
					nbFoisLettreDansMot = nbFoisLettreDansMot + 10;
				}
				if(motComplet.charAt(i)== motUser.charAt(i)){
					System.out.println("motComplet.charAt(i) = "+motComplet.charAt(i)+" motUser.charAt(i)"+motUser.charAt(i));
					points = points + 10;
					pointsMax = pointsMax+10;
				}
				System.out.println("le nombre de points Total "+points);
				
			}
			motUser = null;
		}	
		pointsPerdus = nbErreursInt*3;
		points = points - pointsPerdus;
		
		if(points>nbFoisLettreDansMot){
			points = points - nbFoisLettreDansMot;
			pointsMax = pointsMax - nbFoisLettreDansMot;
		}else {
			points = 0;
		}
//		je peux inserer les données dans la base que si "motUser = null"		
		System.out.println("le nombre de points ganés au final = "+points);
		
		System.out.println("le nombre Erreurs "+nbErreurs);
		
//		Pour les points: chaque lettre rapporte 10 points
//		quand on trouve tout le mot avant la fin, chaque 10 secondes rapportent 5 points de BONUS
		
		
//		bien que le timer n'est plus visible, mais il continu à s'exécuter alors on donne comme longueur du mot un nombre arbitraire
//		pour etre sûr qu'avant la fin du décompte l'utilisateur a quitter la page (le mieux serai d'arreter la timer, A VOIR APRES)
		int longueurMot =5000;
		
		session.setAttribute("pointsPresidents", points);
		session.setAttribute("pointsMaxPresidents", pointsMax);
		session.setAttribute("motCompletPresidents", motComplet);
		session.setAttribute("nbErreursPresidents", nbErreurs);
		
		
		model.addAttribute("longueurMot", longueurMot);
		model.addAttribute("nbErreurs", nbErreurs);
		model.addAttribute("motComplet", motComplet);
		model.addAttribute("penduModel", penduModel);
		model.addAttribute("points", points);
		model.addAttribute("pointsMax", pointsMax);
		model.addAttribute("pointsPerdus", pointsPerdus);
		return "resultatPenduCapitales";
	}
	@RequestMapping(value="resultatPenduSujets")
	public String resultatPenduSujets(Model model, SocialModel sm, HttpServletRequest req, GameModel gm, PenduModel penduModel){
//		recupération pour chaque sujet: le mot, la lettre, les points (gagnés et totals)
//		pour tous les sujets: la longueur
//		le temps restant
		HttpSession session = req.getSession();
		
		String motCompletPays = (String) session.getAttribute("motPays");
		String lettreDevoileePays = (String) session.getAttribute("lettreStringPays");
		char lettreCharPays = lettreDevoileePays.charAt(0);
		Integer pointsMaxPays = (Integer) session.getAttribute("pointsMaxPays");
		Integer pointsPays = (Integer) session.getAttribute("pointsPays");
		String nbErreursPays = (String)session.getAttribute("nbErreursPays");
		System.out.println("le mot du pays: "+motCompletPays+", la lettre : "+lettreCharPays+", les pointsMax: "+pointsMaxPays);
		
		String motCompletCapitales = (String) session.getAttribute("motCapitales");
		String lettreDevoileeCapitales = (String) session.getAttribute("lettreStringCapitales");
		char lettreCharCapitales = lettreDevoileeCapitales.charAt(0);
		Integer pointsMaxCapitales = (Integer) session.getAttribute("pointsMaxCapitales");
		Integer pointsCapitales = (Integer) session.getAttribute("pointsCapitales");
		String nbErreursCapitales = (String)session.getAttribute("nbErreursCapitales");
		System.out.println("le mot de la  Capitale: "+motCompletCapitales+", la lettre : "+lettreCharCapitales+", les pointsMax: "+pointsMaxCapitales);
		
		String motCompletNobels = (String) session.getAttribute("motNobels");
		String lettreDevoileeNobels = (String) session.getAttribute("lettreStringNobels");
		char lettreCharNobels = lettreDevoileeNobels.charAt(0);
		Integer pointsMaxNobels = (Integer) session.getAttribute("pointsMaxNobels");
		Integer pointsNobels = (Integer) session.getAttribute("pointsNobels");
		String nbErreursNobels = (String)session.getAttribute("nbErreursNobels");
		System.out.println("le mot du Nobel: "+motCompletNobels+", la lettre : "+lettreCharNobels+", les pointsMax: "+pointsMaxNobels);
		
		String motCompletArtistes = (String) session.getAttribute("motArtistes");
		String lettreDevoileeArtistes = (String) session.getAttribute("lettreStringArtistes");
		char lettreCharArtistes = lettreDevoileeArtistes.charAt(0);
		Integer pointsMaxArtistes = (Integer) session.getAttribute("pointsMaxArtistes");
		Integer pointsArtistes = (Integer) session.getAttribute("pointsArtistes");
		String nbErreursArtistes = (String)session.getAttribute("nbErreursArtistes");
		System.out.println("le mot de l'ARTISTE: "+motCompletArtistes+", la lettre : "+lettreCharArtistes+", les pointsMax: "+pointsMaxArtistes);
		
		String motCompletPresidents = (String) session.getAttribute("motPresidents");
		String lettreDevoileePresidents = (String) session.getAttribute("lettreStringPresidents");
//		char lettreCharPresidents = lettreDevoileePresidents.charAt(0);
		Integer pointsMaxPresidents = (Integer) session.getAttribute("pointsMaxPresidents");
		Integer pointsPresidents = (Integer) session.getAttribute("pointsPresidents");
		String nbErreursPresidents = (String)session.getAttribute("nbErreursPresidents");
		System.out.println("le mot du President: "+motCompletPresidents+", le mot complet : "+motCompletPresidents+", les pointsMax: "+pointsMaxPresidents);
		
		
//		Enregistement des données dans la base
		
	
		
//		Envoie des resultats à la vue pour affichage
		
		model.addAttribute("motCompletPays", motCompletPays);
		model.addAttribute("lettreCharPays", lettreCharPays);
		model.addAttribute("pointsMaxPays", pointsMaxPays);
		model.addAttribute("pointsPays", pointsPays);
		model.addAttribute("nbErreursPays", nbErreursPays);
		
		model.addAttribute("motCompletCapitales", motCompletCapitales);
		model.addAttribute("lettreCharCapitales", lettreCharCapitales);
		model.addAttribute("pointsMaxCapitales", pointsMaxCapitales);
		model.addAttribute("pointsCapitales", pointsCapitales);
		model.addAttribute("nbErreursCapitales", nbErreursCapitales);
		
		model.addAttribute("motCompletNobels", motCompletNobels);
		model.addAttribute("lettreCharNobels", lettreCharNobels);
		model.addAttribute("pointsMaxNobels", pointsMaxNobels);
		model.addAttribute("pointsNobels", pointsNobels);
		model.addAttribute("nbErreursNobels", nbErreursNobels);
		
		model.addAttribute("motCompletArtistes", motCompletArtistes);
		model.addAttribute("lettreCharArtistes", lettreCharArtistes);
		model.addAttribute("pointsMaxArtistes", pointsMaxArtistes);
		model.addAttribute("pointsArtistes", pointsArtistes);
		model.addAttribute("nbErreursArtistes", nbErreursArtistes);
		
		model.addAttribute("motCompletPresidents", motCompletPresidents);
//		model.addAttribute("lettreCharPresidents", lettreCharPresidents);
		model.addAttribute("pointsMaxPresidents", pointsMaxPresidents);
		model.addAttribute("pointsPresidents", pointsPresidents);
		model.addAttribute("nbErreursPresidents", nbErreursPresidents);
		
		
		
		Integer longueurSujets = (Integer)session.getAttribute("longueurMot");
		String tempsRestant = req.getParameter("tempsRestant");
		
		model.addAttribute("longueurSujets", longueurSujets);
		model.addAttribute("tempsRestant", tempsRestant);
		
		
		return "resultatPenduSujets";
	}
	
	@RequestMapping(value="resultatPenduSujetsTempsFini")
	public String resultatPenduSujetsTempsFini(Model model, SocialModel sm, HttpServletRequest req, GameModel gm, PenduModel penduModel){
//		recupération pour chaque sujet: le mot, la lettre, les points (gagnés et totals)
//		pour tous les sujets: la longueur
//		le temps restant
		HttpSession session = req.getSession();
		
		String motCompletPays = (String) session.getAttribute("motPays");
		String lettreDevoileePays = (String) session.getAttribute("lettreStringPays");
		char lettreCharPays = lettreDevoileePays.charAt(0);
		Integer pointsMaxPays = (Integer) session.getAttribute("pointsMaxPays");
		Integer pointsPays = (Integer) session.getAttribute("pointsPays");
		String nbErreursPays = (String)session.getAttribute("nbErreursPays");
		System.out.println("le mot du pays: "+motCompletPays+", la lettre : "+lettreCharPays+", les pointsMax: "+pointsMaxPays);
		
		String motCompletCapitales = (String) session.getAttribute("motCapitales");
		String lettreDevoileeCapitales = (String) session.getAttribute("lettreStringCapitales");
		char lettreCharCapitales = lettreDevoileeCapitales.charAt(0);
		Integer pointsMaxCapitales = (Integer) session.getAttribute("pointsMaxCapitales");
		Integer pointsCapitales = (Integer) session.getAttribute("pointsCapitales");
		String nbErreursCapitales = (String)session.getAttribute("nbErreursCapitales");
		System.out.println("le mot de la  Capitale: "+motCompletCapitales+", la lettre : "+lettreCharCapitales+", les pointsMax: "+pointsMaxCapitales);
		
		String motCompletNobels = (String) session.getAttribute("motNobels");
		String lettreDevoileeNobels = (String) session.getAttribute("lettreStringNobels");
		char lettreCharNobels = lettreDevoileeNobels.charAt(0);
		Integer pointsMaxNobels = (Integer) session.getAttribute("pointsMaxNobels");
		Integer pointsNobels = (Integer) session.getAttribute("pointsNobels");
		String nbErreursNobels = (String)session.getAttribute("nbErreursNobels");
		System.out.println("le mot du Nobel: "+motCompletNobels+", la lettre : "+lettreCharNobels+", les pointsMax: "+pointsMaxNobels);
		
		String motCompletArtistes = (String) session.getAttribute("motArtistes");
		String lettreDevoileeArtistes = (String) session.getAttribute("lettreStringArtistes");
		char lettreCharArtistes = lettreDevoileeArtistes.charAt(0);
		Integer pointsMaxArtistes = (Integer) session.getAttribute("pointsMaxArtistes");
		Integer pointsArtistes = (Integer) session.getAttribute("pointsArtistes");
		String nbErreursArtistes = (String)session.getAttribute("nbErreursArtistes");
		System.out.println("le mot de l'ARTISTE: "+motCompletArtistes+", la lettre : "+lettreCharArtistes+", les pointsMax: "+pointsMaxArtistes);
		
		String motCompletPresidents = (String) session.getAttribute("motPresidents");
		String lettreDevoileePresidents = (String) session.getAttribute("lettreStringPresidents");
//		char lettreCharPresidents = lettreDevoileePresidents.charAt(0);
		Integer pointsMaxPresidents = (Integer) session.getAttribute("pointsMaxPresidents");
		Integer pointsPresidents = (Integer) session.getAttribute("pointsPresidents");
		String nbErreursPresidents = (String)session.getAttribute("nbErreursPresidents");
		System.out.println("le mot du President: "+motCompletPresidents+", le mot complet : "+motCompletPresidents+", les pointsMax: "+pointsMaxPresidents);
		
		
//		Enregistement des données dans la base
		
	
		
//		Envoie des resultats à la vue pour affichage
		
		model.addAttribute("motCompletPays", motCompletPays);
		model.addAttribute("lettreCharPays", lettreCharPays);
		model.addAttribute("pointsMaxPays", pointsMaxPays);
		model.addAttribute("pointsPays", pointsPays);
		model.addAttribute("nbErreursPays", nbErreursPays);
		
		model.addAttribute("motCompletCapitales", motCompletCapitales);
		model.addAttribute("lettreCharCapitales", lettreCharCapitales);
		model.addAttribute("pointsMaxCapitales", pointsMaxCapitales);
		model.addAttribute("pointsCapitales", pointsCapitales);
		model.addAttribute("nbErreursCapitales", nbErreursCapitales);
		
		model.addAttribute("motCompletNobels", motCompletNobels);
		model.addAttribute("lettreCharNobels", lettreCharNobels);
		model.addAttribute("pointsMaxNobels", pointsMaxNobels);
		model.addAttribute("pointsNobels", pointsNobels);
		model.addAttribute("nbErreursNobels", nbErreursNobels);
		
		model.addAttribute("motCompletArtistes", motCompletArtistes);
		model.addAttribute("lettreCharArtistes", lettreCharArtistes);
		model.addAttribute("pointsMaxArtistes", pointsMaxArtistes);
		model.addAttribute("pointsArtistes", pointsArtistes);
		model.addAttribute("nbErreursArtistes", nbErreursArtistes);
		
		model.addAttribute("motCompletPresidents", motCompletPresidents);
//		model.addAttribute("lettreCharPresidents", lettreCharPresidents);
		model.addAttribute("pointsMaxPresidents", pointsMaxPresidents);
		model.addAttribute("pointsPresidents", pointsPresidents);
		model.addAttribute("nbErreursPresidents", nbErreursPresidents);
		
		
		
		Integer longueurSujets = (Integer)session.getAttribute("longueurMot");
		String tempsRestant = req.getParameter("tempsRestant");
		
		model.addAttribute("longueurSujets", longueurSujets);
		model.addAttribute("tempsRestant", tempsRestant);
		
		
		return "resultatPenduSujetsTempsFini";
	}

}
