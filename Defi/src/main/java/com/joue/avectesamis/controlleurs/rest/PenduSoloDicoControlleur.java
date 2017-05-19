package com.joue.avectesamis.controlleurs.rest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joue.avectesamis.dao.PenduDao;
import com.joue.avectesamis.dao.repositories.FriendRepository;
import com.joue.avectesamis.entites.AbcChallenge;
import com.joue.avectesamis.entites.AbcSolo;
import com.joue.avectesamis.entites.Friend;
import com.joue.avectesamis.entites.jeux.pendu.PenduDicoChallenge;
import com.joue.avectesamis.entites.jeux.pendu.PenduDicoSolo;
import com.joue.avectesamis.entites.jeux.pendu.PenduSujetsSolo;
import com.joue.avectesamis.metier.ChallengeMetier;
import com.joue.avectesamis.models.GameModel;
import com.joue.avectesamis.models.PenduModel;
import com.joue.avectesamis.models.SocialModel;
import com.joue.avectesamis.models.Word;

@Controller
public class PenduSoloDicoControlleur {
	
	@Autowired
	private PenduDao penduDao;
	@Autowired
	private ChallengeMetier metier;
	
	@RequestMapping(value="penduSoloDico", method=RequestMethod.GET)
	public String soloDico(Model model, HttpServletRequest req, PenduModel penduModel, Word word ){
//		word = new Word(req);
		word.initWord(req);
		
//		la map qui va stocker tous les objets
		Map<String, Object> map = new HashMap<String, Object>();
		
		HttpSession session = req.getSession();
		Long id = (Long) session.getAttribute("id");
		Friend moi = metier.getFriend(id);
		
		
		penduModel.setMesDicoSolo(penduDao.mesDicoSolos(id));
		
	     map.put("penduModel", penduModel);
	     model.addAttribute("penduModel", penduModel);

		return "penduSoloDico";
		
	} 
//	Methode qui permet de mixer deux ArrayList
	public List<PenduDicoChallenge> mixageList(List<PenduDicoChallenge> mesChallengesJoues, List<PenduDicoChallenge> challengeAmisOrdreCoupe ){
		List<PenduDicoChallenge> resultat = new ArrayList<PenduDicoChallenge>();
		int size = Math.max(mesChallengesJoues.size(), challengeAmisOrdreCoupe.size());
		
		for (int i = 0; i < size; i++) {
	        if (i < mesChallengesJoues.size()) resultat.add(mesChallengesJoues.get(i));
	        if (i < challengeAmisOrdreCoupe.size()) resultat.add(challengeAmisOrdreCoupe.get(i));
	    }

		
		return resultat;
		
	}
	
	
	@RequestMapping(value="penduSoloDicoJeu", method=RequestMethod.GET)
	public String AbcSoloDicoJeu(Model model, PenduModel penduModel, HttpServletRequest request, Word word){
		word = new Word(request);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		Long id = 2L; 
//		à partir du mot tiré au sort, on choisit une lettre qui sera affichée pour guider le joueur
		String motComplet = word.getWord();
		int longueurMot = motComplet.length();
		int indexLettre = longueurMot/2;
		char lettreDevoilee = motComplet.charAt(indexLettre);
		String lettreString = String.valueOf(lettreDevoilee);
		
		session.setAttribute("motComplet", motComplet);
		session.setAttribute("lettreString", lettreString);
					
					model.addAttribute("longueurMot", longueurMot);
					model.addAttribute("lettreDevoilee", lettreDevoilee);
					model.addAttribute("penduModel", penduModel);
					model.addAttribute("word", word);
					map.put("penduModel", penduModel);
					map.put("word", word);
				
		
		return "penduSoloDicoJeu";
		
	}
	@RequestMapping(value="penduSoloDicoCorrection", method=RequestMethod.GET)
	public Map<String, Object> penduChallengeCorrection(PenduModel penduModel, HttpServletRequest request, Word word){
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		
//		recuperation du mot, nombre de coups, nombre d'erreurs, temps restant, points gagnés, pointsMax
		String mot ="";
		int nbCoups =0;
		int nbErreurs =0;
		int score =0;
		int scoreMax = 0;
		int temps =0;
		int tempsRestants = 10;
		int nbAide = 0;
		boolean aide = false;
		Collection<Character> lesChoix =null;
		
		HttpSession session = request.getSession();
		Long id =  1L;
//				(Long) session.getAttribute("id");
//		le temps en fonction du nombre de r�sultats possibles
		
//		recup�raiton de l'ami
		Long idAmi= 2L;
//				(long) Integer.parseInt(request.getParameter("idAmi"));
		session.setAttribute("idAmi", idAmi);
		Friend ami= metier.getFriend(idAmi);
		
//		moi
		Friend moi = metier.getFriend(id);
		
		PenduDicoSolo solo = new PenduDicoSolo(new Date(), null, 0, false, null, 0, false, 0, null, 0, true, 0);
//		penduDao.savePenduDicoChallenge(challenge, id, idAmi);
		
		map.put("solo", solo);
		
		return map;
	}
	
	@RequestMapping(value="infosPenduSolo")
	public String infosPenduSolo(Model model, SocialModel sm, HttpServletRequest req, GameModel gm, PenduModel penduModel){
		HttpSession session = req.getSession();
		Long id =  (Long) session.getAttribute("id");
		
		
		penduDao.mesDicoSolos(id);
		penduModel.setMesDicoSolo(penduDao.mesDicoSolos(id));;
		
		model.addAttribute("sm", sm);
		model.addAttribute("gm", gm);
		model.addAttribute("penduModel", penduModel);
		return "infosPenduSolo";
	}
	@RequestMapping(value="imageErreurPendu")
	public String imageErreurPendu(Model model, SocialModel sm, HttpServletRequest req, GameModel gm, PenduModel penduModel){

		model.addAttribute("penduModel", penduModel);
		return "imageErreurPendu";
	}
	@RequestMapping(value="resultatPendu")
	public String resultat(Model model, SocialModel sm, HttpServletRequest req, GameModel gm, PenduModel penduModel){
		HttpSession session = req.getSession();
		String motComplet = (String) session.getAttribute("motComplet");
		String nbErreurs = req.getParameter("nbErreurs");
		String tempsRestant = 	req.getParameter("tempsRestant");
		
		String lettreDevoilee = (String) session.getAttribute("lettreString");
		char lettreChar = lettreDevoilee.charAt(0);
		
		String motUser = req.getParameter("motUser");
		int points =0;
		int nbFoisLettreDansMot =0;
		if(motUser!="null"){
			for(int i =0; i<motComplet.length(); i++){
				if(motComplet.charAt(i)==lettreChar){
					nbFoisLettreDansMot = nbFoisLettreDansMot + 10;
				}
				if(motComplet.charAt(i)== motUser.charAt(i)){
					System.out.println("motComplet.charAt(i) = "+motComplet.charAt(i)+" motUser.charAt(i)"+motUser.charAt(i));
					points = points + 10;
				}
				System.out.println("le nombre de points Total "+points);
			}
			motUser = null;
		}	
		if(points>nbFoisLettreDansMot){
			points = points - nbFoisLettreDansMot;
		}else {
			points = 0;
		}
//		je peux inserer les données dans la base que si "motUser = null"
		PenduDicoSolo penduDicoSolo = new PenduDicoSolo();
		
		System.out.println("le nombre de points ganés au final = "+points);
		
		System.out.println("le nombre Erreurs "+nbErreurs+" et le temps Restant "+tempsRestant);
		
//		Pour les points: chaque lettre rapporte 10 points
//		quand on trouve tout le mot avant la fin, chaque 10 secondes rapportent 5 points de BONUS
		
		
//		bien que le timer n'est plus visible, mais il continu à s'exécuter alors on donne comme longueur du mot un nombre arbitraire
//		pour etre sûr qu'avant la fin du décompte l'utilisateur a quitter la page (le mieux serai d'arreter la timer, A VOIR APRES)
		
		model.addAttribute("points", points);
		model.addAttribute("nbErreurs", nbErreurs);
		model.addAttribute("tempsRestant", tempsRestant);
		model.addAttribute("motComplet", motComplet);
		model.addAttribute("penduModel", penduModel);
		return "resultatPendu";
	}
	
	
}
